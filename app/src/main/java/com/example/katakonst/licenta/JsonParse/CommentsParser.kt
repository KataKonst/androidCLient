package com.example.katakonst.licenta.JsonParse

import android.util.Log
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.Tracks
import org.json.JSONArray
import java.io.Serializable
import java.util.*

/**
 * Created by katakonst on 4/18/16.
 */

class CommentsParser {


    fun getComments(Json: String?): List<Comments> {
        val comments = ArrayList<Comments>()

        try {
            val jsArray = JSONArray(Json)

            for (i in 0..jsArray.length() - 1) {
                val jso = jsArray.getJSONObject(i)
                comments.add(Comments(jso.getString(Comments.Id),
                        jso.getString(Comments.Companion.Date),
                        jso.getString(Comments.Companion.Text),
                        jso.getString(Comments.Companion.PhotoLink),
                        jso.getString(Comments.Companion.AuthorId),
                        jso.getString(Comments.Companion.AuthorId)))

            }


        } catch (ex: Exception) {
            Log.d("s", ex.toString())
        }

        return comments
    }
}
