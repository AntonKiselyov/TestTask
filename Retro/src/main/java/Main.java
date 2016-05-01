import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.ResponseBody;
import jsonmapper.JsonDeserializator;
import model.MyTreeNode;
import retrofit.GsonConverterFactory;
import retrofit.*;
import service.MyService;

import java.io.IOException;

/**
 * Created by Admin on 28.04.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/myapp/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MyService myService = retrofit.create(MyService.class);

        final Call<ResponseBody> json = myService.listRepos("+a\r\n");
        json.enqueue(new Callback<ResponseBody>() {
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    System.out.println("success");
                    try {
                        String jsonInString = response.body().string();
                        System.out.println(jsonInString);
                        MyTreeNode myTreeNode = new JsonDeserializator().deserialize(jsonInString);
                        System.out.println(myTreeNode);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else System.out.println("not success");
            }

            public void onFailure(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }
}
