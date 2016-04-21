package com.example.katakonst.licenta.AsyncTask.tracks

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.CommentsDao
import com.example.katakonst.licenta.Dao.LikeDao
import com.example.katakonst.licenta.Dao.TracksDao
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.Tracks

/**
 * Created by katakonst on 4/20/16.
 */

class GetLikedTracksTask(pListView: ArrayAdapter<Tracks>,pUserId:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<Tracks>
    val UserId=pUserId
    val adapter=pListView

    override fun doInBackground(vararg params: Void): Boolean? {
        mList = LikeDao().getLikedTracksByUserk(UserId)
        return true
    }

    override fun onPostExecute(success: Boolean?) {
        adapter.addAll(mList);
        adapter.notifyDataSetChanged();
    }

    override fun onCancelled() {

    }
}