package com.example.katakonst.licenta.AsyncTask.tracks

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.TracksDao
import com.example.katakonst.licenta.JsonModels.Tracks

/**
 * Created by katakonst on 5/8/16.
 */
class GetTrackByMd5(pMd5:String?,pSave:(List<Tracks>)->Tracks) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<Tracks>
    val md5=pMd5
    val save=pSave

    override fun doInBackground(vararg params: Void): Boolean? {
        mList = TracksDao().getTrackByMd5(md5);
        return true
    }

    override fun onPostExecute(success: Boolean?) {
        save(mList);
    }

    override fun onCancelled() {

    }
}