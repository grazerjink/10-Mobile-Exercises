package vn.kjapps.demofoodordering;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SubmitDialog extends AppCompatActivity {
    Button btnOK, btnCancel;
    TextView tvOrder, tvOrder1, tvOrder2, tvOrder3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_submit);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SubmitDialog.this,
                        Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED) {
                    String mess = "Details of the order:\n" + "Size: " + tvOrder.getText().toString() + "\n" + "Tortilla: " + tvOrder1.getText().toString() + "\n" + "Fillings: " + tvOrder2.getText().toString() + "\n"
                            + "Beverage: " + tvOrder3.getText().toString();
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("0905209926", null, mess.toString(), null, null);
                    Toast.makeText(SubmitDialog.this, "Placed order successfully.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    ActivityCompat.requestPermissions(SubmitDialog.this,
                            new String[]{Manifest.permission.SEND_SMS}, 1);
                }
                /*
                String phoneNumber = "0905209926";
                String message = "HUNGRY AGAIN, more tacos ";
                Intent intent = new Intent(Intent.ACTION_SENDTO,
                                            Uri.parse("sms:" + phoneNumber));
                intent.putExtra("sms_body", message);
                startActivity(intent);
                */
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        btnOK = (Button) findViewById(R.id.btnOK);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        tvOrder = (TextView) findViewById(R.id.tvOrder);
        tvOrder1 = (TextView) findViewById(R.id.tvOrder1);
        tvOrder2 = (TextView) findViewById(R.id.tvOrder2);
        tvOrder3 = (TextView) findViewById(R.id.tvOrder3);
        String size = getIntent().getStringExtra("size");
        tvOrder.setText(size);
        String tortilla = getIntent().getStringExtra("tortilla");
        tvOrder1.setText(tortilla);
        String fillings = getIntent().getStringExtra("fillings");
        tvOrder2.setText(fillings);
        String beverage = getIntent().getStringExtra("beverage");
        tvOrder3.setText(beverage);
    }
}
