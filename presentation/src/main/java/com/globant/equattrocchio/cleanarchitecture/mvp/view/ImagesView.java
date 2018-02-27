package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.adapter.ImageAdapter;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.data.response.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.list_of_images) RecyclerView listView;
    private ImageAdapter adapter = null;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    public void fillImages(Result result){
        if(adapter == null){
            adapter = new ImageAdapter(getContext(), result);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            listView.setLayoutManager(layoutManager);
            listView.setAdapter(adapter);
        } else {
            adapter.setResult(result);
            adapter.notifyDataSetChanged();
        }
    }

    public void showError(){
        if(getContext() != null){
            Toast.makeText(getContext(), getContext().getText(R.string.connection_error), Toast.LENGTH_SHORT).show();
        }
    }
}
