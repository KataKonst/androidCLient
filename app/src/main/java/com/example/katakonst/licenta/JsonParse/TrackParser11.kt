package com.example.katakonst.licenta.JsonParse

import android.util.Log

import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.jsonRequests.TrackRequest

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

/**
 * Created by katakonst on 3/5/16.
 */
class TrackParser {


    fun getTracks(Json: String): List<Tracks> {
        val tracks = ArrayList<Tracks>()

        try {
            val jsArray = JSONArray(Json)
            for (i in 0..jsArray.length() - 1) {
                val jso = jsArray.getJSONObject(i)
                tracks.add(Tracks(jso.getString(Tracks.Companion.Name),
                        jso.getString(Tracks.Companion.Link),
                        jso.getString(Tracks.Companion.Id),
                        jso.getString(Tracks.Companion.PhotoLink)))

            }


        } catch (ex: Exception) {
            Log.d("s", ex.toString())
        }

        return tracks
    }
}
