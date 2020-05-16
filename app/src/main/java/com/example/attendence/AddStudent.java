package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {
    private EditText studentName, mcneeseId;
    private Button add;
    private Button delete;
    private Button btnView;
    private FirebaseAuth firebaseAuth;
    Button update;

    private TextView btnvaluedatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        firebaseAuth = FirebaseAuth.getInstance();
        btnvaluedatabase = findViewById(R.id.passedclassnamedatabase);
        Intent classintent = getIntent();
        String classnamepassed = classintent.getStringExtra("Classname1");
        btnvaluedatabase.setText(classnamepassed);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        update=findViewById(R.id.updateStudentdatabase);
        studentName = findViewById(R.id.studentNamedatabase);
        mcneeseId = findViewById(R.id.mcneeseiddatabase);
        add = findViewById(R.id.addStudentdatabase);
        delete = findViewById(R.id.deleteStudentdatabase);
        btnView=findViewById(R.id.view_items_screen);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudent.this, ViewDatabase.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudentdatabase();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });


    }


    public void updateStudentdatabase() {
        String studentNameValue = studentName.getText().toString();
        String mcneeseIdValue = mcneeseId.getText().toString();

        if (!TextUtils.isEmpty(studentNameValue) && !TextUtils.isEmpty(mcneeseIdValue)) {
            String id = databaseReference.push().getKey();
            Students students = new Students(id, studentNameValue, mcneeseIdValue);
            // databaseReference.child(bttnName.getText().toString()).push().setValue(students);
            databaseReference.child(btnvaluedatabase.getText().toString()).child(mcneeseId.getText().toString()).setValue(students);

            studentName.getText();
            mcneeseId.getText();
            Toast.makeText(AddStudent.this, "Student Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddStudent.this, "Please Fill Fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void addStudent() {
        String studentNameValue = studentName.getText().toString();
        String mcneeseIdValue = mcneeseId.getText().toString();
        if (!TextUtils.isEmpty(studentNameValue) && !TextUtils.isEmpty(mcneeseIdValue)) {
            String id = databaseReference.push().getKey();
            Students students = new Students(id, studentNameValue, mcneeseIdValue);
            // databaseReference.child(bttnName.getText().toString()).push().setValue(students);
            databaseReference.child(btnvaluedatabase.getText().toString()).child(mcneeseId.getText().toString()).setValue(students);
            studentName.setText("");
            mcneeseId.setText("");
            Toast.makeText(AddStudent.this, "Student Details Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddStudent.this, "Please Fill Fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteStudent() {
        String studentNameValue = studentName.getText().toString();
        String mcneeseIdValue = mcneeseId.getText().toString();

        if (!TextUtils.isEmpty(studentNameValue) && !TextUtils.isEmpty(mcneeseIdValue)) {
            String id = databaseReference.push().getKey();
            Students students = new Students(id, studentNameValue, mcneeseIdValue);
            // databaseReference.child(bttnName.getText().toString()).push().setValue(students);
            databaseReference.child(btnvaluedatabase.getText().toString()).child(mcneeseId.getText().toString()).removeValue();

            studentName.setText("");
            mcneeseId.setText("");
            Toast.makeText(AddStudent.this, "Student Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddStudent.this, "Please Fill Fields", Toast.LENGTH_SHORT).show();
        }
    }


    //logout

    // logout below
    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(AddStudent.this, SecondActivity.class));
        Toast.makeText(AddStudent.this, "LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

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
                startActivity(new Intent(AddStudent.this,SecondActivity.class));
                Toast.makeText(AddStudent.this,"Logout Successful", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.item3:
//                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}