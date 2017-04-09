package vn.kjapps.demodishtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class TutorialActivity extends AppCompatActivity {
    ImageView imgTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        imgTutor = (ImageView) findViewById(R.id.imgTutor);
        Picasso.with(this).load(getIntent().getIntExtra("TUTORIAL", 0)).into(imgTutor);
    }
}
