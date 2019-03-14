package com.chakibtemal.ecarevideo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.charset.Charset;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText room_name;
    private Button go_to_videoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);

        room_name           = (EditText) findViewById(R.id.room_name);
        go_to_videoActivity = (Button) findViewById(R.id.go_to_videoActivity);
        go_to_videoActivity.setOnClickListener(this);

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.CAPTURE_AUDIO_OUTPUT,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.CAMERA,
                Manifest.permission.CHANGE_NETWORK_STATE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE,
        };

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_to_videoActivity:
                String nameRoom;
                nameRoom = room_name.getText().toString();

                if (nameRoom.isEmpty()){
                    nameRoom = getSaltString();
                }
                Intent intent = new Intent(MainActivity.this, VideoCallActivity.class);
                intent.putExtra("ROOM_NAME", nameRoom);
                startActivity(intent);

        }
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
