package com.example.katakonst.licenta.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ListView
import androidinterview.com.customlistviewimagetext.TracksAdapter
import com.example.katakonst.licenta.AsyncTask.tracks.GetLikedTracksTask
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.JsonModels.Users

import com.example.katakonst.licenta.R
import java.util.*

class UserLikedTraks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_liked_traks)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val args = this.intent.extras
        val id=args.getString(Users.id)


        val trackList = findViewById(R.id.userLikedTracks) as ListView
        var adaprter= TracksAdapter(this,
                ArrayList<Tracks>());
        trackList.adapter=adaprter
        val uploaded= GetLikedTracksTask(adaprter, id)
        uploaded.execute()


    }

}
