package com.example.katakonst.licenta.AsyncTask.Comments

import android.os.AsyncTask
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.katakonst.licenta.Dao.CommentsDao
import com.example.katakonst.licenta.Dao.LikeDao
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.Tracks

/**
 * Created by katakonst on 4/18/16.
 */



/**
 * Created by katakonst on 4/18/16.
 */




class ShowCommentsTask(listView:ArrayAdapter<Comments>,trackId:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<Comments>
    val gTrackId=trackId
    val adapter=listView

    override fun doInBackground(vararg params: Void): Boolean? {
        mList = CommentsDao().getComments(gTrackId);
        return true


    }

    override fun onPostExecute(success: Boolean?) {
        adapter.addAll(mList);
        adapter.notifyDataSetChanged();
    }

    override fun onCancelled() {

    }
}
