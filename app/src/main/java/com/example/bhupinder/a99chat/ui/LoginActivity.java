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
import android.widget.TextView;

import com.example.bhupinder.a99chat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginView{

    // UI references.
    @BindView(R.id.email) private EditText mEmailView;
    @BindView(R.id.password)private EditText mPasswordView;
    @BindView(R.id.login_progress)private View mProgressView;
    @BindView(R.id.email_sign_in_button)private Button mSignInButton;
    @BindView(R.id.email_sign_up_button)private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    public void setInputs(boolean bool){
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
    public void handleSignIn() {

    }

    @Override
    public void handleSignUp() {

    }

    @Override
    public void navigateToMainScreen() {

    }

    @Override
    public void loginError(String error) {

    }

    @Override
    public void newUserSuccess() {

    }

    @Override
    public void newUSerError(String error) {
        mPasswordView.setText("");
        mPasswordView.setError(error);
    }
}

