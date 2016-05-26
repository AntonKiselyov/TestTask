package ru.yandex.service;

import com.squareup.okhttp.ResponseBody;
import org.apache.log4j.Logger;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;
import ru.yandex.core.ClientProperties;
import ru.yandex.core.ExpressionNodeParser;
import ru.yandex.model.ExpressionNode;
import ru.yandex.expressionservice.ServerApp;

import java.io.IOException;

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
                    logger.error("JSON : " + jsonInString);
                    ExpressionNode expressionNode = ExpressionNodeParser.getInstance().parseJsonToExpressionNode(jsonInString);
                    logger.error("Tree : " + expressionNode);
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
