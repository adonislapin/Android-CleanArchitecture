package com.globant.equattrocchio.data.service.api;

import com.globant.equattrocchio.data.response.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SplashbaseApi {

    @GET("api/v1/images/latest")
    Call<ResponseBody> getImages();
}
