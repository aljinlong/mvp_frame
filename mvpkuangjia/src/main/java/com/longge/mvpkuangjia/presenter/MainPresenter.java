package com.longge.mvpkuangjia.presenter;

import com.longge.mvpkuangjia.model.InfoBean;
import com.longge.mvpkuangjia.retrofit.ApiCallback;
import com.longge.mvpkuangjia.view.MainView;

/**
 * 项目名称：mvplizi
 * 类描述：
 * 创建人：aljinlong@163.com
 * 创建时间：2016/11/7 0007 23:19
 * 修改备注：
 */
public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter(MainView view) {
        attachView(view);
    }
    public void loadDataByRetrofitRxjava(String cityId) {
        mvpView.showLoading();
        addSubscription(apiStores.loadDataByRetrofitRxjava(cityId),new ApiCallback<InfoBean>(){


            @Override
            public void onSuccess(InfoBean model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        } );
    }
}
