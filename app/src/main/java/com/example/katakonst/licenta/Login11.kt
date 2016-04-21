package com.example.katakonst.licenta

import android.util.Log

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import org.json.JSONArray
import org.json.JSONObject

import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

/**
 * Created by katakonst on 3/5/16.
 */
class Login {
    internal var url = "127.0.0.1:8080/nume="


    fun getPass(userName: String): String {
        try {
            val url = URL(Constants.ip + "/login?nume=" + userName)
            val conn = url.openConnection() as HttpURLConnection

            val `in` = InputStreamReader(conn.inputStream,
                    Charset.defaultCharset())
            val bufferedReader = BufferedReader(`in`)
            val sb = StringBuffer()
            if (bufferedReader != null) {
                var cp: Int
                cp=bufferedReader.read();
                while (cp != -1) {

                    sb.append(cp.toChar())
                    cp = bufferedReader.read();
                }
                bufferedReader.close()
            }

            return getMd5(sb.toString())
        } catch (ex: Exception) {
            Log.d("w", ex.toString())

        }

        return "ss"


    }


    fun getMd5(userJson: String): String {
        try {
            val jsonAr = JSONArray(userJson)

            //Iterate the jsonArray and print the info of JSONObjects

            for (i in 0..jsonAr.length() - 1) {
                Log.d("s", jsonAr.getJSONObject(i).getString("md5Hash"))
                return jsonAr.getJSONObject(i).getString("md5Hash")


            }
        } catch (ex: Exception) {
            Log.d("s", ex.toString())
        }

        return "not"

    }

}
