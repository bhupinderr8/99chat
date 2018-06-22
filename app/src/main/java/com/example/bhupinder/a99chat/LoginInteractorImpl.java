package com.example.bhupinder.a99chat;

public class LoginInteractorImpl implements LoginInteractor{
    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        this.loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void checkAlreadyAuthenticated() {
        loginRepository.checkAlreadyAuthenticated();
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }

    @Override
    public void doSignUp(String email, String password) {
        loginRepository.signUp(email, password);
    }
}
