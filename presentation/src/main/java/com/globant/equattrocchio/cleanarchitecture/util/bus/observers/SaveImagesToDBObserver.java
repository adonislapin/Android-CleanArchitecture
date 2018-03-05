package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

import com.globant.equattrocchio.data.response.Image;

import java.util.List;

public abstract class SaveImagesToDBObserver extends BusObserver<SaveImagesToDBObserver.SaveImagesToDBAction> {

    public SaveImagesToDBObserver() {
        super(SaveImagesToDBAction.class);
    }
    public static class SaveImagesToDBAction {
        public List<Image> images;

        public SaveImagesToDBAction(List<Image> images){
            this.images = images;
        }

        public List<Image> getImages() {
            return images;
        }
    }

}
