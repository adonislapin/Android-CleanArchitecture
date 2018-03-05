package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;
import android.content.ContentResolver;
import android.util.Log;

import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceByIdObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CursorObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.SaveImagesToDBObserver;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.GetAllSavedImagesUseCase;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.SetImagesUseCase;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class ImagesPresenter {

    private ImagesView view;
    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetImageByIdUseCase getImageByIdUseCase;
    private SetImagesUseCase setImagesUseCase;
    private GetAllSavedImagesUseCase getAllSavedImagesUseCase;

    public ImagesPresenter(ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase, GetImageByIdUseCase getImageByIdUseCase,
            SetImagesUseCase setImagesUseCase, GetAllSavedImagesUseCase getAllSavedImagesUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
        this.getImageByIdUseCase = getImageByIdUseCase;
        this.setImagesUseCase = setImagesUseCase;
        this.getAllSavedImagesUseCase = getAllSavedImagesUseCase;
    }

    private void onCallServiceButtonPressed() {

        getLatestImagesUseCase.execute(new DisposableObserver<Object>() {
            @Override
            public void onNext(@NonNull Object result) {
                view.fillImages((Result) result);
                onSaveImages(((Result) result).getImages());
            }

            @Override
            public void onError(@NonNull Throwable e) {
               view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getLatestImages(null);
            }
        },null);

        //todo acá tengo que llamar a la domain layer para que llame a la data layer y haga el llamdo al servicio
    }

    private void onCallServiceById(final String id){
        getImageByIdUseCase.execute(new DisposableObserver<Object>() {

            @Override
            public void onNext(@NonNull Object o) {
                view.showDetailFor((Image)o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {
                new ImagesServicesImpl().getImageForId(null, id);
            }
        }, id);
    }

    private void onSaveImages(List<Image> result){
        setImagesUseCase.execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                Log.d("DB Realm","data saved");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("DB Realm","fail saving data");
            }

            @Override
            public void onComplete() {
            }
        }, result);
    }

    private void getAllSavedImages(ContentResolver contentResolver){
        getAllSavedImagesUseCase.execute(new DisposableObserver<Object>() {
            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, contentResolver);
    }

    private void loadFromPreferences(){
       // view.showText("EL TEXTO QUE ME TRAGIA DE LAS PREFERENCES");// todo: traerme el texto de las preferences
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new CallServiceButtonObserver() {
            @Override
            public void onEvent(CallServiceButtonPressed event) {
                onCallServiceButtonPressed();
            }
        });

        RxBus.subscribe(activity, new CallServiceByIdObserver() {
            @Override
            public void onEvent(CallServiceItemPressed value) {
                onCallServiceById(value.getId());
            }
        });

        RxBus.subscribe(activity, new SaveImagesToDBObserver() {
            @Override
            public void onEvent(SaveImagesToDBAction value) {
                onSaveImages(value.getImages());
            }
        });

        RxBus.subscribe(activity, new CursorObserver() {
            @Override
            public void onEvent(CursorAction value) {
                getAllSavedImages(value.getContentResolver());
            }
        });
    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }

}
