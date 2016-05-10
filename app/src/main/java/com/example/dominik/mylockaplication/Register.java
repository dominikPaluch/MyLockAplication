package com.example.dominik.mylockaplication;

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.util.HashMap;

/**
 * Created by Piotr on 2016-05-09.
 */
public class Register extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... params) {
        HashMap<String,String> user = new HashMap<>();
        user.put("txtUsername", params[0]);
        user.put("txtPassword", params[1]);

        HttpURLConnection con = Server.openConnection("register.php");
        Server.writeToHttpURLConnection(con, Server.convertHashMapToPOSTString(user));
        return Server.readFromHttpURLConnection(con);
    }
}
