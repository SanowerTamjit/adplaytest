package com.santam.adplaytest.util;

import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class httpconnector {

    public static String getRequest(String urlStr){

        HttpURLConnection request = null;
        URL url = null;

        try {

            url = new URL(urlStr);
            request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            StringBuilder content = getStringBuilder(request);

            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }finally {
            request.disconnect();
        }

    }

    public static String getRequestHttpGet(String urlStr){

        HttpClient httpclient = null;
        HttpGet httpGet;

        try {

            httpclient = new DefaultHttpClient();
            httpGet = new HttpGet(urlStr);

//Execute and get the response.
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String line = "";
            if(response.getStatusLine().getStatusCode() == 200){

                BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
                StringBuilder content= new StringBuilder();

                if((line = rd.readLine()) != null){
                    content.append(line);
                    content.append(System.lineSeparator());
                }
                return content.toString();
            }else{
                return "";
            }
//            return new InputStreamReader((InputStream) request.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }finally {
            ((DefaultHttpClient) httpclient).close();
        }

    }

    private static StringBuilder getStringBuilder(HttpURLConnection request) throws IOException {
        StringBuilder content;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(request.getInputStream()))) {

            String line;
            content = new StringBuilder();

            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        }
        return content;
    }

    public static String postRequest(String urlStr, String urlParameters){

        HttpURLConnection request = null;
        URL url = null;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            url = new URL(urlStr);
            request = (HttpURLConnection) url.openConnection();
            request.setDoOutput(true);
            request.setRequestMethod("POST");
            request.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
            request.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(request.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content = getStringBuilder(request);

            return content.toString();
//            return new InputStreamReader((InputStream) request.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }finally {
            request.disconnect();
        }

    }


    public static String postRequest(String urlStr, List<NameValuePair> params ){
        HttpClient httpclient = null;
        HttpPost httppost;

        try {

             httpclient = new DefaultHttpClient();
            httppost = new HttpPost(urlStr);
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String line = "";
            JsonObject jsonObject = new JsonObject();
            BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));


            StringBuilder content= new StringBuilder();

            if((line = rd.readLine()) != null){
                content.append(line);
                content.append(System.lineSeparator());
            }

            return content.toString();
//            return new InputStreamReader((InputStream) request.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }finally {
            ((DefaultHttpClient) httpclient).close();
        }

    }

}