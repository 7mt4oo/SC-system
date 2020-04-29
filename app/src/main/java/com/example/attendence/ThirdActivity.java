package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ThirdActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    private  Button btnandroid;
    private  Button btneng,btnjava,btndatabase,logout;

    int request_code =1;
    //private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setContentView(R.layout.activity_third);
        //logout = (Button)findViewById(R.id.btnlogout);
        firebaseAuth = FirebaseAuth.getInstance();
        btnandroid = findViewById(R.id.csci409btn);
        btneng = findViewById(R.id.softengbth);
        btnjava = findViewById(R.id.CSCI282java);
        btndatabase = findViewById(R.id.CSCI309data);
        logout = findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Logout();
//            }
//        });

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Logout();
//                }
//        });

    }

//
//    // logout below
//    private void Logout()
//    {
//        firebaseAuth.signOut();
//        finish();
//        startActivity(new Intent(ThirdActivity.this,SecondActivity.class));
//        Toast.makeText(ThirdActivity.this,"Logout Successful", Toast.LENGTH_SHORT).show();
//
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return true;
//    }
//
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case  R.id.logoutMenu:{
//                Logout();
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ThirdActivity.this,SecondActivity.class));
                Toast.makeText(ThirdActivity.this,"Logout Successful", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.item3:
//                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void android(View view){
        //startActivity(new Intent(this,FifthActivity.class));

        String TextClassname = btnandroid.getText().toString();
        // starting our intent
        Intent classintent = new Intent(this,FifthActivity.class);
        classintent.putExtra("Classname",TextClassname);
        startActivityForResult(classintent,request_code);

    }

    public  void softeng(View view){
        // startActivity(new Intent(this,FifthActivity.class));

        String TextClassname = btneng.getText().toString();
        // starting our intent
        Intent classintent = new Intent(this,FifthActivity.class);
        classintent.putExtra("Classname",TextClassname);
        startActivityForResult(classintent,request_code);

    }

    public  void CSCI282db(View view){
        // startActivity(new Intent(this,FifthActivity.class));

        String TextClassname = btnjava.getText().toString();
        // starting our intent
        Intent classintent = new Intent(this,FifthActivity.class);
        classintent.putExtra("Classname",TextClassname);
        startActivityForResult(classintent,request_code);

    }
    public  void CSCI309db(View view){
        // startActivity(new Intent(this,FifthActivity.class));

        String TextClassname = btndatabase.getText().toString();
        // starting our intent
        Intent classintent = new Intent(this,FifthActivity.class);
        classintent.putExtra("Classname",TextClassname);
        startActivityForResult(classintent,request_code);

    }
}
