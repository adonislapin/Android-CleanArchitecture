package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.db.DBInterface;

import io.reactivex.observers.DisposableObserver;

public class SetImagesUseCase extends UseCase<Boolean, Object> {

    DBInterface dbInterface;

    public SetImagesUseCase(DBInterface dbInterface){
        super();
        this.dbInterface = dbInterface;
    }


    @Override
    void buildUseCaseObservable(DisposableObserver<Boolean> observer, Object o) {
        dbInterface.addImages(observer, o);
    }
}
