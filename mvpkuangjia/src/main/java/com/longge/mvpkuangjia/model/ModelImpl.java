package com.longge.mvpkuangjia.model;


/**
* Created by longge125521 on 2016/11/07
*/

public class ModelImpl implements Model{
    private InfoBean infoBean = new InfoBean();
    @Override
    public InfoBean getInfo() {
        return infoBean;
    }

    @Override
    public void setInfo(InfoBean info) {
    }
}