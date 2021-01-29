package com.techakram.signuplogin;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    EditText fsET,seET,emET,psET,cnfPsET;
    TextView linkLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        SharedPrefManager sfm=new SharedPrefManager(this);
        boolean b=sfm.isLoginIn();
        if(b==true)
        {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
            return;
        }

        btnRegister=findViewById(R.id.btnRegister);
        fsET=findViewById(R.id.etFirstNameSUP);
        seET=findViewById(R.id.etLastNameSUP);
        emET=findViewById(R.id.etEmailSUP);
        psET=findViewById(R.id.etpasswordSUP);
        cnfPsET=findViewById(R.id.etCnfpasswordSUP);
        linkLog=findViewById(R.id.etLoginLink);
        btnRegister.setOnClickListener(this);
        linkLog.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.etLoginLink:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
            case R.id.btnRegister:
                validateData();
                break;
        }
    }
    private void validateData() {
        String fs=fsET.getText().toString();
        String ses=seET.getText().toString();
        String em=emET.getText().toString();
        String ps=psET.getText().toString();
        String cnfPs=cnfPsET.getText().toString();
        if (fs.isEmpty())
        {
            fsET.requestFocus();
            fsET.setError("Enter First Name");
            return;
        }
        if (ses.isEmpty())
        {
            seET.requestFocus();
            seET.setError("Enter Last Name");
            return;
        }
        if (em.isEmpty())
        {
            emET.requestFocus();
            emET.setError("Enter Email Id");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(em).matches())
        {
            emET.requestFocus();
            emET.setError("Enter Valid Password");
            return;
        }
        if (ps.isEmpty())
        {
            psET.requestFocus();
            psET.setError("Enter Password");
            return;
        }
        if (ps.length()<6)
        {
            psET.requestFocus();
            psET.setError("Enter minimum 6 character");
            return;
        }
        if (cnfPs.isEmpty())
        {
            cnfPsET.requestFocus();
            cnfPsET.setError("Enter Conform password");
            return;
        }
        if (ps.equals(cnfPs))
        {
            SQLiteOperations sq=new SQLiteOperations(this);
            sq.insertData(fs,ses,em,ps);
            Toast.makeText(this, "Sucessfully Registered", Toast.LENGTH_SHORT).show( );
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        else
        {
            cnfPsET.requestFocus();
            cnfPsET.setError("Enter Same Password");
            return;
        }
    }
}