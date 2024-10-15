package com.example.sqlitedemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ContactModel> contactModelArrayList;
    private Context context;

    // constructor
    public ContactRVAdapter(ArrayList<ContactModel> contactModelArrayList, Context context) {
        this.contactModelArrayList = contactModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ContactModel modal = contactModelArrayList.get(position);
        holder.firstNameTV.setText(modal.getFirstName());
        holder.lastNameTV.setText(modal.getLastName());
        holder.addressTV.setText(modal.getAddress());
        holder.cityTV.setText(modal.getCity());
        holder.ageTV.setText(modal.getAge());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateContactActivity.class);

                // below we are passing all our values.
                i.putExtra("firstName", modal.getFirstName());
                i.putExtra("lastName", modal.getLastName());
                i.putExtra("address", modal.getAddress());
                i.putExtra("city", modal.getCity());
                i.putExtra("age", modal.getAge());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return contactModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView firstNameTV, lastNameTV, addressTV, cityTV, ageTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            firstNameTV = itemView.findViewById(R.id.idTVFirstName);
            lastNameTV = itemView.findViewById(R.id.idTVLastName);
            addressTV = itemView.findViewById(R.id.idTVAddress);
            cityTV = itemView.findViewById(R.id.idTVCity);
            ageTV = itemView.findViewById(R.id.idTVAge);
        }
    }
}
