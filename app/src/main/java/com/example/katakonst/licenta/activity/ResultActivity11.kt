package com.example.katakonst.licenta.activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.katakonst.licenta.AsyncTask.TracksTask
import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.JsonParse.TrackParser
import com.example.katakonst.licenta.R
import com.example.katakonst.licenta.TrackLIst.SimpleItemRecyclerViewAdapter
import com.example.katakonst.licenta.jsonRequests.TrackRequest

import org.apache.commons.codec.net.URLCodec

import java.util.ArrayList

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ResultActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ResultActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.title = title

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }

        val recyclerView = findViewById(R.id.item_list)!!
        setupRecyclerView(recyclerView as RecyclerView)

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        var match: String? = ""
        val args = intent
        if (args != null)
            match = args.getStringExtra("match")
        else {
            Log.d("s", "null")
        }

        if (match == null) {
            Log.d("s", "boss")

            val pl = ArrayList<Tracks>()
            val adapter = SimpleItemRecyclerViewAdapter(pl, mTwoPane, this)
            recyclerView.adapter = adapter

            val task = TracksTask(adapter, Constants.ip + "/tracks")
            task.execute(null as Void)
        } else {
            Log.d("s", match)
            val pl = ArrayList<Tracks>()


        }


    }

}
