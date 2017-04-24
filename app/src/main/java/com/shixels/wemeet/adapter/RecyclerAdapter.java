package com.shixels.wemeet.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shixels.wemeet.R;
import com.shixels.wemeet.helper.UIHelper;
import com.shixels.wemeet.model.Item;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerViewAdapter<RecyclerAdapter.ItemViewHolder> {

    private final LayoutInflater layoutInflater;

    public Context context;

    private ItemViewHolder holder;

    public RecyclerAdapter(final Context context, ArrayList<Item> items) {
        super();

        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;

    }


    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.list_item, parent, false);

        return new ItemViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final Item item) {
        holder.bindData(item);
        String id = "";
        holder.setTag(id);
        this.holder = holder;
        
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_company_name) TextView companyNameTextView;
        @Bind(R.id.text_share_type) TextView shareTypeTextView;
        @Bind(R.id.card_view) CardView cardView;
        @Bind(R.id.text_time) TextView timeTextView;
        @Bind(R.id.text_description) TextView descriptionTextView;
        @Bind(R.id.text_count) TextView countTextView;
        @Bind(R.id.text_likes) TextView likesCountTextView;
         @Bind(R.id.text_share) TextView sharesCountTextView;
         @Bind(R.id.text_comment) TextView commentsCountTextView;
         @Bind(R.id.image_user) ImageView userImage;
         @Bind(R.id.btn_like) ImageView imageLike;
         @Bind(R.id.btn_comment) ImageView imageComment;
         @Bind(R.id.btn_share) ImageView imageShare;
         @Bind(R.id.info_layout)  RelativeLayout largeImageLayout;

         @Bind(R.id.layout_like) LinearLayout likeLayout;
         @Bind(R.id.layout_share) LinearLayout shareLayout;
         @Bind(R.id.layout_comment) LinearLayout commentLayout;

        Context context;

        View view;

        public ItemViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final Item item) {
            companyNameTextView.setText( item.getCompanyNameString());
            shareTypeTextView.setText( item.getShareTypeString());
            timeTextView.setText( item.getTimeString());
            descriptionTextView.setText( item.getDescriptionString());
            countTextView.setText( item.getCountString());
            likesCountTextView.setText( item.getLikesCountString());
            sharesCountTextView.setText( item.getSharesCountString());
            commentsCountTextView.setText( item.getCommentsCountString());

            Bitmap bitmap = BitmapFactory.decodeResource( context.getResources(), item.getImageId());

            userImage.setImageBitmap(UIHelper.getCircularBitmapWithWhiteBorder(bitmap, 3));
            //userImage.setImageDrawable(context.getResources().getDrawable( item.getImageId()));

            largeImageLayout.setBackground(context.getResources().getDrawable( item.getImageId()));

            likeLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String count = likesCountTextView.getText().toString().trim();
                    String newCount = String.valueOf(Integer.parseInt(count) + 1);
                    likesCountTextView.setText(newCount);
                    likesCountTextView.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    imageLike.setImageBitmap( BitmapFactory.decodeResource( context.getResources(), R.mipmap.ic_heart3));

                }
            });commentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });shareLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });
        }

        public void setTag(String string){
            this.view.setTag( string);
        }
        public String getTag(){
           return this.view.getTag().toString();
        }

    }

}