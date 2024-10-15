package com.example.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText firstNameEdt, lastNameEdt, addressEdt, cityEdt, ageEdt;
    private Button addContactBtn, readContactBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        firstNameEdt = findViewById(R.id.idEdtFirstName);
        lastNameEdt = findViewById(R.id.idEdtLastName);
        addressEdt = findViewById(R.id.idEdtAddress);
        cityEdt = findViewById(R.id.idEdtCity);
        ageEdt = findViewById(R.id.idEdtAge);
        addContactBtn = findViewById(R.id.idBtnAddContact);
        readContactBtn = findViewById(R.id.idBtnReadContact);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add contact button.
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String firstName = firstNameEdt.getText().toString();
                String lastName = lastNameEdt.getText().toString();
                String address = addressEdt.getText().toString();
                String city = cityEdt.getText().toString();
                String age = ageEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || age.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // contact to sqlite data and pass all our values to it.
                dbHandler.addNewContact(firstName, lastName, address, city, age);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Contact has been added.", Toast.LENGTH_SHORT).show();
                firstNameEdt.setText("");
                lastNameEdt.setText("");
                addressEdt.setText("");
                cityEdt.setText("");
                ageEdt.setText("");
            }
        });

        readContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewContacts.class);
                startActivity(i);
            }
        });
    }
}

