package com.example.katakonst.licenta.chat

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.Dao.LikeDao
import java.io.InputStream
import java.net.Socket

/**
 * Created by katakonst on 5/5/16.
 */






class SocketTask(textView: TextView?,act: AppCompatActivity,input: InputStream) : AsyncTask<Void, Void, Boolean>(){

    var likesNr="0"
    val gTextViw=textView
    val gAct=act;
    val gInput=input;


    override fun doInBackground(vararg params: Void): Boolean? {



        val buffer = ByteArray(1000);


         while(true)
         {
             val ct=gInput.read(buffer);
             val text=String(buffer,0,ct);
             gAct.runOnUiThread{
                 gTextViw!!.append(text);
             }


         }





    }

    override fun onPostExecute(success: Boolean?) {

        gTextViw!!.text=likesNr;

    }

    override fun onCancelled() {

    }
}