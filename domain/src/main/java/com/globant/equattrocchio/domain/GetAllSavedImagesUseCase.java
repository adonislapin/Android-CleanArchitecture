package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.db.DBInterface;

import io.reactivex.observers.DisposableObserver;

public class GetAllSavedImagesUseCase extends UseCase<Object, Object> {

    DBInterface dbInterface;

    public GetAllSavedImagesUseCase(DBInterface dbInterface){
        super();
        this.dbInterface = dbInterface;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Object> observer, Object o) {
        dbInterface.getAllImages(observer, o);
    }
}
