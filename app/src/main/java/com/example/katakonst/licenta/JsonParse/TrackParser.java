package com.example.katakonst.licenta.JsonParse;

import android.util.Log;

import com.example.katakonst.licenta.JsonModels.Tracks;
import com.example.katakonst.licenta.jsonRequests.TrackRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katakonst on 3/5/16.
 */
public class TrackParser {




    public  List<Tracks> getTracks(String Json)
    {
        List<Tracks> tracks = new ArrayList<Tracks>();

        try{
        JSONArray jsArray=new JSONArray(Json);

            for(int i=0;i<jsArray.length();i++)
            {
                JSONObject jso=jsArray.getJSONObject(i);
                tracks.add(new Tracks(jso.getString("nume"),jso.getString("link"),i));

            }


            }
        catch(Exception ex)
        {
            Log.d("s",ex.toString());
        }

        return  tracks;
    }
}
