package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.just.agentweb.WebViewClient;
import com.wkq.base.adutlis.AdBlocker;
import com.wkq.order.R;
import com.wkq.order.databinding.AdbWebBinding;

import org.adblockplus.libadblockplus.android.settings.AdblockHelper;
import org.adblockplus.libadblockplus.android.settings.AdblockSettings;
import org.adblockplus.libadblockplus.android.settings.AdblockSettingsStorage;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-13
 * <p>
 * 用途:
 */


public class AdbWebActivity extends AppCompatActivity {

    private AdbWebBinding binding;


    public static void startActivity(Context context, String url){
        Intent intent =new Intent(context, AdbWebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    private final WebViewClient webViewClient = new WebViewClient()
    {


        @Override
        public void onPageFinished(final WebView view, final String url)
        {


        }

        @Override
        public void onReceivedError(final WebView view, final int errorCode,
                                    final String description, final String failingUrl){

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            Log.e("url",request.getUrl().toString());
            return super.shouldOverrideUrlLoading(view, request);
        }
    };


    private final WebChromeClient webChromeClient = new WebChromeClient()
    {
        @Override
        public void onProgressChanged(final WebView view, final int newProgress)
        {

        }

        @Override
        public void onPermissionRequest(PermissionRequest request)
        {
            // A very rough example to enable playing DRM video content
            final String[] perms = {PermissionRequest.RESOURCE_PROTECTED_MEDIA_ID};
            request.grant(perms);
        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.adb_web);



        // to show that external WebViewClient is still working
        binding.adb.setWebViewClient(webViewClient);

        // to show that external WebChromeClient is still working
        binding.adb.setWebChromeClient(webChromeClient);

        // to enable local storage for HTML5
        binding.adb.getSettings().setDomStorageEnabled(true);

        binding.adb.loadUrl("https://api.spjx.live/?url=https://m.iqiyi.com/v_19ruq8g5gc.html?vfrmblk=");
    }


    @Override
    protected void onDestroy() {
        binding.adb.dispose(null); // Release it when webView is no longer needed

        super.onDestroy();
    }
}
