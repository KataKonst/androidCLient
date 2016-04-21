package com.example.katakonst.licenta.JsonParse

import android.util.Log
import com.example.katakonst.licenta.JsonModels.Like
import com.example.katakonst.licenta.JsonModels.Tracks
import org.json.JSONArray
import java.util.*

/**
 * Created by katakonst on 4/17/16.
 */

class LikeParser {
    fun getTracks(Json: String): List<Like> {
        val likes = ArrayList<Like>()

        try {

            val jsArray = JSONArray(Json)
            for (i in 0..jsArray.length() - 1) {
                val jso = jsArray.getJSONObject(i)
                likes.add(Like(jso.getString(Like.Authorid), jso.getString(Like.TrackId),jso.getString(Like.Id)))
            }


        } catch (ex: Exception) {
            Log.d("s", ex.toString())
        }

        return likes
    }
}