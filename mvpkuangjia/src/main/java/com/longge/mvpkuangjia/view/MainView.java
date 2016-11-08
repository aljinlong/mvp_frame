package com.longge.mvpkuangjia.view;

import com.longge.mvpkuangjia.model.InfoBean;

/**
 * 项目名称：mvplizi
 * 类描述：
 * 创建人：aljinlong@163.com
 * 创建时间：2016/11/7 0007 22:47
 * 修改备注：
 */
public interface MainView extends BaseView {
    void getDataSuccess(InfoBean model);
    void getDataFail(String msg);
}
