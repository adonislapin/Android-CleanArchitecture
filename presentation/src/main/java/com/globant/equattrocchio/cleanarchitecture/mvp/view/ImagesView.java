package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.adapter.ImageAdapter;
import com.globant.equattrocchio.cleanarchitecture.fragments.DialogFragmentDetail;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceByIdObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CursorObserver;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView implements IClickImage {

    @BindView(R.id.list_of_images) RecyclerView listView;
    private ImageAdapter adapter = null;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    @OnClick(R.id.button_refresh)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    public void fillImages(Result result){
        if(adapter == null){
            adapter = new ImageAdapter(getContext(), result);
            adapter.setiClickImage(this);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            listView.setLayoutManager(layoutManager);
            listView.setAdapter(adapter);
        } else {
            adapter.setResult(result);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onImageClicked(String id) {
        RxBus.post(new CallServiceByIdObserver.CallServiceItemPressed(id));
    }

    public void showDetailFor(Image image) {
        if (image != null) {
            DialogFragmentDetail dialogFragmentDetail = DialogFragmentDetail.newInstance(image);
            dialogFragmentDetail.show(getFragmentManager(), "fragment_dialog");
        } else {
            showError();
        }
    }

    public void showError(){
        if(getContext() != null){
            Toast.makeText(getContext(), getContext().getText(R.string.connection_error), Toast.LENGTH_SHORT).show();
        }
    }

    public void getDataFromDb(){
        RxBus.post(new CursorObserver.CursorAction(getContentResolver()));
    }

    public ContentResolver getContentResolver(){
        if(getContext() != null){
            return getContext().getContentResolver();
        } else
            return null;
    }
}
