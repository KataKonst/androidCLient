package com.example.katakonst.licenta.AsyncTask.LikeTasks

import android.os.AsyncTask
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.katakonst.licenta.Dao.LikeDao
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.Users

/**
 * Created by katakonst on 4/19/16.
 */


/**
 * Created by katakonst on 4/18/16.
 */




class ShowUsersWhoLikedTrack(listView: ArrayAdapter<Users>,trackId:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<Users>
    val gTrackId=trackId
    val adapter=listView


    override fun doInBackground(vararg params: Void): Boolean? {
        mList = LikeDao().getUsersWhoLikedTrack(gTrackId);
        return true


    }

    override fun onPostExecute(success: Boolean?) {

        adapter.addAll(mList);
        adapter.notifyDataSetChanged();

    }

    override fun onCancelled() {

    }
}
