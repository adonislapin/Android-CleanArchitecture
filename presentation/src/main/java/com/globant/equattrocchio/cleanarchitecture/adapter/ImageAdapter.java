package com.globant.equattrocchio.cleanarchitecture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.IClickImage;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Result result;
    private Context context;
    private IClickImage iClickImage;

    public ImageAdapter(Context context, Result result){
        this.result = result;
        this.context = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);

        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Image imgTemp = result.getImages().get(position);

        holder.itemView.setTag(imgTemp.getId());
        holder.id.setText("ID: " + imgTemp.getId());
        Glide.with(context).load(imgTemp.getUrl()).into(holder.img);
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setiClickImage(IClickImage iClickImage) {
        this.iClickImage = iClickImage;
    }

    @Override
    public int getItemCount() {
        return result.getImages().size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public ImageView img;

        public ImageViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.txt_id);
            img = view.findViewById(R.id.imgv_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = "" + (int)v.getTag();
                    iClickImage.onImageClicked(id);
                }
            });
        }
    }
}
