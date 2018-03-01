package com.globant.equattrocchio.cleanarchitecture.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.constants.DetailKeys;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.DialogFragmentDetailPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.DialogFragmentDetailView;

public class DialogFragmentDetail extends DialogFragment {

    private DialogFragmentDetailPresenter presenter;

    public DialogFragmentDetail(){
        super();
    }

    public static DialogFragmentDetail newInstance(Parcelable image){
        DialogFragmentDetail detailFragment = new DialogFragmentDetail();
        Bundle fragmentBundle = new Bundle();

        fragmentBundle.putParcelable(DetailKeys.DITAIL_IMAGE_KEY, image);
        detailFragment.setArguments(fragmentBundle);

        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View customDialogFragment = inflater.inflate(R.layout.dialog_fragment_detail, container, false);
        return customDialogFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new DialogFragmentDetailPresenter(new DialogFragmentDetailView(this));
        presenter.init();
    }
}
