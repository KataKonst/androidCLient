package com.example.katakonst.licenta

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.katakonst.licenta.AsyncTask.LikeTasks.ShowUsersWhoLikedTrack
import com.example.katakonst.licenta.JsonModels.Comments

import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.JsonModels.Users
import com.example.katakonst.licenta.activity.UserPage
import java.util.*

class UsersWhoLikedTrack : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_who_liked_track)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val args = this.intent.extras
        val id=args.getString(Tracks.Id)
        val listView = findViewById(R.id.listView2) as ListView
        var adaprter= ArrayAdapter<Users>(listView.context,
                android.R.layout.simple_list_item_1,
                ArrayList<Users>());
        listView.adapter=adaprter
        var intent= Intent(this, UserPage::class.java);

        listView.setOnItemClickListener{
            parent: AdapterView<*>, view:View, position:Int,
            id:Long ->
            val selectedFromList =listView.getItemAtPosition(position) as Users;
            var bundle=Bundle()
            bundle.putString(Users.id,selectedFromList.id)
            bundle.putString(Users.photoLink,selectedFromList.photoLink)
            bundle.putString(Users.nume,selectedFromList.nume)
            intent.putExtras(bundle)
            this.startActivity(intent)

        }
        val getUsers=ShowUsersWhoLikedTrack(adaprter,id)
        getUsers.execute()
    }

}
