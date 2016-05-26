package ru.yandex.service;

import com.squareup.okhttp.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;
import ru.yandex.core.ClientProperties;
import ru.yandex.core.ExpressionNodeParser;
import ru.yandex.expressionservice.ServerApp;
import ru.yandex.model.ExpressionNode;

import java.io.IOException;

/**
 * Created by Admin on 30.04.2016.
 */
public class ClientAppTest {

    private static ExpressionService expressionService;
    private static final String testing = "{\"expression\":\"(+)\",\"nodes\":[{\"expression\":\"(\"},{\"expression\":\"+\",\"nodes\":[{\"expression\":\"+\"}]},{\"expression\":\")\"}]}";
    private static ExpressionNode tree;
    private static ServerApp serverApp;
    private static Thread thread;

    @Before
    public void setUp() throws Exception {
        thread = new Thread(() -> {
            try {
                ServerApp.runServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        String BASE_URL = new ClientProperties().getProperties();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        expressionService = retrofit.create(ExpressionService.class);
        tree = ExpressionNodeParser.getInstance().parseJsonToExpressionNode(testing);
    }

    @Test
    public void testGetTree() throws IOException {
        Call<ResponseBody> call = expressionService.getExpressionTree("(+)");
        Response<ResponseBody> response = call.execute();
        String jsonInString = response.body().string();
        assert jsonInString.equals(testing);
        ExpressionNode expressionNode = ExpressionNodeParser.getInstance().parseJsonToExpressionNode(jsonInString);
        System.out.println(expressionNode);
        assert expressionNode.equals(tree);
    }

    @Test
    public void testGetTreeFail() throws IOException {
        Call<ResponseBody> call = expressionService.getExpressionTree("(+");
        Response<ResponseBody> response = call.execute();
        String jsonInString = response.body().string();
        assert !jsonInString.equals(testing);
    }

    @Test
    public void testGetTree2() throws IOException {
        Call<ResponseBody> call = expressionService.getExpressionTree("a(+)");
        Response<ResponseBody> response = call.execute();
        String jsonInString = response.body().string();
        assert jsonInString.equals(testing);
    }

    @Before
    public void tearDown() throws IOException {
        thread.interrupt();
    }

}