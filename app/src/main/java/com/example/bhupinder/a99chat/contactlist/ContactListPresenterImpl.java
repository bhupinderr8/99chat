package com.example.bhupinder.a99chat.contactlist;

import com.example.bhupinder.a99chat.contactlist.entities.User;
import com.example.bhupinder.a99chat.contactlist.events.ContactListEvent;
import com.example.bhupinder.a99chat.contactlist.ui.ContactListView;
import com.example.bhupinder.a99chat.lib.EventBus;
import com.example.bhupinder.a99chat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

public class ContactListPresenterImpl implements ContactListPresenter {

    private EventBus eventBus;

    private ContactListInteractor contactListInteractor;

    private ContactListSessionInteractor contactListSessionInteractor;

    private ContactListView contactListView;

    public ContactListPresenterImpl(ContactListView contactListView) {
        this.contactListView = contactListView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.contactListInteractor = new ContactListInteractorImpl();
        this.contactListSessionInteractor = new ContactListSessionInteractorImpl();

    }

    @Override
    public void onPause() {
        contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactListInteractor.unSubscribeForContactEvents();
    }

    @Override
    public void onResume() {
        contactListInteractor.subscribeForContactEvents();
        contactListSessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        contactListView = null;
        contactListInteractor.destroyContactListListener();
    }

    @Override
    public void signOff() {
        contactListInteractor.unSubscribeForContactEvents();
        contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactListInteractor.destroyContactListListener();
        contactListSessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        contactListSessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        contactListInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()){

            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;

            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;

            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }

    }

    public void onContactChanged(User user){
        if(contactListView != null){
            contactListView.onContactChanged(user);
        }
    }

    public void onContactRemoved(User user){
        if(contactListView != null){
            contactListView.onContactRemoved(user);
        }

    }

    public void onContactAdded(User user){
        if(contactListView != null){
            contactListView.onContactAdded(user);
        }

    }
}
