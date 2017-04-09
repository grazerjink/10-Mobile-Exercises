package com.kj.apps.homework05;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KJ Mok on 23/03/2017.
 */

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.MyViewHolder> {

    Context mContext;
    int mLayoutRes;
    List<Item> itemList;

    public FeedsAdapter(Context mContext, int mLayoutRes, List<Item> itemList) {
        this.mContext = mContext;
        this.mLayoutRes = mLayoutRes;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Infate
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(mLayoutRes, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(itemList.get(position).getTitle().toString());
        holder.tvDescription.setText(itemList.get(position).getContent().toString());
        holder.tvPubDate.setText(itemList.get(position).getDate().toString());
        holder.tvCreator.setText(itemList.get(position).getCreator().toString());
        Picasso.with(mContext)
                .load(itemList.get(position).getLink_img())
                .resize(90, 90)
                .centerCrop()
                .error(R.drawable.none)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void clear() {
        itemList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Item> feeds) {
        itemList.addAll(feeds);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvPubDate;
        TextView tvDescription;
        TextView tvCreator;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvContent);
            tvPubDate = (TextView) itemView.findViewById(R.id.tvDate);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tvCreator = (TextView) itemView.findViewById(R.id.tvCreator);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            //Tao action tai ViewHolder lun vi chua CardView
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = itemList.get(getAdapterPosition()).getLink().toString();
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent tabsIntent = builder.build();
                    tabsIntent.launchUrl(mContext, Uri.parse(url));
                }
            });
        }
    }
}
