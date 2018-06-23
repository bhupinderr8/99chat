package com.example.bhupinder.a99chat.contactlist;

public interface ContactListRepository {

    void signOff();

    String getCurrentEmail();

    void removeContact(String email);

    void destroyContactListListener();

    void subscribeForContactListUpdates();

    void unSubscribeForContactListUpdates();

    void changeUserConnectionStatus(boolean online);
}
