package com.globant.equattrocchio.domain.service;

import io.reactivex.Observer;

public interface ImagesServices {

<<<<<<< Updated upstream
    void getLatestImages(Observer<Boolean> observer);
=======
    void getLatestImages(Observer<Object> observer);

    void getImageForId(Observer<Object> observer , String id);
>>>>>>> Stashed changes
}
