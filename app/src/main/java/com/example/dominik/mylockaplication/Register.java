package com.example.dominik.mylockaplication;

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.util.HashMap;

public class Register extends AsyncTask<String,Void,String>
{

    @Override
    protected String doInBackground(String... params)
    {
        HashMap<String,String> user = new HashMap<>();
        user.put("txtUsername", params[0]);
        user.put("txtPassword", params[1]);

        HttpURLConnection con = Server.openConnection("register.php");
        Server.writeToHttpURLConnection(con, Server.convertHashMapToPOSTString(user));
        return Server.readFromHttpURLConnection(con);
    }
}
