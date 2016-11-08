package com.longge.mvpkuangjia.retrofit;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 项目名称：mvplizi
 * 类描述：
 * 创建人：aljinlong@163.com
 * 创建时间：2016/11/7 0007 23:42
 * 修改备注：
 */
public abstract class RetrofitCallback<M> implements retrofit2.Callback<M> {
    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String msg);

    public abstract void onThrowable(Throwable t);

    public abstract void onFinish();
    @Override
    public void onResponse(Call<M> call, Response<M> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            onFailure(response.code(), response.errorBody().toString());
        }
        onFinish();
    }



    @Override
    public void onFailure(Call<M> call, Throwable t) {
        onThrowable(t);
        onFinish();
    }


}
