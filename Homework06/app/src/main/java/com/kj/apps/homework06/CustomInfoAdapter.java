package com.kj.apps.homework06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by KJ Mok on 08/04/2017.
 */

public class CustomInfoAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    DiaDiem diaDiem;

    public CustomInfoAdapter(Context context, DiaDiem diaDiem) {
        this.context = context;
        this.diaDiem = diaDiem;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.layout_map_item, null);
        TextView tvTen = (TextView) v.findViewById(R.id.tvTen);

        tvTen.setText(diaDiem.getTen());
        return v;
    }
}
