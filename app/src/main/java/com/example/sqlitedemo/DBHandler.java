package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "contactsdb";

    private static final int DB_VERSION = 4;

    private static final String TABLE_NAME = "Contact";

    private static final String ID_COL = "id";

    private static final String FIRST_NAME_COL = "firstName";

    private static final String LAST_NAME_COL = "lastName";

    private static final String ADDRESS_COL = "address";

    private static final String CITY_COL = "city";

    private static final String AGE_COL = "age";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + ADDRESS_COL + " TEXT,"
                + CITY_COL + " TEXT,"
                + AGE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewContact(String firstName, String lastName, String address, String city, String age)
    {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(FIRST_NAME_COL, firstName);
        values.put(LAST_NAME_COL, lastName);
        values.put(ADDRESS_COL, address);
        values.put(CITY_COL, city);
        values.put(AGE_COL, age);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the contacts.
    public ArrayList<ContactModel> readContacts() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorContacts = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<ContactModel> contactModelArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorContacts.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                contactModelArrayList.add(new ContactModel(
                        cursorContacts.getString(1),
                        cursorContacts.getString(4),
                        cursorContacts.getString(2),
                        cursorContacts.getString(3),
                        cursorContacts.getString(5)
                ));
            } while (cursorContacts.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorContacts.close();
        return contactModelArrayList;
    }

    // below is the method for updating our contacts
    public void updateContact(String originalFirstName, String firstName, String lastName, String address, String city, String age) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(FIRST_NAME_COL, firstName);
        values.put(LAST_NAME_COL, lastName);
        values.put(ADDRESS_COL, address);
        values.put(CITY_COL, city);
        values.put(AGE_COL, age);


        db.update(TABLE_NAME, values, "firstName=?", new String[]{originalFirstName});
        db.close();
    }

    public void deleteContact(String originalFirstName) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "firstName=?", new String[]{originalFirstName});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
