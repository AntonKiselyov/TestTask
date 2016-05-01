package ru.yandex.service;

import com.squareup.okhttp.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;
import ru.yandex.model.*;
import ru.yandex.core.*;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Admin on 30.04.2016.
 */
public class ClientAppTest {

    private static ExpressionService expressionService;
    private static final String testing = "{\"expression\":\"(+)\",\"nodes\":[{\"expression\":\"(\"},{\"expression\":\"+\",\"nodes\":[{\"expression\":\"+\"}]},{\"expression\":\")\"}]}";
    private static ExpressionNode tree;

    @Before
    public void setUp() throws Exception {
        String BASE_URL = new ClientProperties().getProperties();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        expressionService = retrofit.create(ExpressionService.class);
        tree = ExpressionNodeDeserializer.getInstance().deserialize(testing);
    }

    @Test
    public void testGetTree() throws IOException {
        Call<ResponseBody> call = expressionService.getExpressionTree("(+)");
        Response<ResponseBody> response = call.execute();
        String jsonInString = response.body().string();
        assert jsonInString.equals(testing);
        ExpressionNode expressionNode = ExpressionNodeDeserializer.getInstance().deserialize(jsonInString);
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

}