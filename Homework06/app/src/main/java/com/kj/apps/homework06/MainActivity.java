package com.kj.apps.homework06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<DiaDiem> dsDiaDiem;
    DiaDiemAdapter adpDiaDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listview);
        dsDiaDiem = DanhSachDiaDiem.danhSach();
        adpDiaDiem = new DiaDiemAdapter(this, R.layout.layout_item_list, dsDiaDiem);
        lv.setAdapter(adpDiaDiem);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaDiem diaDiem = dsDiaDiem.get(position);
                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                i.putExtra("DIADIEM", diaDiem);
                startActivity(i);
            }
        });
    }
}
