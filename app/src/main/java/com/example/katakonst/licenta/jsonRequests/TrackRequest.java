package com.example.katakonst.licenta.jsonRequests;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by katakonst on 3/5/16.
 */
public class TrackRequest {

    String mUrl;

     public TrackRequest(String url)
     {
         mUrl=url;
     }

    public  String getTracks()
    {
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStreamReader in = new InputStreamReader( conn.getInputStream(),
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

            return sb.toString();
        }
        catch (Exception ex){
            Log.d("w", ex.toString());

        }



return "error";

}
    }
