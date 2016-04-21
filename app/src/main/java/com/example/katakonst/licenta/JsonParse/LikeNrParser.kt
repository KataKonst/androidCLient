package com.example.katakonst.licenta.JsonParse

import android.util.Log
import com.example.katakonst.licenta.JsonModels.Like
import com.example.katakonst.licenta.JsonModels.LikeNr
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Created by katakonst on 4/17/16.
 */

class LikeNrParser {
    fun LikesNr(Json: String): List<LikeNr> {
        val likesNr = ArrayList<LikeNr>()

        try {

            val jsArray = JSONObject(Json)

                likesNr.add(LikeNr(jsArray.getString(LikeNr.Nr)))



        } catch (ex: Exception) {
            Log.d("s", ex.toString())
        }

        return likesNr
    }
}