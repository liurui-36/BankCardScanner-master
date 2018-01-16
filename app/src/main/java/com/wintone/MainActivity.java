package com.wintone;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wintone.smartvision_bankCard.ScanCamera;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(this, new String[]{
                Manifest.permission.CAMERA
        }, 999);
    }

    public void gotoScan(View view) {
        Intent intentTack = new Intent(this, ScanCamera.class);
        startActivityForResult(intentTack, 666);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == RESULT_OK) {
            int[] picR = data.getIntArrayExtra("PicR");
            char[] StringR = data.getCharArrayExtra("StringR");
            Log.i("TAG", String.valueOf(StringR));
            Bitmap bitmap = Bitmap.createBitmap(picR, 400, 80, Bitmap.Config.ARGB_8888);
//            imageView.setImageBitmap(bitmap);
        }
    }

    public static void checkPermission(Activity context, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            byte status = 0;
            String[] var4 = permissions;
            int var5 = permissions.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                String s = var4[var6];
                int i = ContextCompat.checkSelfPermission(context, s);
                if (i == -1) {
                    status = -1;
                    break;
                }
            }

            if (status != 0) {
                ActivityCompat.requestPermissions(context, permissions, requestCode);
            }
        }

    }
}
