package com.example.katakonst.licenta.AsyncTask.HashTags

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.HashTagsDao
import com.example.katakonst.licenta.JsonModels.Tracks

/**
 * Created by katakonst on 4/18/16.
 */



/**
 * Created by katakonst on 4/18/16.
 */



/**
 * Created by katakonst on 4/20/16.
 */

class GetTracksByHashTagTask(listView: ArrayAdapter<Tracks>,pHashId:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<Tracks>
    val HashId=pHashId
    val adapter=listView

    override fun doInBackground(vararg params: Void): Boolean? {
        mList = HashTagsDao().getTracksByHashTags(HashId)
        return true
    }

    override fun onPostExecute(success: Boolean?) {
        adapter.addAll(mList);
        adapter.notifyDataSetChanged();
    }

    override fun onCancelled() {

    }
}