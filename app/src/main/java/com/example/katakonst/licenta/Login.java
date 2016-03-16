package com.example.katakonst.licenta;

import android.util.Log;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by katakonst on 3/5/16.
 */
public class Login {
String url="127.0.0.1:8080/nume=";


    public  String getPass(String userName)
    {
        try {
            URL url = new URL("http://192.168.1.102:8080/login?nume=" + userName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStreamReader   in = new InputStreamReader( conn.getInputStream(),
                    Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(in);
                StringBuffer sb=new StringBuffer();
            if (bufferedReader != null) {
                int cp;
                while ((cp = bufferedReader.read()) != -1) {
                    sb.append((char) cp);
                }
                bufferedReader.close();
            }

            return getMd5( sb.toString());
        }
        catch (Exception ex){
            Log.d("w",ex.toString());

        }

        return "ss";


    }


    public String getMd5(String userJson)
    {
        try {
            JSONArray jsonAr = new JSONArray(userJson);

            //Iterate the jsonArray and print the info of JSONObjects

            for(int i=0; i < jsonAr.length(); i++) {
                Log.d("s", jsonAr.getJSONObject(i).getString("md5Hash"));
                return  jsonAr.getJSONObject(i).getString("md5Hash");


            }
        }
        catch(Exception ex)
        {
            Log.d("s",ex.toString());
        }
        return  "not";

    }

}
