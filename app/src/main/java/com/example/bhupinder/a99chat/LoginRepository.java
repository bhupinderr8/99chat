package com.example.bhupinder.a99chat;

public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkAlreadyAuthenticated();
}
