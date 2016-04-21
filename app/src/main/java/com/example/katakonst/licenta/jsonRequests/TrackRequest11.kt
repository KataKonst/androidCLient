package com.example.katakonst.licenta.jsonRequests

import android.util.Log

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

/**
 * Created by katakonst on 3/5/16.
 */
class TrackRequest(internal var mUrl:

                   String) {

    val tracks: String
        get() {
            try {
                val url = URL(mUrl)
                Log.d("ss", mUrl)
                val conn = url.openConnection() as HttpURLConnection

                val `in` = InputStreamReader(conn.inputStream,
                        Charset.defaultCharset())
                val bufferedReader = BufferedReader(`in`)
                val sb = StringBuffer()
                if (bufferedReader != null) {
                    var cp: Int=bufferedReader.read()
                    while ((cp) != -1) {

                        sb.append(cp.toChar())
                        cp = bufferedReader.read()

                    }
                    bufferedReader.close()
                }

                return sb.toString()
            } catch (ex: Exception) {
                Log.d("w", ex.toString())

            }



            return "error"

        }
}
