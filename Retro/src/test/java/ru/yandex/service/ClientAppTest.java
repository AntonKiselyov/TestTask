package ru.yandex.service;

import com.squareup.okhttp.ResponseBody;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
 * Created by Admin on 30.04.2016.
 */
public class ClientAppTest {

    private static ExpressionService expressionService;
    private static final String testing = "{\"variables\":[\"a\",\"b\",\"c\",\"d\"],\"tree\":{\"expression\":\"(a+b)*(c-d)\",\"nodes\":[{\"expression\":\"(a+b)\",\"nodes\":[{\"expression\":\"(\"},{\"expression\":\"a+b\",\"nodes\":[{\"expression\":\"a\",\"nodes\":[{\"expression\":\"a\"}]},{\"expression\":\"+\"},{\"expression\":\"b\",\"nodes\":[{\"expression\":\"b\"}]}]},{\"expression\":\")\"}]},{\"expression\":\"*\"},{\"expression\":\"(c-d)\",\"nodes\":[{\"expression\":\"(\"},{\"expression\":\"c-d\",\"nodes\":[{\"expression\":\"c\",\"nodes\":[{\"expression\":\"c\"}]},{\"expression\":\"-\"},{\"expression\":\"d\",\"nodes\":[{\"expression\":\"d\"}]}]},{\"expression\":\")\"}]}]}}";
    private static ResultStructure resultStructure;
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
        resultStructure = ResultStructureParser.getInstance().parseJsonToResultStructure(testing);
    }

    @Test
    public void testGetTree() throws IOException {
        Call<ResponseBody> call = expressionService.getExpressionTree("(a+b)*(c-d)");
        Response<ResponseBody> response = call.execute();
        String jsonInString = response.body().string();
        Assert.assertEquals(jsonInString,testing);
        ResultStructure result = ResultStructureParser.getInstance().parseJsonToResultStructure(jsonInString);
        Assert.assertEquals(resultStructure.toString(),result.toString());
    }

    @Test
    public void testComputeTheResult() throws IOException {
        LinkedList<Double> list = new LinkedList<>();
        list.add(10d);
        list.add(5d);
        list.add(10d);
        list.add(6d);
        Call<ResponseBody> call = expressionService.getExpressionTree("(a+b)*(c-d)");
        Response<ResponseBody> response = call.execute();
        String jsonInString = response.body().string();
        ResultStructure resultStructure1 = ResultStructureParser.getInstance().parseJsonToResultStructure(jsonInString);
        resultStructure.getVariables().getVars().entrySet().stream()
                .forEach(stringDoubleEntry -> {
                    stringDoubleEntry.setValue(list.removeFirst());
                });
        Assert.assertEquals(ExpressionCalculator.getCalculator().calculate(resultStructure1.getExpressionNode(),resultStructure1.getVariables()),60);;
    }

    @Test
    public void testGetTreeFail() throws IOException {
        Call<ResponseBody> call = expressionService.getExpressionTree("(+");
        Response<ResponseBody> response = call.execute();
        String jsonInString = response.body().string();
        Assert.assertNotEquals(jsonInString,testing);
    }

    @Before
    public void tearDown() throws IOException {
        thread.interrupt();
    }

}