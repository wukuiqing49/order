package com.wkq.order.modlue.developer.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wkq.order.R;
import com.wkq.order.databinding.ActivityApiTestBinding;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介:
 */
public class ApiTestActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityApiTestBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_api_test);

        binding.setOnclick(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_top:
                getMoveTop();
                break;

        }

    }

    private void getMoveTop() {
    }
}
