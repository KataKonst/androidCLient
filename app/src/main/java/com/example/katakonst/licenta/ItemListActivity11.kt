package com.example.katakonst.licenta

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidinterview.com.customlistviewimagetext.TracksAdapter
import com.example.katakonst.licenta.AsyncTask.HashTags.GetTracksByHashTagTask
import com.example.katakonst.licenta.AsyncTask.HashTags.AllHashTagsTask
import com.example.katakonst.licenta.AsyncTask.TracksTask
import com.example.katakonst.licenta.AsyncTask.tracks.SearchTrackTask
import com.example.katakonst.licenta.JsonModels.HashTags
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.activity.ChooseNameActivity
import com.example.katakonst.licenta.activity.MatchActivity

import java.util.ArrayList

class ItemListActivity : AppCompatActivity() {



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
        val match_Act = findViewById(R.id.shaz_Act) as Button

        match_Act.setOnClickListener {
            var intent= Intent(this, MatchActivity::class.java);
            this.startActivity(intent)
        }


        val chat_but = findViewById(R.id.chatButton) as Button

        chat_but.setOnClickListener {
            var intent3= Intent(this, ChooseNameActivity::class.java);
            this.startActivity(intent3)
        }



        val trackList = findViewById(R.id.hashTagsList) as ListView
        var adaprter= ArrayAdapter<HashTags>(trackList.context,
                android.R.layout.simple_list_item_1,
                ArrayList<HashTags>());
        trackList.adapter=adaprter
        val uploaded= AllHashTagsTask(adaprter)
        uploaded.execute()



        val startTracks= findViewById(R.id.startList) as ListView
        var startTracksAdapter= TracksAdapter(this,
                ArrayList<Tracks>());
        startTracks.adapter=startTracksAdapter
        val start= TracksTask(startTracksAdapter)
        start.execute()

        var trackDetailsIntent= Intent(this, ItemDetailActivity::class.java);

          startTracks.setOnItemClickListener{
            parent: AdapterView<*>, view:View, position:Int,
            id:Long ->
            val selectedFromList =startTracks.getItemAtPosition(position) as Tracks;
            var bundle=Bundle()
            bundle.putString(Tracks.Id,selectedFromList.id)
            bundle.putString(Tracks.Name,selectedFromList.name)
            bundle.putString(Tracks.Link,selectedFromList.link)
            bundle.putString(Tracks.PhotoLink,selectedFromList.photoLink)

            trackDetailsIntent.putExtras(bundle)
            this.startActivity(trackDetailsIntent)

        }



        trackList.setOnItemClickListener{
            parent: AdapterView<*>, view:View, position:Int,
            id:Long ->
            val selectedFromList =trackList.getItemAtPosition(position) as HashTags;
            val pl = ArrayList<Tracks>()
            startTracksAdapter.clear();
            val searchTracks= GetTracksByHashTagTask( startTracksAdapter, selectedFromList.id)
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

                startTracksAdapter.clear();
                val startTask= SearchTrackTask(startTracksAdapter,query)
                startTask.execute()
            }


        });
    }




}
