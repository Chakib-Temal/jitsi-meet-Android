package com.chakibtemal.ecarevideo;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.react.modules.core.PermissionListener;

import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetView;

import java.net.MalformedURLException;
import java.net.URL;

public class VideoCallActivity extends FragmentActivity implements JitsiMeetActivityInterface {
    private JitsiMeetView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new JitsiMeetView(this);
        URL url = null;

        Intent intent = getIntent();
        String nameRoom = intent.getStringExtra("ROOM_NAME");

        try {
            url = new URL("https://ecare.voip.bellatrix.io/"+ nameRoom);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        view.loadURL(url);
        setContentView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        view.dispose();
        view = null;

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {

    }


}
