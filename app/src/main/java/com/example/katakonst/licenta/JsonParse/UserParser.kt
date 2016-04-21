package com.example.katakonst.licenta.JsonParse

import android.util.Log
import com.example.katakonst.licenta.JsonModels.Like
import com.example.katakonst.licenta.JsonModels.Users
import org.json.JSONArray
import java.util.*

/**
 * Created by katakonst on 4/19/16.
 */

class UserPaser {
    fun getUsers(Json: String): List<Users> {
        val users = ArrayList<Users>()

        try {

            val jsArray = JSONArray(Json)
            for (i in 0..jsArray.length() - 1) {
                val jso = jsArray.getJSONObject(i)
                users.add(Users(jso.getString(Users.id), jso.getString(Users.nume), jso.getString(Users.photoLink)))
            }


        } catch (ex: Exception) {
            Log.d("s", ex.toString())
        }

        return users
    }
}