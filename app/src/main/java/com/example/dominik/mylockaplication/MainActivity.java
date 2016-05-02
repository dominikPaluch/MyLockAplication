package com.example.dominik.mylockaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener {

    Button btnLogin;
    EditText etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etLogin);
        etUsername.setHint("Your Login");
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword.setHint("Your Password");
        btnLogin = (Button) findViewById(R.id.btnLogin) ;
        btnLogin.setOnClickListener(this);

    }
    @Override
    public void processFinish(String result) {
        if(result.equals("success"))
        {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ActivityAfterLogin.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Login faileddd", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        HashMap postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("txtUsername", etUsername.getText().toString());
        postData.put("txtPassword", etPassword.getText().toString() );

        PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
        task.execute("http://10.0.2.2:8080/client/login.php"); //localhost:8080

    }
}
