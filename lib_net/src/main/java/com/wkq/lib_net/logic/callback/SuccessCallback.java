package com.wkq.lib_net.logic.callback;

public interface SuccessCallback<T> extends Callback {
    //逻辑操作成功事件响应
    void onSuccess(T data);
}