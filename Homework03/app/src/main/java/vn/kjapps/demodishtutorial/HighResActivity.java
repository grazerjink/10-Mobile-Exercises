package vn.kjapps.demodishtutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class HighResActivity extends AppCompatActivity {
    ImageView imgHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_res);
        imgHigh = (ImageView) findViewById(R.id.imgHigh);
        int imgRes = getIntent().getIntExtra("HIGH_RES", 0);
        Picasso.with(this).load(imgRes).fit().into(imgHigh);
    }
}
