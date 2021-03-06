package com.ebeyonds.myapplication.ui.main.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<Integer> is_logged_in = new MutableLiveData<>();
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    public MutableLiveData<Integer> getIs_logged_in() {
        return is_logged_in;
    }

    public void setIs_logged_in(int is_logged_in) {
        this.is_logged_in.setValue(is_logged_in);
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
