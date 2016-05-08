package com.example.katakonst.licenta.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.katakonst.licenta.AsyncTask.tracks.GetSimilarTracksTask
import com.example.katakonst.licenta.ItemDetailActivity
import com.example.katakonst.licenta.JsonModels.Tracks

import com.example.katakonst.licenta.R
import java.util.*

class SimilarTracks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_similar_tracks)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val trackList = findViewById(R.id.similarTracksList) as ListView
        var adaprter= ArrayAdapter<Tracks>(trackList.context,
                android.R.layout.simple_list_item_1,
                ArrayList<Tracks>());
        trackList.adapter=adaprter

        val args = this.intent.extras
        val trackId=args.getString(Tracks.Id)

        val uploaded= GetSimilarTracksTask(adaprter, trackId)
        uploaded.execute()


        trackList.setOnItemClickListener{

            parent: AdapterView<*>, view:View, position:Int,
            id:Long ->
            var intent= Intent(this, ItemDetailActivity::class.java);

            val selectedFromList =trackList.getItemAtPosition(position) as Tracks;
            var bundle=Bundle()
            bundle.putString(Tracks.Id,selectedFromList.id)
            bundle.putString(Tracks.Name,selectedFromList.name)
            bundle.putString(Tracks.Link,selectedFromList.link)
            bundle.putString(Tracks.PhotoLink,selectedFromList.photoLink)

            intent.putExtras(bundle)
            this.startActivity(intent)

        }

    }

}
