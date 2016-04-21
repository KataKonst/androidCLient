package com.example.katakonst.licenta.AsyncTask.tracks

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.TracksDao
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.TrackLIst.SimpleItemRecyclerViewAdapter

/**
 * Created by katakonst on 4/21/16.
 */

class SearchTrackTask(private val mAdapter: SimpleItemRecyclerViewAdapter,pSearchString:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<Tracks>
    val searchString=pSearchString
    val adapter=mAdapter

    override fun doInBackground(vararg params: Void): Boolean? {
        mList = TracksDao().searchTracks(searchString)
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