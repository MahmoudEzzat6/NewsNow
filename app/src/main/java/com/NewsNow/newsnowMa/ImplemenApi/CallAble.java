package com.NewsNow.newsnowMa.ImplemenApi;

import com.NewsNow.newsnowMa.ApiService.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallAble {
    //https://newsapi.org/v2/top-headlines?country=eg&apiKey=51ae57c82ada474cb2376f3c6f3d43de
   @GET("/v2/top-headlines?country=eg&apiKey=51ae57c82ada474cb2376f3c6f3d43de")
    Call<NewsModel> getData(@Query("category") String cat);

}
