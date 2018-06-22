package com.example.bhupinder.a99chat.login.ui;

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
    void handleSignIn();
    void handleSignUp();
    void navigateToMainScreen();
    void loginError(String error);
    void newUserSuccess();
    void newUSerError(String error);

}
