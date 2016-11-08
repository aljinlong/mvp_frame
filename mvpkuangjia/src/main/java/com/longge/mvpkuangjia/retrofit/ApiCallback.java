package com.longge.mvpkuangjia.retrofit;

import com.longge.mvpkuangjia.utils.LogUtil;

import retrofit2.adapter.rxjava.HttpException;

/**
 * 项目名称：mvplizi
 * 类描述：
 * 创建人：aljinlong@163.com
 * 创建时间：2016/11/7 0007 23:21
 * 修改备注：
 */
public abstract class ApiCallback<T> extends rx.Subscriber<T> {

    public abstract void onSuccess(T model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            LogUtil.d("code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(T model) {
        onSuccess(model);

    }
}
