package com.example.bhupinder.a99chat.login;

public interface LoginInteractor {
    void checkAlreadyAuthenticated();
    void doSignIn(String email, String password);
    void doSignUp(String email, String password);
}
