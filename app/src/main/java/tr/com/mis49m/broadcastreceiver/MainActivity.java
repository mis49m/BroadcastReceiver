package tr.com.mis49m.broadcastreceiver;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    private final String TAG = "MainActivity";
    private int REQUEST_SMS_RECEIVE = 10;

    private Button btnSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendBroadcast = (Button) findViewById(R.id.btnSendBroadcast);
        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("msg", "hi");
                intent.setAction("com.mis49m.MyBroadcast");
                sendBroadcast(intent);
            }
        });

        //-- Check for version
        if (Build.VERSION.SDK_INT >= 23) {

            //-- Check if permisson granted already
            boolean permissionGranted = ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
            //-- If permission is not granted in the current state
            if(permissionGranted==false){
                //-- Show popup to user for permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        REQUEST_SMS_RECEIVE);

            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_RECEIVE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "Permission granted");
            }else{
                Log.i(TAG, "Permission denied");
            }
        }
    }


}
