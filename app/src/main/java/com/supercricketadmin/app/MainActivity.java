package com.supercricketadmin.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    EditText number;
    Button done;
    FirebaseFirestore dbRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.number);
        done = findViewById(R.id.done);

        dbRoot = FirebaseFirestore.getInstance();


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!number.getText().toString().isEmpty() && number.getText().toString().length() == 10) {
                    Map<String, String> v = new HashMap<>();

                    v.put("number", "+91" + number.getText().toString());

                    FirebaseFirestore.getInstance().collection("user").add(v)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    Log.e("add Number", v.toString());
                                }
                            });
                }
                else{
                    Toast.makeText(MainActivity.this, "Please Enter the Valid Number", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}