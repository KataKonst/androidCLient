package com.example.katakonst.licenta.AsyncTask.HashTags

import android.os.AsyncTask
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.katakonst.licenta.Dao.CommentsDao
import com.example.katakonst.licenta.Dao.HashTagsDao
import com.example.katakonst.licenta.Dao.LikeDao
import com.example.katakonst.licenta.Dao.TracksDao
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.HashTags
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.TrackLIst.SimpleItemRecyclerViewAdapter

/**
 * Created by katakonst on 4/18/16.
 */



/**
 * Created by katakonst on 4/18/16.
 */



/**
 * Created by katakonst on 4/20/16.
 */

class GetTracksByHashTagTask(private val mAdapter: SimpleItemRecyclerViewAdapter,pHashId:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<Tracks>
    val HashId=pHashId
    val adapter=mAdapter

    override fun doInBackground(vararg params: Void): Boolean? {
        mList = HashTagsDao().getTracksByHashTags(HashId)
        return true
    }

    override fun onPostExecute(success: Boolean?) {
        for(trk in mList){
            adapter.addItem(trk);
        }
        adapter.notifyDataSetChanged();
    }

    override fun onCancelled() {

    }
}