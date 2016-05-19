package com.example.dominik.mylockaplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AfterSuccessLogin extends AppCompatActivity
{
    Button btnLogout;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_success_login);
        context = this;
        btnLogout = (Button) findViewById(R.id.btnLogout);
    }

    public void Logout(View view)
    {
        Intent i = new Intent(context, MainActivity.class);
        startActivity(i);
    }
}
