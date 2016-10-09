package com.raneshprasad.securityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    Button ok;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    EditText name;
    EditText email;
    EditText password;

    String name_str;
    String email_str;
    String password_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.editText_name);
        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_pass);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Vybhav, over here the kids will type the code for taking
                in the text from EditText and converting it to string.

                 */
                name_str = name.getText().toString();
                email_str = email.getText().toString();
                password_str = password.getText().toString();
                myRef.push().setValue(name_str + " " +password_str);

                myRef.push().child(name_str).setValue(email_str);
                Intent intent = new Intent(getApplicationContext(), DataActivity.class);
                startActivity(intent);
            }
        });

    }
}
