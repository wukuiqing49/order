package com.wkq.order.modlue.web.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityPlayHelpBinding;
import com.wkq.order.modlue.main.modle.PlayHelpInfo;
import com.wkq.order.modlue.main.ui.adapter.PlayHelpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-23
 * <p>
 * 用途:
 */


public class PlayHelperActivity extends AppCompatActivity {

    private ActivityPlayHelpBinding binding;

    List<PlayHelpInfo> steps=new ArrayList<>();

    public static void startPlayHelperActivity(Context context) {
        Intent intent = new Intent(context, PlayHelperActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_play_help);
        initData();
        initView();

    }


    private void initData() {
        steps.add(new PlayHelpInfo(R.mipmap.play_1,"第1步"));
        steps.add(new PlayHelpInfo(R.mipmap.play_2,"第2步"));
        steps.add(new PlayHelpInfo(R.mipmap.play_3,"第3步"));
        steps.add(new PlayHelpInfo(R.mipmap.play_4,"第4步"));
        steps.add(new PlayHelpInfo(R.mipmap.play_5,"第5步"));
    }

    private void initView() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.addTranslucentView(this, 0);
//        StatusBarUtil.setStatusBarWrite(this);
//        StatusBarUtil.setColor(this, this.getResources().getColor(R.color.color_f4f4f4), 0);
        binding.rvPlayHelp.setLayoutManager(new LinearLayoutManager(this));
        PlayHelpAdapter mAdapter=new PlayHelpAdapter(this);
        binding.rvPlayHelp.setAdapter(mAdapter);
        mAdapter.addItems(steps);

    }
}
