package vn.kjapps.demoflashlight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btnWhite, btnRed, btnBlue, btnYellow, btnPurple;
    private ImageView imgScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapViews();
        addEvents();
    }

    private void addEvents() {
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgScreen.setBackgroundResource(R.drawable.blue);
            }
        });

        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgScreen.setBackgroundResource(R.drawable.purple);
            }
        });

        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgScreen.setBackgroundResource(R.drawable.yellow);
            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgScreen.setBackgroundResource(R.drawable.red);
            }
        });
        btnWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgScreen.setBackgroundResource(R.drawable.white);
            }
        });
    }

    private void mapViews() {
        btnWhite = (Button) findViewById(R.id.btnWhite);
        btnRed = (Button) findViewById(R.id.btnRed);
        btnBlue = (Button) findViewById(R.id.btnBlue);
        btnYellow = (Button) findViewById(R.id.btnYellow);
        btnPurple = (Button) findViewById(R.id.btnPurple);
        imgScreen = (ImageView) findViewById(R.id.imgScreen);
    }
}
