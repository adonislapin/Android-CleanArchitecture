package com.globant.equattrocchio.data.service.api;

import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SplashbaseApi {

    @GET("api/v1/images/latest")
    Call<Result> getImages();

    @GET("/api/v1/images/{id}")
    Call<Image> getImageForId(@Path("id") String id);
}
