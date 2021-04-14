package com.laikang.jtcameraview;

import android.graphics.Bitmap;

import java.io.File;

public interface CameraStateListener {
    void onBeforestartPreview();
    void onCameraOpend();
    void onPreviewStart();
    void onPreviewStop();
    void onShutter();
    void onCupture(Bitmap bitmap);
    void onCut(File file);
    void onCameraClosed();
}
