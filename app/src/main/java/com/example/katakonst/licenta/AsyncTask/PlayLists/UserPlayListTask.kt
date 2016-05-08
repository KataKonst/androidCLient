package com.example.katakonst.licenta.AsyncTask.PlayLists

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.PlayListsDao
import com.example.katakonst.licenta.JsonModels.PlayLists

/**
 * Created by katakonst on 4/24/16.
 */

class UserPlayListTask(listView: ArrayAdapter<PlayLists>,userId:String?) : AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<PlayLists>
    val gUserId=userId
    val adapter=listView


    override fun doInBackground(vararg params: Void): Boolean? {
        mList = PlayListsDao().getUserPlayList(gUserId);
        return true


    }

    override fun onPostExecute(success: Boolean?) {

        adapter.addAll(mList);
        adapter.notifyDataSetChanged();

    }

    override fun onCancelled() {

    }
}