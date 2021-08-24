package com.example.crudmysqlwebapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    EditText et_id;
    EditText et_name;
    EditText et_email;
    EditText et_contact;
    EditText et_address;

    Button btn_update;

    int position;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        et_id = findViewById(R.id.et_id_update);
        et_name = findViewById(R.id.et_name_update);
        et_email = findViewById(R.id.et_email_update);
        et_contact = findViewById(R.id.et_contact_update);
        et_address = findViewById(R.id.et_address_update);

        btn_update = findViewById(R.id.btn_insertData_update);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        et_id.setText("ID : " + ListView.employeeArrayList.get(position).getId());
        et_name.setText("Name : " + ListView.employeeArrayList.get(position).getName());
        et_email.setText("Email : " + ListView.employeeArrayList.get(position).getEmail());
        et_contact.setText("Contact : " + ListView.employeeArrayList.get(position).getContact());
        et_address.setText("Address : " + ListView.employeeArrayList.get(position).getAddress());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_update();
            }
        });
    }

    private void btn_update() {

        String name = et_name.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String contact = et_contact.getText().toString().trim();
        String address = et_address.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://govindtechmysql.000webhostapp.com/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if (response.equalsIgnoreCase("Data Updated")) {
                    Toast.makeText(EditActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditActivity.this, ListView.class));
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", String.valueOf(position));
                params.put("name",name);
                params.put("email",email);
                params.put("contact",contact);
                params.put("address",address);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditActivity.this);
        requestQueue.add(request);
    }
}