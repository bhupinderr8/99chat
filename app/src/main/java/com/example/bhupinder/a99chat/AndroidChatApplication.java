package com.example.bhupinder.a99chat;

import android.app.Application;

import com.example.bhupinder.a99chat.lib.GlideImageLoader;
import com.example.bhupinder.a99chat.lib.ImageLoader;
import com.google.firebase.database.FirebaseDatabase;



public class AndroidChatApplication extends Application {
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        setupImageLoader();
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private void setupFirebase(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
