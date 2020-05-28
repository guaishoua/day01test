package com.hdj.myframe;

public interface IContract<D> {
    void onSuccess(int witchApi,int loadType,D... d);
    void onFailed(int witchApi,Throwable throwable);
}
