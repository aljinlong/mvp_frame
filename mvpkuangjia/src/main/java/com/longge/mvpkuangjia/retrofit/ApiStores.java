package com.longge.mvpkuangjia.retrofit;

import com.longge.mvpkuangjia.model.InfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 项目名称：mvplizi
 * 类描述：
 * 创建人：aljinlong@163.com
 * 创建时间：2016/11/7 0007 23:14
 * 修改备注：
 */
public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://www.weather.com.cn/";

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Call<InfoBean> loadDataByRetrofit(@Path("cityId") String cityId);

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<InfoBean> loadDataByRetrofitRxjava(@Path("cityId") String cityId);
}
