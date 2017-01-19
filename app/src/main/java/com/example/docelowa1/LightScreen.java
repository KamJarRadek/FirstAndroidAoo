package com.example.docelowa1;


import android.content.pm.PackageManager;
//import android.graphics.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
//import java.security.Policy;

public class LightScreen extends AppCompatActivity {
    Button goBack2, lampButton;
    private boolean isOn = false;
    Camera cam;
    Parameters parameters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light);
    }

    public void onLampBackClick(View view) {
        flashLightOff(view);
        finish();
    }

    public void onlightClick(View view) {


        if(!isOn){
            flashLightOn(view);
        }else flashLightOff(view);
    }
    public void flashLightOn(View view) {

        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam = Camera.open();
                parameters = cam.getParameters();
                parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(parameters);
                cam.startPreview();
                isOn = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Exception flashLightOn()",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void flashLightOff(View view) {
        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam.stopPreview();
                cam.release();
                cam = null;
                isOn = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Exception flashLightOff",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
