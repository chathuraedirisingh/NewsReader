package com.ebeyonds.myapplication.ui.main.topheadlines;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ebeyonds.myapplication.data.entity.NewsResponse;

public class TopHeadlinesViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> newsResponse = new MutableLiveData<>();

    public void setNewsResponse(NewsResponse body) {
        this.newsResponse.setValue(body);
    }

    public MutableLiveData<NewsResponse> getNewsResponse() {
        return newsResponse;
    }
}
