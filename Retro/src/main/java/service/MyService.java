package service;

import com.squareup.okhttp.ResponseBody;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

/**
 * Created by Admin on 28.04.2016.
 */

public interface MyService {
    @GET("expressions/{expression}")
    Call<ResponseBody> listRepos(@Path("expression") String expression);
}
