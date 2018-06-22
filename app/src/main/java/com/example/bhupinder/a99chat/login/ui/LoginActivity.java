package com.example.bhupinder.a99chat.login.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bhupinder.a99chat.ContactListActivity;
import com.example.bhupinder.a99chat.login.LoginPresenter;
import com.example.bhupinder.a99chat.login.LoginPresenterImpl;
import com.example.bhupinder.a99chat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private LoginPresenter loginPresenter;
    // UI references.
    @BindView(R.id.email) AutoCompleteTextView mEmailView;
    @BindView(R.id.password) EditText mPasswordView;
    @BindView(R.id.login_progress) ProgressBar mProgressView;
    @BindView(R.id.email_sign_in_button) Button mSignInButton;
    @BindView(R.id.email_sign_up_button) Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticatedUser();

    }

    private void setInputs(boolean bool){
        mEmailView.setEnabled(bool);
        mPasswordView.setEnabled(bool);
        mSignInButton.setEnabled(bool);
        mSignUpButton.setEnabled(bool);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        mProgressView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    @OnClick(R.id.email_sign_in_button)
    public void handleSignIn() {
        loginPresenter.validateLogin(mEmailView.getText().toString(),
                                    mPasswordView.getText().toString());

    }

    @Override
    @OnClick(R.id.email_sign_up_button)
    public void handleSignUp() {
        Toast toast = Toast.makeText(getApplicationContext(), "YESS !", Toast.LENGTH_LONG);
        toast.show();
        loginPresenter.registerNewUser(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, ContactListActivity.class));
        Toast toast = Toast.makeText(getApplicationContext(), "YESS !", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void loginError(String error) {
        mPasswordView.setText("");
        mPasswordView.setError(error);
    }

    @Override
    public void newUserSuccess() {
        Toast toast = Toast.makeText(getApplicationContext(), "ID Registered", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void newUSerError(String error) {
        mPasswordView.setText("");
        mPasswordView.setError(error);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }
}

