package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.constants.DetailKeys;
import com.globant.equattrocchio.cleanarchitecture.fragments.DialogFragmentDetail;
import com.globant.equattrocchio.cleanarchitecture.utils.Utils;
import com.globant.equattrocchio.data.response.Image;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogFragmentDetailView extends FragmentView<DialogFragmentDetail, Void, Void> {


    @BindView(R.id.img) ImageView imageView;
    @BindView(R.id.txt_details) TextView txtDetails;
    private Bundle dialogBundle;

    public DialogFragmentDetailView(DialogFragmentDetail fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init(){
        dialogBundle = getFragment().getArguments();
        setDialogContent();
    }

    public void setDialogContent(){
        if(dialogBundle != null){
            Image image = dialogBundle.getParcelable(DetailKeys.DITAIL_IMAGE_KEY);
            txtDetails.setText(Utils.formatFullInformationOf(image, getContext()));
            Glide.with(getContext()).load(image.getUrl()).into(imageView);
        }
    }

    public void dismissDialog(){
        getFragment().dismiss();
    }

    @OnClick(R.id.dialog_neutral_button)
    public void onNeutralButtonClick(){
        dismissDialog();
    }

}
