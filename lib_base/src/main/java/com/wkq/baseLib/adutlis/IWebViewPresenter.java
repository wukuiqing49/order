package com.wkq.baseLib.adutlis;


import android.webkit.WebView;
import android.widget.PopupWindow;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public interface IWebViewPresenter {
    void validateUrl(String url);

    void onBackPressed(PopupWindow menu, WebView webView);

    void onReceivedTitle(String title, String url);

    void onClick(int resId, String url, PopupWindow popupWindow);

    void setEnabledGoBackAndGoFoward(boolean enabledGoBack, boolean enabledGoFoward);

    void onLongClick(WebView.HitTestResult result);

    void onProgressChanged(SwipeRefreshLayout swipeRefreshLayout, int progress);
}
