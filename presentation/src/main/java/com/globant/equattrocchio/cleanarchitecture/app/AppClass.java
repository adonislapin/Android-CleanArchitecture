package com.globant.equattrocchio.cleanarchitecture.app;

import android.app.Application;

import io.realm.Realm;


public class AppClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
