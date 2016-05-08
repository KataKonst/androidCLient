package com.example.katakonst.licenta.AsyncTask.PlayLists

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.PlayListsDao
import com.example.katakonst.licenta.JsonModels.Tracks

/**
 * Created by katakonst on 4/24/16.
 */

class GetTracksOfPlayListTask(listView: ArrayAdapter<Tracks>,playId:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<Tracks>
    val gPlayId=playId
    val adapter=listView


    override fun doInBackground(vararg params: Void): Boolean? {
        mList = PlayListsDao().PlayListTracks(gPlayId);
        return true


    }

    override fun onPostExecute(success: Boolean?) {

        adapter.addAll(mList);
        adapter.notifyDataSetChanged();

    }

    override fun onCancelled() {

    }
}