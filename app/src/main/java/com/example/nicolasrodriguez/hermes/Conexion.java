package com.example.nicolasrodriguez.hermes;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Conexion extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection httpURLConnection = null;
        URL url = null;

        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            int codigo = httpURLConnection.getResponseCode();

            if (codigo == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream (httpURLConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String linea = "";
                StringBuffer buffer = new StringBuffer();

                while ((linea = reader.readLine()) != null){
                    buffer.append(linea);
                }

                return buffer.toString();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
