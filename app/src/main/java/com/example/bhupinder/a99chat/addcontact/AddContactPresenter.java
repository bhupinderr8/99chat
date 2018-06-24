package com.example.bhupinder.a99chat.addcontact;


import com.example.bhupinder.a99chat.addcontact.events.AddContactEvent;

public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}

