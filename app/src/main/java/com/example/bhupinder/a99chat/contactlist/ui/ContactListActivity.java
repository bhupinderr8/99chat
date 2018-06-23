package com.example.bhupinder.a99chat.contactlist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bhupinder.a99chat.R;
import com.example.bhupinder.a99chat.contactlist.ContactListPresenter;
import com.example.bhupinder.a99chat.contactlist.ContactListPresenterImpl;
import com.example.bhupinder.a99chat.contactlist.entities.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener {

    @BindView(R.id.recyclerViewContacts) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private ContactListPresenter contactListPresenter;
    private ContactListAdapter contactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        contactListPresenter = new ContactListPresenterImpl(this);
        contactListPresenter.onCreate();

        toolbar.setSubtitle(contactListPresenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);

        setUpAdapter();
        setupRecyclerView();
    }

    private void setUpAdapter() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactListPresenter.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        contactListPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactListPresenter.onResume();
    }

    @Override
    public void onContactAdded(User user) {

    }

    @Override
    public void onContactChanged(User user) {

    }

    @Override
    public void onContactRemoved(User user) {

    }

    @Override
    public void onItemClick(User user) {

    }

    @Override
    public void onItemLongClick(User user) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void addContact(){

    }

    private void setupRecyclerView(){

    }


}
