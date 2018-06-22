package com.example.bhupinder.a99chat.login;

import com.example.bhupinder.a99chat.login.events.LoginEvent;
import com.example.bhupinder.a99chat.login.ui.LoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginPresenterImpl implements LoginPresenter {
    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.eventBus = EventBus.getDefault();
        this.loginInteractor = new LoginInteractorImpl();
    }

    private void onSignUpSuccess(){
        if(loginView != null){
            loginView.newUserSuccess();
        }
    }

    private void onSignInSuccess(){
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
    }

    private void onSignInError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUSerError(error);
        }
    }

    private void onFailedToRecoverSession(){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
        }
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkAlreadyAuthenticated();
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;

            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;

            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;

            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;

            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
        }
    }

    @Override
    public void validateLogin(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email, password);
    }
}
