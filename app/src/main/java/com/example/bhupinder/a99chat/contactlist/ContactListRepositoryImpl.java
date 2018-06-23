package com.example.bhupinder.a99chat.contactlist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.bhupinder.a99chat.contactlist.entities.User;
import com.example.bhupinder.a99chat.contactlist.events.ContactListEvent;
import com.example.bhupinder.a99chat.domain.FirebaseHelper;
import com.example.bhupinder.a99chat.lib.EventBus;
import com.example.bhupinder.a99chat.lib.GreenRobotEventBus;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class ContactListRepositoryImpl implements ContactListRepository {

    private FirebaseHelper helper;
    private ChildEventListener childEventListener;

    ContactListRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail, email).removeValue();
        helper.getOneContactReference(email, currentUserEmail).removeValue();
    }

    @Override
    public void destroyContactListListener() {
        childEventListener = null;
    }

    @Override
    public void subscribeForContactListUpdates() {
        if(childEventListener == null){
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String email = dataSnapshot.getKey();
                    email.replace("_",".");
                    boolean online = (boolean) dataSnapshot.getValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactAdded, user);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String email = dataSnapshot.getKey();
                    email.replace("_",".");
                    boolean online = (boolean) dataSnapshot.getValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactChanged, user);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    String email = dataSnapshot.getKey();
                    email.replace("_",".");
                    boolean online = (boolean) dataSnapshot.getValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactRemoved, user);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
        }
    }

    @Override
    public void unSubscribeForContactListUpdates() {
        if(childEventListener != null) helper.getMyContactsReference().removeEventListener(childEventListener);
    }

    @Override
    public void changeUserConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }

    private void postEvent(int type, User user){
        EventBus eventBus = GreenRobotEventBus.getInstance();
        ContactListEvent event = new ContactListEvent();
        event.setEventType(type);
        event.setUser(user);
        eventBus.post(event);
    }
}
