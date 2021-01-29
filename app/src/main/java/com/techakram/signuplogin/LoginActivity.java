package com.techakram.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailEt,passwordEt;
    Button buttonLogin;
    TextView linkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt=findViewById(R.id.etEmailLogin);
        passwordEt=findViewById(R.id.etpasswordLogin);
        buttonLogin=findViewById(R.id.btnLogin);
        linkLogin=findViewById(R.id.etRegisterLink);

        buttonLogin.setOnClickListener(this);
        linkLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.etLoginLink:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
                break;

            case R.id.btnLogin:
                loginValidate();
                break;
        }


    }

    private void loginValidate() {
        String email=emailEt.getText().toString();
        String password=passwordEt.getText().toString();

        if (email.isEmpty())
        {
            emailEt.requestFocus();
            emailEt.setError("Please enter Email ID");
            return;
        }
        if (password.isEmpty())
        {
            passwordEt.requestFocus();
            passwordEt.setError("Enter Password");
            return;
        }

        validateUserAndLogin(email,password);
    }

    private void validateUserAndLogin(String email, String password) {

        SQLiteOperations s = new SQLiteOperations(this);
        int co=0;
        Cursor c = s.readData( );

        UserModel u;

        if (c.moveToFirst( )) {
            do {
                if (password.equals(c.getString(3))&&email.equals(c.getString(2)))
                {
                    co=co+1;
                    u=new UserModel(c.getString(0),c.getString(1),c.getString(2));
                    SharedPrefManager sf=new SharedPrefManager(this);
                    sf.saveUser(u);
                    startActivity(new Intent(this,HomeActivity.class));
                    finish();
                    Toast.makeText(this, "Login Sucess" , Toast.LENGTH_SHORT).show( );
                    return;
                }
                // Adding country to lis
            } while (c.moveToNext( ));

        }

        if (co==0)
        {
            Toast.makeText(this, "Invalid Credential", Toast.LENGTH_SHORT).show( );
        }

    }

}