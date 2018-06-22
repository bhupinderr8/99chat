package com.example.bhupinder.a99chat.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bhupinder.a99chat.LoginPresenter;
import com.example.bhupinder.a99chat.LoginPresenterImpl;
import com.example.bhupinder.a99chat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.READ_CONTACTS;

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

