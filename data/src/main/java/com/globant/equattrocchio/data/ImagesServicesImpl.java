package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL= "http://splashbase.co/";
    private Retrofit retrofit = null;
    private SplashbaseApi api= null;

    public void init(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().
                    baseUrl(URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if(api == null){
            api  = retrofit.create(SplashbaseApi.class);
        }
    }

    @Override
    public void getLatestImages(final Observer<Object> observer) {
        init();

        Call<Result> call = api.getImages();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //todo: show the response.body() on the ui
                observer.onNext(response.body());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //todo: update the UI with a connection error message
                observer.onError(t);
            }
        });
    }

    @Override
    public void getImageForId(final Observer<Object> observer, String id) {
        init();
        Call<Image> call = api.getImageForId(id);

        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                observer.onNext(response.body());
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                observer.onError(t);
            }
        });
    }
}
