package com.example.bhupinder.a99chat.addcontact;


public class AddContactInteractorImpl implements AddContactInteractor {
    AddContactRepository addContactRepository;

    public AddContactInteractorImpl(AddContactRepositoryImpl addContactRepository) {
        this.addContactRepository = addContactRepository;
    }

    @Override
    public void addContact(String email) {
        addContactRepository.addContact(email);
    }
}
