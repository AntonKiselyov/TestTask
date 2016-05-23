package ru.yandex.service;

import com.squareup.okhttp.ResponseBody;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Admin on 28.04.2016.
 */

public interface ExpressionService {
    @GET("expressions/{expression}")
    Call<ResponseBody> getExpressionTree(@Path("expression") String expression);
}
