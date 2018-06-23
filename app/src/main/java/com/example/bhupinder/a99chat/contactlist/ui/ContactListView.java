package com.example.bhupinder.a99chat.contactlist.ui;

import com.example.bhupinder.a99chat.contactlist.entities.User;

public interface ContactListView {
    void onContactAdded(User user);

    void onContactChanged(User user);

    void onContactRemoved(User user);
}
