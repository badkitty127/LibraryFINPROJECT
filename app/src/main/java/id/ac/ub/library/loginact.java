package id.ac.ub.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginact extends AppCompatActivity {
    Button loginbtn;
    TextView email1,pass1;
    FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);
        loginbtn = findViewById(R.id.loginbtn);
        email1 = findViewById(R.id.nametxt);
        pass1 = findViewById(R.id.passwordtxt);

        firebaseauth = FirebaseAuth.getInstance();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailz = email1.getText().toString();
                String password1 = pass1.getText().toString();
                login(emailz,password1);
            }
        });


    }

    public void login(String email, String password){
        firebaseauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(loginact.this, new OnCompleteListener<AuthResult>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(loginact.this, MainActivity.class);
                    Toast.makeText(loginact.this,"Login",4).show();
                    startActivity(intent);

                } else {
                    Toast.makeText(loginact.this,"eaaa :)",4).show();
                }
            }
        });
    }
}