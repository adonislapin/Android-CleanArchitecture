package com.globant.equattrocchio.domain;

import android.media.Image;

import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class GetImageByIdUseCase extends UseCase<Object, String> {

    private ImagesServices imagesServices;

    public GetImageByIdUseCase(ImagesServices imagesServices){
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Object> observer, String s) {
        imagesServices.getImageForId(observer, s);
    }


}
