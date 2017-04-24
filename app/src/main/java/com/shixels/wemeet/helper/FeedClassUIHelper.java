package com.shixels.wemeet.helper;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shixels.wemeet.R;

public class FeedClassUIHelper {
     View view;
    private  RelativeLayout infoLayout;
    private  LinearLayout likeLayout, commentLayout, shareLayout;
    private  TextView timeTextView, descriptionTextView, countTextView,
            likesCountText,  sharesCountText,  commentsCountText,
            shareTypeTextView, companyNameTextView;
    private  ImageView userImage;

    public FeedClassUIHelper(View view){
        this.view = view;
        loadUIElements();
    }

    public  void loadUIElements(){
        userImage = (ImageView) view.findViewById(R.id.image_user);
        shareTypeTextView = (TextView) view.findViewById( R.id.text_share_type);
        companyNameTextView = (TextView) view.findViewById( R.id.text_company_name);

        timeTextView  = (TextView)view.findViewById(R.id.text_time);
        descriptionTextView  = (TextView)view.findViewById(R.id.text_description);
        countTextView  = (TextView)view.findViewById(R.id.text_count);
        infoLayout = (RelativeLayout) view.findViewById(R.id.info_layout); //reference is made to this layout so as to make the image background dynamic

        //clickable layouts for likes, comments and shares
        likeLayout = (LinearLayout) view.findViewById(R.id.layout_like);
        shareLayout = (LinearLayout) view.findViewById(R.id.layout_share);
        commentLayout = (LinearLayout) view.findViewById(R.id.layout_comment);

        //dynamic textviews for likes, comments and shares counts
        likesCountText = (TextView) view.findViewById(R.id.text_likes);
        sharesCountText = (TextView) view.findViewById(R.id.text_share);
        commentsCountText = (TextView) view.findViewById(R.id.text_comment);

    }

    public TextView getCompanyNameTextView() {
        return companyNameTextView;
    }

    public ImageView getUserImage() {
        return userImage;
    }

    public TextView getShareTypeTextView() {
        return shareTypeTextView;
    }

    public RelativeLayout getInfoLayout() {
        return infoLayout;
    }

    public TextView getTimeTextView() {
        return timeTextView;
    }

    public TextView getDescriptionTextView() {
        return descriptionTextView;
    }

    public TextView getCountTextView() {
        return countTextView;
    }

    public LinearLayout getLikeLayout() {
        return likeLayout;
    }

    public LinearLayout getCommentLayout() {
        return commentLayout;
    }

    public LinearLayout getShareLayout() {
        return shareLayout;
    }

    public TextView getLikesCountText() {
        return likesCountText;
    }

    public TextView getSharesCountText() {
        return sharesCountText;
    }

    public TextView getCommentsCountText() {
        return commentsCountText;
    }
}
