package com.example.financeadvisor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;


public class LoginPage extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton, signIn,register;

    FirebaseAuth auth;
    FirebaseDatabase database;

    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signIn = findViewById(R.id.google);
register=findViewById(R.id.signupText);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginPage.this,MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginPage.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginPage.this,Registration.class);
                startActivity(i);
            }
        });

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(LoginPage.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("we are creating your account");


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                                .build();

mGoogleSignInClient = GoogleSignIn.getClient(this,gso);


        signIn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

 signin();
    }
});




    }

    int RC_SIGN_IN = 40;
    private void signin() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
     else {
        Toast.makeText(LoginPage.this, "Authentication failed: ", Toast.LENGTH_SHORT).show();
    }




}

    private void firebaseAuth(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);

        auth.signInWithCredential(credential)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    Intent intent = new Intent(LoginPage.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginPage.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}