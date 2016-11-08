package com.longge.mvpkuangjia.view;

import android.os.Bundle;

import com.longge.mvpkuangjia.presenter.BasePresenter;

/**
 * 项目名称：mvplizi
 * 类描述：
 * 创建人：aljinlong@163.com
 * 创建时间：2016/11/7 0007 23:38
 * 修改备注：
 */
public abstract class MvpActivity <P extends BasePresenter> extends BaseActivity{
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }


    public void hideLoading() {
        dismissProgressDialog();
    }
}
