package com.example.katakonst.licenta.chat

import android.os.AsyncTask
import android.util.Log
import java.io.OutputStream

/**
 * Created by katakonst on 5/5/16.
 */





class SendMessageTask(message:String,out: OutputStream) : AsyncTask<Void, Void, Boolean>(){

  val gMessage=message
    val gOut=out


    override fun doInBackground(vararg params: Void): Boolean? {

        Log.d("ss","boss");
        Log.d("ss","sss");
        gOut.write(gMessage.toByteArray());
        gOut.flush()

        return  true

    }

    override fun onPostExecute(success: Boolean?) {


    }

    override fun onCancelled() {

    }
}