package com.kj.apps.homework06;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KJ Mok on 08/04/2017.
 */

public class DiaDiemAdapter extends ArrayAdapter {

    Context mContext;
    int mResource;
    List<DiaDiem> dsDiaDiem;

    public DiaDiemAdapter(Context context, int resource, List<DiaDiem> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        dsDiaDiem = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTen = (TextView) convertView.findViewById(R.id.tvTen);
            viewHolder.tvChiTiet = (TextView) convertView.findViewById(R.id.tvChiTiet);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTen.setText(dsDiaDiem.get(position).getTen());
        viewHolder.tvChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(dsDiaDiem.get(position).getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvTen;
        TextView tvChiTiet;
    }
}

