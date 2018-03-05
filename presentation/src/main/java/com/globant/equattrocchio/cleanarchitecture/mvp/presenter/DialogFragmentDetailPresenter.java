package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.DialogFragmentDetailView;

public class DialogFragmentDetailPresenter {

    private DialogFragmentDetailView view;

    public DialogFragmentDetailPresenter(DialogFragmentDetailView view){
        this.view = view;
    }

    public void init(){
        view.init();
    }
}
