package com.example.katakonst.licenta.AsyncTask.tracks

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.TracksDao
import com.example.katakonst.licenta.JsonModels.Tracks

/**
 * Created by katakonst on 4/27/16.
 */


class GetSimilarTracksTask(pListView: ArrayAdapter<Tracks>,pTrackId:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<Tracks>
    val TrackId=pTrackId
    val adapter=pListView

    override fun doInBackground(vararg params: Void): Boolean? {
        mList = TracksDao().getSimilarTracks(TrackId)
        return true
    }

    override fun onPostExecute(success: Boolean?) {
        adapter.addAll(mList);
        adapter.notifyDataSetChanged();
    }

    override fun onCancelled() {

    }
}