package com.chakibtemal.ecarevideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.facebook.react.modules.core.PermissionListener;
import org.jitsi.meet.sdk.JitsiMeetActivityInterface;
import org.jitsi.meet.sdk.JitsiMeetView;
import org.jitsi.meet.sdk.ReactActivityLifecycleCallbacks;
import java.net.MalformedURLException;
import java.net.URL;

public class TestActivity extends FragmentActivity implements JitsiMeetActivityInterface {
    public static final String domainName = "https://ecare.voip.bellatrix.io/";
    private JitsiMeetView view;

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {
        ReactActivityLifecycleCallbacks.onActivityResult(
                this, requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        ReactActivityLifecycleCallbacks.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        URL url = null;

        Intent intent = getIntent();
        String nameRoom = intent.getStringExtra("ROOM_NAME");

        try {
            url = new URL(domainName + nameRoom);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        view = new JitsiMeetView(this);
        view.loadURL(url);
        setContentView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view.dispose();
        view = null;

        ReactActivityLifecycleCallbacks.onHostDestroy(this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        ReactActivityLifecycleCallbacks.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ReactActivityLifecycleCallbacks.onHostResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ReactActivityLifecycleCallbacks.onHostPause(this);
    }

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) { }

    @Override
    public void onRequestPermissionsResult(
            final int requestCode,
            final String[] permissions,
            final int[] grantResults) {
        ReactActivityLifecycleCallbacks.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
