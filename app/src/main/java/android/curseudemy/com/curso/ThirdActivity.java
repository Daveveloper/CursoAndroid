package android.curseudemy.com.curso;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnCam;
    private ImageButton imgBtnWeb;
    private final int CALL_PHONE_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imgBtnPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imgBtnCam = (ImageButton) findViewById(R.id.imageButtonCamera);
        imgBtnWeb = (ImageButton) findViewById(R.id.imageButtonWeb);

        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_CODE);
                    } else {
                        olderVersions(phoneNumber);
                    }
                }
            }


            private void olderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("Tel: " + phoneNumber));
                if (checkPermissions(Manifest.permission.CALL_PHONE)) {
                    if (ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "Action declined...", Toast.LENGTH_LONG).show();
                }
            }


        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case CALL_PHONE_CODE:

                String permiso = permissions[0];
                int result = grantResults[0];

                if (permiso.equals(Manifest.permission.CALL_PHONE)) {
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("Tel: " + phoneNumber));

                        if (ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                        }else{
                            Toast.makeText(ThirdActivity.this, "Action declined...", Toast.LENGTH_LONG).show();
                        }
                    }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }

    private boolean checkPermissions(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
