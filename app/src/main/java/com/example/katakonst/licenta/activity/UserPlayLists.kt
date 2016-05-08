package com.example.katakonst.licenta.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.katakonst.licenta.AsyncTask.PlayLists.UserPlayListTask
import com.example.katakonst.licenta.JsonModels.PlayLists
import com.example.katakonst.licenta.JsonModels.Users

import com.example.katakonst.licenta.R
import java.util.*

class UserPlayLists : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_play_lists)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val args = this.intent.extras
        val id=args.getString(Users.id)

        val trackList = findViewById(R.id.userPlayLists) as ListView
        var adaprter= ArrayAdapter<PlayLists>(trackList.context,
                android.R.layout.simple_list_item_1,
                ArrayList<PlayLists>());
        trackList.adapter=adaprter
        val uploaded= UserPlayListTask(adaprter, id)
        uploaded.execute()

        var tracksIntent= Intent(this, PlayTracks::class.java);


        trackList.setOnItemClickListener{
            parent: AdapterView<*>, view:View, position:Int,
            id:Long ->
            val selectedFromList =trackList.getItemAtPosition(position) as PlayLists;
            var bundle=Bundle()
            bundle.putString(PlayLists.Id,selectedFromList.id)
            tracksIntent.putExtras(bundle)
            this.startActivity(tracksIntent)

        }


    }

}
