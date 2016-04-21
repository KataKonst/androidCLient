package com.example.katakonst.licenta.AsyncTask.users

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.katakonst.licenta.Dao.LikeDao
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.Users
import java.io.InputStream

/**
 * Created by katakonst on 4/19/16.
 */

class DownloadImageTask(pImageView:ImageView,pUrl:String?) : AsyncTask<Void, Void, Boolean>() {
    val ImageView=pImageView
    val Url=pUrl
    internal lateinit var bit: Bitmap



    override fun doInBackground(vararg params: Void): Boolean? {
            bit = BitmapFactory.decodeStream(java.net.URL(Url).openStream());

        return true;



    }

    override fun onPostExecute(success: Boolean?) {

        ImageView.setImageBitmap(bit);


    }

    override fun onCancelled() {

    }

}
