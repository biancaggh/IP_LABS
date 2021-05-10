package com.profilepractice;

import org.json.JSONException;

import java.net.MalformedURLException;

public class LoginPresenter {

    private LoginService service;
    private LoginView view;
    public LoginPresenter(LoginView view, LoginService service) {
        this.view = view;
        this.service = service;
    }

    public void userLogin() throws MalformedURLException, JSONException {
        String username = view.getUsername();
        if(username.isEmpty()) {
            view.showUsernameError(R.string.username_error);
            return;
        }
        String password = view.getPassword();
        if(password.isEmpty()){
            view.showPasswordError(R.string.password_error);
            return;
        }
        boolean loginSucceeded = (boolean) service.login(username, password);
        if(loginSucceeded) {
            view.startActivity();
            return;
        }
        view.showLoginError(R.string.login_failed);
    }
}
