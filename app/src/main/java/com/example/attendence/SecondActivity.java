package com.example.attendence;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView userRegistration;
    private Spinner spinner;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog processDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Email = findViewById(R.id.editemail);
        Password = findViewById(R.id.editpassword);
        Login = findViewById(R.id.loginmainbutton);
        spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        firebaseAuth = FirebaseAuth.getInstance();
        processDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(this,ThirdActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item = spinner.getSelectedItem().toString();
                if(Email.getText().toString().equals("admin")&& Password.getText().toString().equals("admin")&& item.equals("Admin")){
                    Intent intent = new Intent(SecondActivity.this, AddStudent.class);
                    startActivity(intent);
                }else if(Email.getText().toString().equals("user")&& Password.getText().toString().equals("user")&& item.equals("User")){
                    Intent intent = new Intent(SecondActivity.this, AddStudent.class);
                    startActivity(intent);
                }else if(Email.getText().toString().equals("super")&& Password.getText().toString().equals("super")&& item.equals("Supervisor")){
                    Intent intent = new Intent(SecondActivity.this, AddStudent.class);
                    startActivity(intent);
                }else {
//                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
//                if (Email.getText().toString().equals("admin@admin.com") && Password.getText().toString().equals("admin")){
//                Toast.makeText(SecondActivity.this,"LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), AddStudent.class);
//                    startActivity(intent);
//
//                } else {
////                    Toast.makeText(SecondActivity.this,"LOGIN FAILED", Toast.LENGTH_SHORT).show();
//
//
//                }
                validate(Email.getText().toString(), Password.getText().toString());

            }
        });

        //classname = findViewById(R.id.viewclassname);

//        Intent classintent = getIntent();
//        String classnamepassed = classintent.getStringExtra("Classname");
//        classname.setText(classnamepassed);
    }

    private void validate (String userEmail, String userPassword){

        processDialog.setMessage("........Please Wait.......");
        processDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    processDialog.dismiss();
                    Toast.makeText(SecondActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
                }else {
                    Toast.makeText(SecondActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    processDialog.dismiss();

                }
            }
        });

    }

}
