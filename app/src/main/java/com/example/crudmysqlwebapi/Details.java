package com.example.crudmysqlwebapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView tvid;
    TextView tvname;
    TextView tvemail;
    TextView tvcontact;
    TextView tvaddress;
    int position;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvcontact = findViewById(R.id.txtcontact);
        tvaddress = findViewById(R.id.txtaddress);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID : "+ListView.employeeArrayList.get(position).getId());
        tvname.setText("Name : "+ListView.employeeArrayList.get(position).getName());
        tvemail.setText("Email : "+ListView.employeeArrayList.get(position).getEmail());
        tvcontact.setText("Contact : "+ListView.employeeArrayList.get(position).getContact());
        tvaddress.setText("Address : "+ListView.employeeArrayList.get(position).getAddress());
    }
}