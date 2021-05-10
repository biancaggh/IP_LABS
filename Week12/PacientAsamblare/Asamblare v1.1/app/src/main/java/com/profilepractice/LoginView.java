package com.profilepractice;

import org.json.JSONException;

import java.net.MalformedURLException;

public interface LoginView {
    String getUsername();

    void showUsernameError(int resId);

    String getPassword();

    void showPasswordError(int resId);

    void startActivity() throws MalformedURLException, JSONException;

    void showLoginError(int resId);
}
