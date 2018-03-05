package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.app.Fragment;
import android.widget.Toast;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;

public class FragmentView<T extends Fragment, HV, HA> {

    protected Observer<HV> viewObserver;
    protected Observer<HA> adapterObserver;

    private WeakReference<T> fragmentRef;

    protected FragmentView(T fragment) {
        fragmentRef = new WeakReference<>(fragment);
    }

    @Nullable
    public T getFragment() {
        return fragmentRef.get();
    }

    @Nullable
    public MainActivity getActivity() {
        return (MainActivity) fragmentRef.get().getActivity();
    }

    @Nullable
    public Context getContext() {
        return getActivity();
    }

    public void showMessage(@StringRes int message) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String message) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public Observer<HV> getViewObserver() {
        return viewObserver;
    }

    public void setViewObserver(Observer<HV> observer) {
        this.viewObserver = observer;
    }

    public Observer<HA> getAdapterObserver() {
        return adapterObserver;
    }

    public void setAdapterObserver(Observer<HA> observer) {
        this.adapterObserver = observer;
    }

    protected void onViewNext(HV value) {
        if (viewObserver == null) {
            return;
        }
        viewObserver.onNext(value);
    }

}
