package com.wkq.order.modlue.demo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityDeomBinding;
import com.wkq.order.modlue.demo.ui.adapter.CustomBehaviorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-11
 * <p>
 * 用途:
 */


public class DemoActivity extends AppCompatActivity {

    private ActivityDeomBinding binding;

    public static void startActivity(Context context) {

        Intent intent = new Intent();
        intent.setClass(context, DemoActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_deom);

        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.addTranslucentView(this, 0);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.binding.rvContent.setLayoutManager(new LinearLayoutManager(this));
        CustomBehaviorAdapter customBehaviorAdapter = new CustomBehaviorAdapter(this);
        this.binding.rvContent.setAdapter(customBehaviorAdapter);

        List<String> listString = new ArrayList<>();

        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");
        listString.add("测试");

        customBehaviorAdapter.addItems(listString);
    }
}
