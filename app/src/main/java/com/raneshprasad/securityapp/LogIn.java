package com.raneshprasad.securityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {
    Button ok;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    EditText username;
    EditText password;
    String username_str;
    String pass_str;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ok = (Button) findViewById(R.id.button_ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            username_str = username.getText().toString();
            pass_str = password.getText().toString();
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                        if(postSnapshot.getValue().toString().equals(username_str + " " +pass_str) ){
                            intent = new Intent(getApplicationContext(), DataActivity.class);
                            startActivity(intent);
                        }else if(postSnapshot.getValue().toString().substring(0, username_str.length()).equals(username_str) || postSnapshot.getValue().toString().substring(username_str.length() + 1).equals(pass_str)){
                            Toast.makeText(getApplicationContext(), "Either your username or your password is incorrect.", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Looks like you have to create an account!", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            }
        });
    }
}
