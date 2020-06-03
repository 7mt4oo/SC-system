package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class FifthActivity extends AppCompatActivity {
    private TextView btnvalue;
    int request_code = 1;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        firebaseAuth = FirebaseAuth.getInstance();
        btnvalue = findViewById(R.id.passedclassname);

        Intent classintent = getIntent();
        String classnamepassed = classintent.getStringExtra("Classname");
        btnvalue.setText(classnamepassed);
    }

    public void addStudents(View view) {
        //startActivity(new Intent(this,AddStudent.class));

        String TextClassname = btnvalue.getText().toString();
        // starting our intent
        Intent classintent = new Intent(this, AddStudent.class);
        classintent.putExtra("Classname1", TextClassname);
        startActivityForResult(classintent, request_code);
    }

    public void takeAttendence(View view) {
        // startActivity(new Intent(this,TakeAttendence.class));

        String TextClassname = btnvalue.getText().toString();
        // starting our intent
        Intent classintent = new Intent(this, TakeAttendence.class);
        classintent.putExtra("Classname1", TextClassname);
        startActivityForResult(classintent, request_code);
    }

    // logout below
    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(FifthActivity.this, SecondActivity.class));
        Toasty.success(this, "Logout Successful", Toast.LENGTH_SHORT, true).show();

    }


}