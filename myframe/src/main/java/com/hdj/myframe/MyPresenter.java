package com.hdj.myframe;

public class MyPresenter implements IPresenter{

    private IContract iContract;
    private IModel iModel;

    public MyPresenter(IContract iContract, IModel iModel) {
        this.iContract = iContract;
        this.iModel = iModel;
    }

    @Override
    public void getData(int witchApi, Object... p) {
        iModel.getData(this,witchApi,p);
    }

    @Override
    public void onSuccess(int witchApi, int loadType, Object[] d) {
        iContract.onSuccess(witchApi,loadType,d);
    }

    @Override
    public void onFailed(int witchApi, Throwable throwable) {
        iContract.onFailed(witchApi,throwable);
    }
}
