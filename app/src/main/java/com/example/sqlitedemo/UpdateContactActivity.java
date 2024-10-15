package com.example.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateContactActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText firstNameEdt, lastNameEdt, addressEdt, cityEdt, ageEdt;
    private Button updateContactBtn, deleteContactBtn;
    private DBHandler dbHandler;
    String firstName, lastName, address, city, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        // initializing all our variables.
        firstNameEdt = findViewById(R.id.idEdtFirstName);
        lastNameEdt = findViewById(R.id.idEdtLastName);
        addressEdt = findViewById(R.id.idEdtAddress);
        cityEdt = findViewById(R.id.idEdtCity);
        ageEdt = findViewById(R.id.idEdtAge);
        updateContactBtn = findViewById(R.id.idBtnUpdateContact);
        deleteContactBtn = findViewById(R.id.idBtnDeleteContact);

        // on below line we are initializing our dbhandler class.
        dbHandler = new DBHandler(UpdateContactActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");
        address = getIntent().getStringExtra("address");
        city = getIntent().getStringExtra("city");
        age = getIntent().getStringExtra("age");

        // setting data to edit text
        // of our update activity.
        firstNameEdt.setText(firstName);
        lastNameEdt.setText(lastName);
        addressEdt.setText(address);
        cityEdt.setText(city);
        ageEdt.setText(age);

        // adding on click listener to our update contact button.
        updateContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update contact
                // method and passing all our edit text values.
                // (Tried to compare using address but it wouldn't work even though the address and
                // originalAddress are the same, and i cannot figure out why)
                dbHandler.updateContact(firstName, firstNameEdt.getText().toString(), lastNameEdt.getText().toString(), addressEdt.getText().toString(), cityEdt.getText().toString(), ageEdt.getText().toString());

                // displaying a toast message that our contact has been updated.
                Toast.makeText(UpdateContactActivity.this, "Contact Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateContactActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our contact.
        deleteContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our contact.
                dbHandler.deleteContact(firstName);
                Toast.makeText(UpdateContactActivity.this, "Deleted the contact", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateContactActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
