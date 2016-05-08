package com.example.katakonst.licenta.activity

import android.content.Intent
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.katakonst.licenta.AsyncTask.PlayLists.GetTracksOfPlayListTask
import com.example.katakonst.licenta.ItemDetailActivity
import com.example.katakonst.licenta.JsonModels.PlayLists
import com.example.katakonst.licenta.JsonModels.Tracks

import com.example.katakonst.licenta.R
import java.util.*

class PlayTracks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_tracks)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val args = this.intent.extras
        val id=args.getString(PlayLists.Id)

        val trackList = findViewById(R.id.playTracksList) as ListView
        var adaprter= ArrayAdapter<Tracks>(trackList.context,
                android.R.layout.simple_list_item_1,
                ArrayList<Tracks>());
        trackList.adapter=adaprter
        val uploaded= GetTracksOfPlayListTask(adaprter, id)
        uploaded.execute()

        var intent= Intent(this, ItemDetailActivity::class.java);

        trackList.setOnItemClickListener{
            parent: AdapterView<*>, view:View, position:Int,
            id:Long ->
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
