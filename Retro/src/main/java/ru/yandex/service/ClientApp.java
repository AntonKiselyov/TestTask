package ru.yandex.service;

import com.squareup.okhttp.ResponseBody;
import org.apache.log4j.Logger;
import ru.yandex.core.*;
import ru.yandex.model.*;
import retrofit.*;

import java.io.IOException;

/**
 * Created by Admin on 28.04.2016.
 */
public class ClientApp {
    final static Logger logger = Logger.getLogger(ClientApp.class);
    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            try {
                String expression = String.valueOf(args[0]);
                String BASE_URL = new ClientProperties().getProperties();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .build();
                ExpressionService expressionService = retrofit.create(ExpressionService.class);
                Call<ResponseBody> call = expressionService.getExpressionTree(expression);
                Response<ResponseBody> response = call.execute();
                String jsonInString = response.body().string();
                logger.error("JSON : " + jsonInString);
                ExpressionNode expressionNode = ExpressionNodeDeserializer.getInstance().deserialize(jsonInString);
                logger.error("Tree : " + expressionNode);
            } catch (NumberFormatException e) {
                logger.error("Argument " + args[0] + " must be string.");
                System.exit(1);
            }
        }
        else {
            logger.error("Argument expression not found!");
            System.exit(1);
        }
    }
}
