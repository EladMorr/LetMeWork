package com.example.letmework.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.letmework.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }
    public void createNewUser(View view) {
        EditText email = findViewById(R.id.newUserEmail);
        String emailText = email.getText().toString();
        EditText password = findViewById(R.id.newUserPassword);
        String passwordText = password.getText().toString();
        EditText name = findViewById(R.id.newUserName);
        String nameText = password.getText().toString();
        EditText phone = findViewById(R.id.newUserPhone);
        String phoneText = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"User created", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_register_to_login);
                        } else {
                            Toast.makeText(MainActivity.this,"Register failed", Toast.LENGTH_LONG).show();

                        }
                    }
                });


    }

    public void loginFunc(View view) {
        EditText email = findViewById(R.id.emailLog);
        String emailText = email.getText().toString();
        EditText password = findViewById(R.id.passwordLog);
        String passwordText = password.getText().toString();

        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"Welcome back", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_login_to_main);


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,"sign-in failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}