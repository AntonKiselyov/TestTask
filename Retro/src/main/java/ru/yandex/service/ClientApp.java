package ru.yandex.service;

import com.squareup.okhttp.ResponseBody;
import org.apache.log4j.Logger;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;
import ru.yandex.core.ClientProperties;
import ru.yandex.core.ExpressionCalculator;
import ru.yandex.core.ResultStructureParser;
import ru.yandex.expressionservice.ServerApp;
import ru.yandex.model.ResultStructure;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Admin on 28.04.2016.
 */
class ClientApp {
    private final static Logger logger = Logger.getLogger(ClientApp.class);
    private static Thread serverThread;
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            try {
                String expression = String.valueOf(args[0]);
                String BASE_URL = new ClientProperties().getProperties();
                serverThread = new Thread(() -> {
                    try {
                        ServerApp.runServer();
                    } catch (IOException e) {
                        logger.error("Error when run server app: " + e.getMessage());
                    }
                });
                serverThread.start();
                if(serverThread.isAlive()) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .build();
                    ExpressionService expressionService = retrofit.create(ExpressionService.class);
                    Call<ResponseBody> call = expressionService.getExpressionTree(expression);
                    Response<ResponseBody> response = call.execute();
                    String jsonInString = response.body().string();
                    logger.debug("JSON : " + jsonInString);
                    ResultStructure resultStructure = ResultStructureParser.getInstance().parseJsonToResultStructure(jsonInString);
                    logger.debug("Result : " + resultStructure);

                    LinkedList<Double> list = new LinkedList<>();
                    //Считываем число аргументов и сами значения переменных
                    int count = Integer.valueOf(args[1]);
                    int i = 2;
                    while(i != count+2) {
                        list.add(Double.valueOf(args[i]));
                        i++;
                    }

                    resultStructure.getVariables().getVars().entrySet().stream()
                            .forEach(stringDoubleEntry -> {
                                stringDoubleEntry.setValue(list.removeFirst());
                            });
                    logger.debug("Result of expression: " + ExpressionCalculator.getCalculator().calculate(resultStructure.getExpressionNode(),resultStructure.getVariables()));
                }
                serverThread.interrupt();
            } catch (NumberFormatException e) {
                logger.error("Argument " + args[0] + " must be string.");
                serverThread.interrupt();
                System.exit(1);
            }
        }
        else {
            logger.error("Argument expression not found!");
            System.exit(1);
        }
    }
}
