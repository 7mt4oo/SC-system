package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class TakeAttendence extends AppCompatActivity {
    private EditText takeAttendence,take;
    private Button attendenceBtn;
    private TextView btnvaluedatabase;
    private FirebaseAuth firebaseAuth;
    //..........Date
    private TextView tvCounter;

    private TextView mDisplayDate;
    Button button;





    // DatabaseReference databaseReference;

    private  int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);
        firebaseAuth = FirebaseAuth.getInstance();

        //.. DAte
        mDisplayDate = (TextView) findViewById(R.id.tvDate);


        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String date = month+1 + "-" + day + "-" + year;

        mDisplayDate.setText(date);

        //..

        btnvaluedatabase = findViewById(R.id.classnamepassedtakeattendence);
        Intent classintent = getIntent();
        String classnamepassed = classintent.getStringExtra("Classname1");
        btnvaluedatabase.setText(classnamepassed);
        tvCounter = findViewById(R.id.tvcounter);
        takeAttendence = findViewById(R.id.takeattendence);
//        take = findViewById(R.id.takeattendenenceschool);
        attendenceBtn = findViewById(R.id.attendencebtn);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myitent = new Intent(TakeAttendence.this, MainActivitysc.class);
                startActivity(myitent);
            }
        });

        // databaseReference = FirebaseDatabase.getInstance().getReference("Attendence");
        // FROM LOACATION BUT REMBER TO GIVE IT INSIDE LOOOP ELSE WILL COME BACK
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("students");

        // final     DatabaseReference fromPath = FirebaseDatabase.getInstance().getReference("students");

        ref.child(btnvaluedatabase.getText().toString()).orderByChild("mcneeseId").equalTo(takeAttendence.getText().toString());
        //....................... TO LOCATION
        final   DatabaseReference toPath = FirebaseDatabase.getInstance()
                .getReference("Attendance")
                .child(btnvaluedatabase.getText().toString())  // NEED TO GET DATE INPUT
                .child("Date = "+date)
                .child(takeAttendence.getText().toString());
        //                .child(take.getText().toString());
        // CHECKING IF THE GIVEN INPUT FROM USER IS IN DATABASE AND THEN IF IT IS COPY THAT CHILD AND PASTE THAT CHILD TO ATTENDENCE
        attendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(btnvaluedatabase.getText().toString()).orderByChild("mcneeseId").equalTo(takeAttendence.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                            //.........................

                            ValueEventListener valueEventListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    toPath.child(takeAttendence.getText().toString()).setValue(dataSnapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isComplete()) {
                                                takeAttendence.setText("");
                                                //  Toast.makeText(TakeAttendence.this,"Attendence Accepted",Toast.LENGTH_SHORT).show();

                                            } else {

                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {}
                            };
                            ref.child(btnvaluedatabase.getText().toString()).child(takeAttendence.getText().toString()).addListenerForSingleValueEvent(valueEventListener);

                            //..................................................................
                            counter = counter + 1;
                            tvCounter.setText(String.valueOf(counter));
                            Toast.makeText(TakeAttendence.this,"Attendance Accepted",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(TakeAttendence.this,"Invalid",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }


    // attendence counter


    // logout below
    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(TakeAttendence.this,SecondActivity.class));
        Toast.makeText(TakeAttendence.this,"Logout Successful", Toast.LENGTH_SHORT).show();

    }


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
                startActivity(new Intent(TakeAttendence.this,SecondActivity.class));
                Toast.makeText(TakeAttendence.this,"Logout Successful", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.item3:
//                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
