package com.example.katakonst.licenta.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.katakonst.licenta.AsyncTask.tracks.GetLikedTracksTask
import com.example.katakonst.licenta.AsyncTask.tracks.UserUploadedTracksTask
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
        var adaprter= ArrayAdapter<Tracks>(trackList.context,
                android.R.layout.simple_list_item_1,
                ArrayList<Tracks>());
        trackList.adapter=adaprter
        val uploaded= GetLikedTracksTask(adaprter, id)
        uploaded.execute()


    }

}
