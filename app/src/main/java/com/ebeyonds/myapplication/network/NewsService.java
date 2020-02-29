package com.ebeyonds.myapplication.network;

import com.ebeyonds.myapplication.BuildConfig;
import com.ebeyonds.myapplication.data.entity.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("top-headlines?pageSize=50&country=us&apiKey=" + BuildConfig.NEWS_API_KEY)
    Call<NewsResponse> getAllNews();

    @GET("everything?language=en&sortBy=popularity&pageSize=50&apiKey=" + BuildConfig.NEWS_API_KEY)
    Call<NewsResponse> getByFavorite(@Query("q") String q);

}
