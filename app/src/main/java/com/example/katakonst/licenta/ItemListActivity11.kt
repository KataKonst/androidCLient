package com.example.katakonst.licenta

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.katakonst.licenta.AsyncTask.HashTags.GetTracksByHashTagTask
import com.example.katakonst.licenta.AsyncTask.HashTags.AllHashTagsTask


import com.example.katakonst.licenta.AsyncTask.TracksTask
import com.example.katakonst.licenta.AsyncTask.tracks.SearchTrackTask
import com.example.katakonst.licenta.JsonModels.HashTags
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.TrackLIst.SimpleItemRecyclerViewAdapter

import java.util.ArrayList

class ItemListActivity : AppCompatActivity() {

    private var mTwoPane: Boolean = false


    override fun onCreateOptionsMenu(menu:Menu):Boolean {
        super.onCreateOptionsMenu(menu);
        val inflater=getMenuInflater();

        inflater.inflate(R.menu.options_menu, menu);
        Log.d("ss","inf")

        return true;

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)


        this.openOptionsMenu();
        val recyclerView = findViewById(R.id.item_list)!!
        setupRecyclerView(recyclerView as RecyclerView)


        val trackList = findViewById(R.id.hashTagsList) as ListView
        var adaprter= ArrayAdapter<HashTags>(trackList.context,
                android.R.layout.simple_list_item_1,
                ArrayList<HashTags>());
        trackList.adapter=adaprter
        val uploaded= AllHashTagsTask(adaprter)
        uploaded.execute()


        trackList.setOnItemClickListener{
            parent: AdapterView<*>, view:View, position:Int,
            id:Long ->
            val selectedFromList =trackList.getItemAtPosition(position) as HashTags;
            val pl = ArrayList<Tracks>()
            val adapter = SimpleItemRecyclerViewAdapter(pl, mTwoPane, this@ItemListActivity)
            recyclerView.adapter = adapter
            val searchTracks= GetTracksByHashTagTask(adapter, selectedFromList.id)
            searchTracks.execute()



        }



        val fb = findViewById(R.id.fab) as SearchView
        fb.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                callSearch(query);
                return true;
            }


            override fun onQueryTextChange(newText: String): Boolean {

//                callSearch(newText);

                return true;
            }

            fun callSearch(query: String): Unit {

                val pl = ArrayList<Tracks>()
                val adapter = SimpleItemRecyclerViewAdapter(pl, mTwoPane, this@ItemListActivity)
                recyclerView.adapter = adapter

                val searchTracks= SearchTrackTask(adapter, query)
                searchTracks.execute()
            }


        });
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
            Log.d("s", "Sssss")

            val pl = ArrayList<Tracks>()
            val adapter = SimpleItemRecyclerViewAdapter(pl, mTwoPane, this)
            recyclerView.adapter = adapter
            val task = TracksTask(adapter, Constants.ip + "/tracks")
            task.execute()
        } else {
            Log.d("s", match)
            val pl = ArrayList<Tracks>()
            val adapter = SimpleItemRecyclerViewAdapter(pl, mTwoPane, this)
            recyclerView.adapter = adapter
            val task = TracksTask(adapter, Constants.ip + "/tracks")
            task.execute()

        }


    }

}
