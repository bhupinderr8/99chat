package com.example.bhupinder.a99chat.login;

import com.example.bhupinder.a99chat.login.events.LoginEvent;


public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void onEventMainThread(LoginEvent event);
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
