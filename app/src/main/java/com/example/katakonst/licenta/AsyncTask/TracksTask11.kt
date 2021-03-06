package com.example.katakonst.licenta.AsyncTask

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.TracksDao
import com.example.katakonst.licenta.JsonModels.Tracks


/**
 * Created by katakonst on 3/5/16.
 */
class TracksTask(private val mAdapter: ArrayAdapter<Tracks>) : AsyncTask<Void, Void, Boolean>() {
    internal lateinit var mList: List<Tracks>
    val adapter=mAdapter






    override fun doInBackground(vararg params: Void): Boolean? {

        mList = TracksDao().getAllTracks()
        return true


    }

    override fun onPostExecute(success: Boolean?) {

        adapter.addAll(mList);
        adapter.notifyDataSetChanged();


    }

    override fun onCancelled() {

    }


}
