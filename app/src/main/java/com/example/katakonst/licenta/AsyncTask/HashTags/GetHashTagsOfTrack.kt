package com.example.katakonst.licenta.AsyncTask.HashTags

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.HashTagsDao
import com.example.katakonst.licenta.Dao.LikeDao
import com.example.katakonst.licenta.JsonModels.HashTags
import com.example.katakonst.licenta.JsonModels.Users

/**
 * Created by katakonst on 4/21/16.
 */



class GetHashTagsOfTrack(listView: ArrayAdapter<HashTags>,trackId:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<HashTags>
    val gTrackId=trackId
    val adapter=listView


    override fun doInBackground(vararg params: Void): Boolean? {
        mList = HashTagsDao().getHashTagsOfTrack(gTrackId);
        return true


    }

    override fun onPostExecute(success: Boolean?) {

        adapter.addAll(mList);
        adapter.notifyDataSetChanged();

    }

    override fun onCancelled() {

    }
}