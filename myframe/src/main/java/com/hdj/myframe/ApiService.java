package com.hdj.myframe;



import com.hdj.data.TestInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    String url = "http://static.owspace.com/";

    @GET(".")
   Observable<TestInfo> getInfoData(@QueryMap Map<String, Object> params, @Query("page_id") int pageId);

}
