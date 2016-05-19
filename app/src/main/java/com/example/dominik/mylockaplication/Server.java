package com.example.dominik.mylockaplication;

import android.util.Log;

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


public class Server {
    private static String address = "http://piotrtraczyk.tk/accountApp/";
    public static HttpURLConnection openConnection(String specificAddress)
    {
        HttpURLConnection resultHttpURLConnection = null;
        try {
            URL url = new URL(address + specificAddress);
            resultHttpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            Log.d("DEBUG", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("DEBUG", e.getMessage());
            e.printStackTrace();
        }
        return resultHttpURLConnection;
    }

    public static void writeToHttpURLConnection(HttpURLConnection connection, String toWrite)
    {
        connection.setDoOutput(true);
        connection.setDoInput(true);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(connection.getOutputStream(),"UTF-8"));
            writer.write(toWrite);
            writer.flush();
        } catch (IOException e) {
            Log.d("DEBUG", e.getMessage());
            e.printStackTrace();
        }
    }
    public static String readFromHttpURLConnection(HttpURLConnection connection)
    {
        BufferedReader reader = null;
        String buffer = null;
        StringBuilder response = new StringBuilder();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(),"UTF-8"));
            while((buffer = reader.readLine()) != null)
            {
                response.append(buffer+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString().trim();
    }

    public static String convertHashMapToPOSTString(HashMap<String,String> hashMap)
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
        result.append("&mobile=android");
        return result.toString();
    }
}
