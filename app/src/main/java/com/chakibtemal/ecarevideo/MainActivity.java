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

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {
    public static final int sizeOfRandomNameRoom = 8;
    private EditText        room_name;
    private Button          go_to_videoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);

        room_name           = (EditText) findViewById(R.id.room_name);
        go_to_videoActivity = (Button) findViewById(R.id.go_to_videoActivity);
        go_to_videoActivity.setOnClickListener(this);

        checkPermissions();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_to_videoActivity:
                String nameRoom;
                nameRoom = room_name.getText().toString();
                if (nameRoom.isEmpty()){
                    nameRoom = getRandomString();
                }
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra("ROOM_NAME", nameRoom);
                startActivity(intent);
        }
    }

    public void checkPermissions(){
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

    public static String getRandomString() {
        String possibilities = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < sizeOfRandomNameRoom) { // length of the random string.
            int index = (int) (rnd.nextFloat() * possibilities.length());
            salt.append(possibilities.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
