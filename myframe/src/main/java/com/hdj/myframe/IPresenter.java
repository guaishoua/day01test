package com.hdj.myframe;

public interface IPresenter<P> extends IContract {
    void getData(int witchApi,P... p);
}
