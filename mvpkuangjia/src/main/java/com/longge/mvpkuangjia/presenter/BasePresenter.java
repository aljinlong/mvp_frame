package com.longge.mvpkuangjia.presenter;

import com.longge.mvpkuangjia.retrofit.ApiStores;
import com.longge.mvpkuangjia.retrofit.AppClient;
import com.longge.mvpkuangjia.view.BaseView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 项目名称：mvplizi
 * 类描述：
 * 创建人：aljinlong@163.com
 * 创建时间：2016/11/7 0007 22:36
 * 修改备注：
 */
public class BasePresenter<V extends BaseView> {
    public V mvpView;
    protected ApiStores apiStores; //接口
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiStores = AppClient.retrofit().create(ApiStores.class);
    }
    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }
    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

}
