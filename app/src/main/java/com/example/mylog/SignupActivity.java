package com.example.mylog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Email, Password, ConfirmPassword;
    private Button SignIn;
    private TextView LoginText;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }


        progressDialog = new ProgressDialog(this);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        ConfirmPassword = findViewById(R.id.confirmPassword);
        SignIn = findViewById(R.id.Signup);
        LoginText = findViewById(R.id.login);

        SignIn.setOnClickListener(this);
        LoginText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == SignIn) {
            signIn();
        }

        if(v == LoginText) {
            startActivity(new Intent(this, Login.class));
        }

    }

    public void signIn(){
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String confirmpassword = ConfirmPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(confirmpassword)){
            Toast.makeText(this, "Please confirm your password",Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.equals(confirmpassword)) {
            progressDialog.setMessage("Please wait...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Registered succesfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignupActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            Toast.makeText(this, "Password doesn't match",Toast.LENGTH_SHORT).show();
            return;
        }

    }

}
