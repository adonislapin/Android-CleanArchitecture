package com.globant.equattrocchio.domain.db;

import io.reactivex.Observer;

public interface DBInterface {

    void addImages(Observer<Boolean> observer , Object images);

    Object getAllImages();

    void getAllImages(Observer<Object> observer, Object contentResolver);
}
