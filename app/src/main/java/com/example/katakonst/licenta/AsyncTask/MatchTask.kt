package com.example.katakonst.licenta.AsyncTask

import android.os.AsyncTask
import android.widget.TextView
import com.example.katakonst.licenta.Dao.LikeDao

/**
 * Created by katakonst on 4/24/16.
 */



class MatchTask(textView: TextView?,trackId:String?) : AsyncTask<Void, Void, Boolean>(){

    var likesNr="0"
    val gTextViw=textView
    val gTrackId=trackId


    override fun doInBackground(vararg params: Void): Boolean? {
        likesNr = LikeDao().getLikesNr(gTrackId).nr;
        return true


    }

    override fun onPostExecute(success: Boolean?) {

        gTextViw!!.text=likesNr;

    }

    override fun onCancelled() {

    }
}