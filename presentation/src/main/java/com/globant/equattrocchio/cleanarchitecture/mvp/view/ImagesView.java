package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
<<<<<<< Updated upstream
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
=======
import com.globant.equattrocchio.cleanarchitecture.adapter.ImageAdapter;
import com.globant.equattrocchio.cleanarchitecture.fragments.DialogFragmentDetail;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceByIdObserver;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
>>>>>>> Stashed changes

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView implements IClickImage {

    @BindView(R.id.tv_incoming_json) TextView tvlabel;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void showText(String text) {
        tvlabel.setText(text);
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

<<<<<<< Updated upstream
    public void showError() {
        tvlabel.setText(R.string.connection_error);
=======
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

    public void showError(){
        if(getContext() != null){
            Toast.makeText(getContext(), getContext().getText(R.string.connection_error), Toast.LENGTH_SHORT).show();
        }
>>>>>>> Stashed changes
    }

    @Override
    public void onImageClicked(String id) {
        RxBus.post(new CallServiceByIdObserver.CallServiceItemPressed(id));
    }

    public void showDetailFor(Image image){
        if(image != null){
            DialogFragmentDetail dialogFragmentDetail = DialogFragmentDetail.newInstance(image);
            dialogFragmentDetail.show(getFragmentManager(), "fragment_dialog");
        } else {
            showError();
        }
    }
}
