package com.wkq.order;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.media.ImagePicker;
import com.wkq.media.PickerConfig;
import com.wkq.order.databinding.ActivityMainBinding;
import com.wkq.order.databinding.ActivityMainTestBinding;
import com.wkq.order.modlue.main.frame.presenter.MainPresenter;
import com.wkq.order.modlue.main.frame.view.MainView;
import com.wkq.qr.ui.activity.ScanActivity;

public class MainActivity extends MvpBindingActivity<MainView, MainPresenter, ActivityMainTestBinding> {


    //发布文件最大值
    private static final int media_item_max_size = 30 * 1024 * 1024;
    private static final int image_item_max_size = 20 * 1024 * 1024;
    //发布视频最大时长
    //发布视频最大时长
    private static final int media_item_max_time = 15_000;
    private static final int media_item_select_max_time = 10 * 1000;

    public static final int camera_def = 0;
    public static final int camera_bx = 1;

    public static final int ACTION_CAMERA_BX = 20010;
    public static final int REQUEST_CODE_START_MOMENT_CAMERA = 10001;
    public static final int REQUEST_CODE_START_MOMENT_BX_CAMERA = 10002;
    public static final int REQUEST_CODE_START_MOMENT_READ_WRITE = 10003;
    public static final int REQUEST_CODE_START_DOWNLOAD_READ_WRITE = 10012;


    public static void startUpdateActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.tv.setOnClickListener(v -> {
            new ImagePicker.Builder()
                    .setSelectGif(true)
                    .maxNum(9)
                    .needCamera(true)
                    .maxVideoSize(media_item_max_size)
                    .maxImageSize(image_item_max_size)
                    .showTime(true)
                    .maxTime(media_item_select_max_time)
                    .selectMode(PickerConfig.PICKER_IMAGE_VIDEO)
                    .cachePath((Build.VERSION.SDK_INT == Build.VERSION_CODES.Q ? getExternalFilesDir("") : Environment.getExternalStorageDirectory()) + "/strike/file/")
                    .videoTrimPath((Build.VERSION.SDK_INT == Build.VERSION_CODES.Q ? getExternalFilesDir("") : Environment.getExternalStorageDirectory()) + "/strike/file/")
                    .isFriendCircle(true)
                    .builder()
                    //跳转到图片选择页面 activity    请求码            结果码
                    .start(MainActivity.this, 200, PickerConfig.DEFAULT_RESULT_CODE);
            getPresenter().getData(this);
        });

        binding.tvScan.setOnClickListener(v -> {

            ScanActivity.startScanActivity(this);
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_test;
    }
}
