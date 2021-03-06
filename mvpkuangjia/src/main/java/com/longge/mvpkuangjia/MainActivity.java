package com.longge.mvpkuangjia;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.longge.mvpkuangjia.model.InfoBean;
import com.longge.mvpkuangjia.presenter.MainPresenter;
import com.longge.mvpkuangjia.retrofit.ApiCallback;
import com.longge.mvpkuangjia.retrofit.RetrofitCallback;
import com.longge.mvpkuangjia.view.MainView;
import com.longge.mvpkuangjia.view.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * aljinlong@163.com
 */
public class MainActivity extends MvpActivity<MainPresenter> implements MainView {
    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBarAsHome("MVP+Retrofit+Rxjava");

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void getDataSuccess(InfoBean model) {
        //接口成功回调
        dataSuccess(model);
    }

    @Override
    public void getDataFail(String msg) {
        toastShow("网络不给力");

    }


    @OnClick({R.id.button0, R.id.button1, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                loadDataByRetrofit();
                break;
            case R.id.button1:
                loadDataByRetrofitRxjava();
                break;
            case R.id.button2:
                //请求接口
                mvpPresenter.loadDataByRetrofitRxjava("101310222");
                break;
        }
    }

    private void loadDataByRetrofit() {
        showProgressDialog();
        Call<InfoBean> call = apiStores.loadDataByRetrofit("101190201");
        call.enqueue(new RetrofitCallback<InfoBean>() {
            @Override
            public void onSuccess(InfoBean model) {
                dataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                toastShow(msg);
            }

            @Override
            public void onThrowable(Throwable t) {
                toastShow(t.getMessage());
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
        addCalls(call);
    }

    //全国+国外主要城市代码http://mobile.weather.com.cn/js/citylist.xml
    private void loadDataByRetrofitRxjava() {
        showProgressDialog();
        addSubscription(apiStores.loadDataByRetrofitRxjava("101220602"),
                new ApiCallback<InfoBean>() {
                    @Override
                    public void onSuccess(InfoBean model) {
                        dataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        toastShow(msg);

                    }

                    @Override
                    public void onFinish() {
                        dismissProgressDialog();
                    }
                });
    }

    private void dataSuccess(InfoBean model) {
        InfoBean.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
//        text.setText(showData);
        toastShow(showData);

    }
}
