package com.example.katakonst.licenta.AsyncTask

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log

import com.example.katakonst.licenta.ItemListActivity
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.JsonParse.TrackParser
import com.example.katakonst.licenta.Login
import com.example.katakonst.licenta.R
import com.example.katakonst.licenta.TrackLIst.SimpleItemRecyclerViewAdapter
import com.example.katakonst.licenta.jsonRequests.TrackRequest

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils

/**
 * Created by katakonst on 3/5/16.
 */
class TracksTask(private val mAdapter: SimpleItemRecyclerViewAdapter, private val mUrl: String) : AsyncTask<Void, Void, Boolean>() {
    internal lateinit var mList: List<Tracks>

    override fun doInBackground(vararg params: Void): Boolean? {
        val json = TrackRequest(mUrl).tracks
        mList = TrackParser().getTracks(json)
        return true


    }

    override fun onPostExecute(success: Boolean?) {

        for (tr in mList) {
            mAdapter.addItem(tr)


        }


    }

    override fun onCancelled() {

    }


}
