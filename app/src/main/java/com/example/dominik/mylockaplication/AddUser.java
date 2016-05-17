package com.example.dominik.mylockaplication;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.HashMap;

public class
AddUser extends AppCompatActivity implements View.OnClickListener
{
    EditText etLogin, etName, etPassword1, etPassword2, etEmail;
    private Context context;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etName = (EditText)findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword2 = (EditText)findViewById(R.id.etPassword2);
        etPassword1 = (EditText)findViewById(R.id.etPassword1);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        context = this;

    }

    @Override
    public void onClick(View v)
    {
        new TryToAddNewUser().execute(etName.getText().toString(),
                etLogin.getText().toString(),
                etPassword1.getText().toString(),
                etPassword2.getText().toString(),
                etEmail.getText().toString());

    }

    class TryToAddNewUser extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params)
        {
            HttpURLConnection connection = Server.openConnection("register.php");
            HashMap<String, String> loginAndPassMap = new HashMap<>();
            loginAndPassMap.put("txtName", params[0]);
            loginAndPassMap.put("txtLogin", params[1]);
            loginAndPassMap.put("txtPassword1", params[2]);
            loginAndPassMap.put("txtPassword2", params[3]);
            loginAndPassMap.put("txtEmail", params[4]);
            Server.writeToHttpURLConnection(connection, Server.convertHashMapToPOSTString(loginAndPassMap));

            return Server.readFromHttpURLConnection(connection);
        }

        @Override
        protected void onPostExecute(String responce)
        {
            Log.d("DEBUG", responce);
            if(responce.equals("user_added"))
            {
                Toast.makeText(context, "Add new user successful!", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(context, "Add new user wrong!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
