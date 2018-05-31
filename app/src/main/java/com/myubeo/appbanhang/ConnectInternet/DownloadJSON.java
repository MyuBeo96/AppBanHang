package com.myubeo.appbanhang.ConnectInternet;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by as1 on 3/29/2018.
 */

public class DownloadJSON extends AsyncTask<String, Void, String> {

    String link;
    List<HashMap<String, String>> arrts;
    StringBuilder data;
    boolean check = true;

    public DownloadJSON(String link){
        this.link = link;
        check = true;
    }

    public DownloadJSON(String link, List<HashMap<String, String>> arrts){
        this.link = link;
        this.arrts = arrts;
        check = false;
    }

    @Override
    protected String doInBackground(String... strings) {
        String a = "";
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(!check){
                a = methodPOST(httpURLConnection);
            }else {
                a = methodGET(httpURLConnection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    private String methodGET(HttpURLConnection httpURLConnection){
        String duLieu = "";
        InputStream inputStream = null;
        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            data = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                data.append(line);
            }

            duLieu = data.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return duLieu;
    }

    private String methodPOST(HttpURLConnection httpURLConnection){
        String duLieu = "";
        String key = "";
        String value = "";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();

            int count = arrts.size();

            for (int i = 0; i < count; i++){
                for (Map.Entry<String, String> values: arrts.get(i).entrySet()){
                    key = values.getKey();
                    value = values.getValue();

                }

                builder.appendQueryParameter(key, value);
            }

            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(query);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            duLieu = methodGET(httpURLConnection);


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return duLieu;
    }
}
