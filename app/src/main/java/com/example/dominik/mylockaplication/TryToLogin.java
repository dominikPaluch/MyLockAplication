package com.example.dominik.mylockaplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Piotr on 2016-05-02.
 */
public class TryToLogin {

    private String login;
    private String password;
    private URL loginUrl;
    boolean logged = false;
    public TryToLogin(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public String logIn()
    {
        String response = null;
        String result="";


        try {
            loginUrl = new URL("http://localhost:80/client/login.php");
            HttpURLConnection connection = (HttpURLConnection) loginUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("login", login);
            hashMap.put("password", password);

            result = getHashMapInString(hashMap);

            writer.write(result);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(),"UTF-8"));

            response = reader.readLine();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    String getHashMapInString(HashMap<String,String> hashMap)
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String,String> item: hashMap.entrySet())
        {
            if(first)
                first = false;
            else
                result.append("&");

            result.append(item.getKey());
            result.append("=");
            result.append(item.getValue());
        }

        return result.toString();
    }
}
