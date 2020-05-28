package com.hdj.myframe;

import com.hdj.data.TestInfo;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyModel implements IModel {
    @Override
    public void getData(final IPresenter presenter, final int witchApi, Object[] params) {
        final int loadType = (int) params[0];
        Map param = (Map) params[1];
        int pageId = (int) params[2];
        ApiService apiService = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
        Observable infoData = apiService.getInfoData(param, pageId);
        infoData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestInfo>() {
                    @Override
                    public void accept(TestInfo testInfo) throws Exception {
                        presenter.onSuccess(witchApi,loadType,testInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        presenter.onFailed(witchApi,throwable);
                    }
                });
    }
}
