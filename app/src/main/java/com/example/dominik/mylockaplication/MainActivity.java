package com.example.dominik.mylockaplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText etUsername, etPassword;
    private Context context;
    private Toast toast = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etLogin);
        etUsername.setHint("Your Login");
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword.setHint("Your Password");
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        context = this;
    }

    public void addUser(View view)
    {
        Intent i = new Intent(this, AddUser.class);
        startActivity(i);
    }

//    public static void changeToast(String message, Context context, Toast toast)
//    {
//        if(toast != null)
//        {
//            toast.cancel();
//        }
//        toast = Toast.makeText(context , message, Toast.LENGTH_SHORT);
//        toast.show();
//    }

    class TryToLogin extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params)
        {

            HttpURLConnection loginConnection = Server.openConnection("login.php");
            HashMap<String, String> loginAndPassMap = new HashMap<>();
            loginAndPassMap.put("txtUsername", params[0]);
            loginAndPassMap.put("txtPassword", params[1]);
            Server.writeToHttpURLConnection(loginConnection,
                    Server.convertHashMapToPOSTString(loginAndPassMap));
            return Server.readFromHttpURLConnection(loginConnection);
        }

        @Override
        protected void onPostExecute(String responce)
        {
            if (responce.equals("success"))
            {
                Intent i = new Intent(context, AfterSuccessLogin.class);
                startActivity(i);
            }
            else
            {
                // changeToast("Login or password wrong", context, toast);
                if(toast != null)
                {
                    toast.cancel();
                }
                toast = Toast.makeText(context , "Login or password wrong", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        Log.d("DEBUG", "tried to log");
        new TryToLogin().execute(etUsername.getText().toString(),
                etPassword.getText().toString());

    }


}
