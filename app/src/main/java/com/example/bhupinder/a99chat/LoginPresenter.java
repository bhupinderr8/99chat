package com.example.bhupinder.a99chat;

import com.example.bhupinder.a99chat.events.LoginEvent;

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser(LoginEvent event);
    void onEventMainThread(LoginEvent event);
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
