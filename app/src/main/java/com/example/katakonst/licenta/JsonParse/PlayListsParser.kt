package com.example.katakonst.licenta.JsonParse

import android.util.Log
import com.example.katakonst.licenta.JsonModels.PlayLists
import org.json.JSONArray
import java.util.*

/**
 * Created by katakonst on 4/17/16.
 */

class PlayListsParser {
    fun getPlayLists(Json: String): List<PlayLists> {
        val playLists = ArrayList<PlayLists>()

        try {

            val jsArray = JSONArray(Json)
            for (i in 0..jsArray.length() - 1) {
                val jso = jsArray.getJSONObject(i)
                playLists.add(PlayLists(jso.getString(PlayLists.Id),
                        jso.getString(PlayLists.Authorid),
                        jso.getString(PlayLists.Nume),
                        jso.getString(PlayLists.Date)))
            }


        } catch (ex: Exception) {
            Log.d("s", ex.toString())
        }

        return playLists
    }
}