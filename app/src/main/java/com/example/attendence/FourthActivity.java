package com.example.attendence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class FourthActivity extends AppCompatActivity {

    private EditText UserNameEmail, UserClassName, UserPasswordRegister;
    private Button UserRegisterBtn;

    private FirebaseAuth fireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        UserNameEmail = findViewById(R.id.profemail);
        UserClassName = findViewById(R.id.classnameregister);
        UserPasswordRegister = findViewById(R.id.passwordRegister);
        UserRegisterBtn= findViewById(R.id.buttonRegister);

        fireBaseAuth =FirebaseAuth.getInstance();

        UserRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    // Register to database upload
                    String user_email = UserNameEmail.getText().toString().trim();
                    String user_password = UserPasswordRegister.getText().toString().trim();
                    fireBaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toasty.success(FourthActivity.this, "Registration Successful", Toast.LENGTH_SHORT, true).show();

//                                Toast.makeText(FourthActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(FourthActivity.this,ThirdActivity.class));
                            }else{
//                                Toast.makeText(FourthActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                                Toasty.error(FourthActivity.this, "Registration Failed", Toast.LENGTH_SHORT, true).show();

                            }

                        }
                    });
                }
            }
        });

    }

    private Boolean validate(){
        boolean result = false;
        String name = UserNameEmail.getText().toString();
        String password = UserPasswordRegister.getText().toString();
        String classname = UserClassName.getText().toString();

        if(name.isEmpty() && password.isEmpty() || classname.isEmpty()){
            Toasty.error(this, "Please Fill Fields", Toast.LENGTH_SHORT, true).show();
        }else{
            result =true;
        }
        return result;
    }
}
