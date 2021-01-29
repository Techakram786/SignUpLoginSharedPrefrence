package com.techakram.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn=findViewById(R.id.logout);
        btn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                SharedPrefManager sf=new SharedPrefManager(HomeActivity.this);
                sf.logout();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                Toast.makeText(HomeActivity.this, "Logout", Toast.LENGTH_SHORT).show( );
            }
        });
    }
}