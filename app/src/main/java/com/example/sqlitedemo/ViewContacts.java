package com.example.sqlitedemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewContacts extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<ContactModel> contactModelArrayList;
    private DBHandler dbHandler;
    private ContactRVAdapter contactRVAdapter;
    private RecyclerView contactsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        // initializing our all variables.
        contactModelArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewContacts.this);

        // getting our contact array
        // list from db handler class.
        contactModelArrayList = dbHandler.readContacts();

        // on below line passing our array list to our adapter class.
        contactRVAdapter = new ContactRVAdapter(contactModelArrayList, ViewContacts.this);
        contactsRV = findViewById(R.id.idRVContacts);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewContacts.this, RecyclerView.VERTICAL, false);
        contactsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        contactsRV.setAdapter(contactRVAdapter);
    }
}
