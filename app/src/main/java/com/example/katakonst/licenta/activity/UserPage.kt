package com.example.katakonst.licenta.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.*
import androidinterview.com.customlistviewimagetext.TracksAdapter
import com.example.katakonst.licenta.AsyncTask.tracks.UserUploadedTracksTask
import com.example.katakonst.licenta.AsyncTask.users.DownloadImageTask
import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.ItemDetailActivity
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.JsonModels.Users

import com.example.katakonst.licenta.R
import java.util.*

class UserPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val args = this.intent.extras
        val id=args.getString(Users.id)
        val nume=args.getString(Users.nume)
        val photoLink=args.getString(Users.photoLink)
        val userText = findViewById(R.id.userText) as TextView
        userText.text=nume
        val photoView = findViewById(R.id.userPhoto) as ImageView

        val trackList = findViewById(R.id.uploadedTracks) as ListView

        var adaprter= TracksAdapter(this,
                ArrayList<Tracks>());
        trackList.adapter=adaprter
        val uploaded=UserUploadedTracksTask(adaprter,id)
        uploaded.execute()

        val down=DownloadImageTask(photoView,Constants.streamip+"/"+photoLink)
        down.execute()

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


        val showTracksLikedButton= findViewById(R.id.showTracksLiked) as Button
        var showLikedTracksIntent= Intent(this, UserLikedTraks::class.java);


        showTracksLikedButton.setOnClickListener {

            var bundle=Bundle()
            bundle.putString(Users.id,id)
            showLikedTracksIntent.putExtras(bundle)

            this.startActivity(showLikedTracksIntent)


        }

        var showUserIdIntent= Intent(this, UserPlayLists::class.java);


        val userPlayListsButton=findViewById(R.id.userPlayLists) as Button

        userPlayListsButton.setOnClickListener {
            var bundle=Bundle()
            bundle.putString(Users.id,id)
            showUserIdIntent.putExtras(bundle)

            this.startActivity(showUserIdIntent)

        }






    }

}
