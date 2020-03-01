package com.ebeyonds.myapplication.ui.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ebeyonds.myapplication.data.entity.NewsResponse;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Integer> IS_LOGGED_IN = new MutableLiveData<>();
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();


    public MutableLiveData<Integer> getIS_LOGGED_IN() {
        return IS_LOGGED_IN;
    }

    public void setIS_LOGGED_IN(int IS_LOGGED_IN) {
        this.IS_LOGGED_IN.setValue(IS_LOGGED_IN);
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setValue(username);
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }
}
