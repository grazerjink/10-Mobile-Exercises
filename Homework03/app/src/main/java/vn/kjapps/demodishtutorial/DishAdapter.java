package vn.kjapps.demodishtutorial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KJ Mok on 26/02/2017.
 */

public class DishAdapter extends ArrayAdapter<Dish> {

    List<Dish> lishDish;
    Context mContext;
    int mResource;

    public DishAdapter(Context context, int resource, List<Dish> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        lishDish = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.imgPicture = (ImageView) convertView.findViewById(R.id.imgPicture);
            viewHolder.imgHighRes = (ImageView) convertView.findViewById(R.id.imgHighRes);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(lishDish.get(position).getName());
        Picasso.with(mContext).load(lishDish.get(position).getImage()).fit().noFade().into(viewHolder.imgPicture);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,TutorialActivity.class);
                i.putExtra("TUTORIAL",lishDish.get(position).getImage_tutorial());
                mContext.startActivity(i);
            }
        });

        viewHolder.imgHighRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,HighResActivity.class);
                i.putExtra("HIGH_RES",lishDish.get(position).getHigh_image());
                mContext.startActivity(i);
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imgPicture;
        TextView tvTitle;
        ImageView imgHighRes;
        LinearLayout linearLayout;
    }
}
