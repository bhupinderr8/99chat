package com.example.bhupinder.a99chat.contactlist;

public interface ContactListInteractor {
    void subscribeForContactEvents();

    void unSubscribeForContactEvents();

    void destroyContactListListener();

    void removeContact(String email);
}
