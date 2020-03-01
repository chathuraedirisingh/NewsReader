package com.ebeyonds.myapplication.ui.main.favorite;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ebeyonds.myapplication.data.entity.Article;
import com.ebeyonds.myapplication.data.entity.NewsResponse;

public class FavoriteViewModel extends ViewModel {

    private MutableLiveData<String> query = new MutableLiveData<>();
    private MutableLiveData<NewsResponse> newsResponse = new MutableLiveData<>();

    public void setNewsResponse(NewsResponse body) {
        this.newsResponse.setValue(body);
    }

    public MutableLiveData<NewsResponse> getNewsResponse() {
        return newsResponse;
    }

    public MutableLiveData<String> getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query.setValue(query);
    }


}
