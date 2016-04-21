package com.example.katakonst.licenta.JsonParse

import android.util.Log
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.HashTags
import org.json.JSONArray
import java.util.*


/**
 * Created by katakonst on 4/18/16.
 */

class HashTagsParser {


    fun getHashTags(Json: String?): List<HashTags> {
        val hashTags = ArrayList<HashTags>()

        try {
            val jsArray = JSONArray(Json)

            for (i in 0..jsArray.length() - 1) {
                val jso = jsArray.getJSONObject(i)
                hashTags.add(HashTags(jso.getString(HashTags.Id),
                        jso.getString(HashTags.Description)))

            }


        } catch (ex: Exception) {
            Log.d("s", ex.toString())
        }

        return hashTags
    }
}