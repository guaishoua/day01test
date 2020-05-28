package com.hdj.myframe;
/**
 * created by 胡大江 on 2020/5/28
 * */
public interface IModel<T> {
    void getData(IPresenter presenter,int witchApi,T... params);
}
