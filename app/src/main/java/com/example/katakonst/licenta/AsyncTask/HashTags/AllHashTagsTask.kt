package com.example.katakonst.licenta.AsyncTask.HashTags

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.example.katakonst.licenta.Dao.CommentsDao
import com.example.katakonst.licenta.Dao.HashTagsDao
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.HashTags

/**
 * Created by katakonst on 4/21/16.
 */


class AllHashTagsTask(listView: ArrayAdapter<HashTags> ): AsyncTask<Void, Void, Boolean>(){

    internal lateinit var mList: List<HashTags>
    val adapter=listView

    override fun doInBackground(vararg params: Void): Boolean? {
        mList = HashTagsDao().getHashTags()
        return true


    }

    override fun onPostExecute(success: Boolean?) {
        adapter.addAll(mList);
        adapter.notifyDataSetChanged();
    }

    override fun onCancelled() {

    }
}
