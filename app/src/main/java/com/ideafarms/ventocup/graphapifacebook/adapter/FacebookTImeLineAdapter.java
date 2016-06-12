package com.ideafarms.ventocup.graphapifacebook.adapter;

import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ideafarms.ventocup.graphapifacebook.R;
import com.ideafarms.ventocup.graphapifacebook.model.FacebookPage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nick on 6/12/2016.
 */
public class FacebookTImeLineAdapter extends RecyclerView.Adapter<FacebookTImeLineAdapter.ViewHolder> {
    private static final String TAG = FacebookTImeLineAdapter.class.getSimpleName();
    List<FacebookPage> _mFacebookPost;
    Context mContext;

    public FacebookTImeLineAdapter(List<FacebookPage> _mFacebookPost, Context mContext) {
        this._mFacebookPost = _mFacebookPost;
        this.mContext = mContext;
    }

    @Override
    public FacebookTImeLineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.facebook_post_holder, parent, false);
        ViewHolder vHolder = new ViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        FacebookPage page = new FacebookPage();
        page = _mFacebookPost.get(position);
        if (page.getMessage() != null) {
            holder.mTxtMessage.setText(page.getMessage());
        } else {
            holder.mTxtMessage.setVisibility(View.GONE);
        }

        if (page.getPicture() != null) {
            Log.e(TAG, page.getPicture());
            Picasso.with(mContext)
                    .load(page.getFull_picture())
                    .placeholder(R.drawable.ic_insert_photo_black_24dp)
                    .into(holder.mImgPhoto);
        } else {
            holder.mImgPhoto.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return _mFacebookPost.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtMessage;
        public ImageView mImgPhoto;


        public ViewHolder(View itemView) {
            super(itemView);

            mTxtMessage = (TextView) itemView.findViewById(R.id.txtMessage);
            mImgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);
        }
    }
}
