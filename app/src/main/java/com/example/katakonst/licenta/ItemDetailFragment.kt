package com.example.katakonst.licenta

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.katakonst.licenta.AsyncTask.Comments.ShowCommentsTask
import com.example.katakonst.licenta.AsyncTask.HashTags.GetHashTagsOfTrack
import com.example.katakonst.licenta.AsyncTask.ShowLikeNrTask
import com.example.katakonst.licenta.AsyncTask.users.DownloadImageTask
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.HashTags

import com.example.katakonst.licenta.JsonModels.Tracks

import org.apache.commons.codec.net.URLCodec
import java.util.*


class ItemDetailFragment : Fragment() {

    var nume: String? = null
    var id: String? = null
        private set
    var link: String? = null
    var photoLink:String?=null;
    private var tvw: View? = null
    private var mItem: Tracks? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


            val args = activity.intent.extras
            link = args.getString(Tracks.Companion.Link)
            nume = args.getString(Tracks.Companion.Name)
            id=args.getString(Tracks.Companion.Id).toString()
            photoLink=args.getString(Tracks.Companion.PhotoLink);
            Log.d("s", nume)
            val activity = this.activity

        try {
                val url = Constants.streamip + "/" + URLCodec().encode(link)
                Log.d("S", url)
                val mPlayer = MediaPlayer()
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mPlayer.setDataSource(url)
                mPlayer.prepare()
                val duration = mPlayer .duration
                val seekBar = this.activity.findViewById(R.id.seekBar3) as SeekBar
                val likeText = this.activity.findViewById(R.id.LikeNrText) as TextView
                val task = ShowLikeNrTask(likeText, id)
                task.execute()
                val playBut = this.activity.findViewById(R.id.button) as Button
                val likeButton = this.activity.findViewById(R.id.LikeButton) as Button
                val trackPhoto = this.activity.findViewById(R.id.trackPhoto) as ImageView
                val trackPhotoThread=DownloadImageTask(trackPhoto,Constants.streamip+"/"+photoLink)
                trackPhotoThread.execute();
               var hashTagList=this.activity.findViewById(R.id.TrackHashTags) as ListView

            var hashTagAdapter=ArrayAdapter<HashTags>(hashTagList.context,
                    android.R.layout.simple_list_item_1,
                    ArrayList<HashTags>());
            hashTagList.adapter=hashTagAdapter

            val hashTagTask = GetHashTagsOfTrack(hashTagAdapter, id)
            hashTagTask.execute()




            likeButton.setOnClickListener{
                var intent=Intent(this.context, UsersWhoLikedTrack::class.java);
                var bundle=Bundle()
                bundle.putString(Tracks.Id,id)
                intent.putExtras(bundle)
                this.activity.startActivity(intent)

             }

                playBut.setOnClickListener {

                    if (playBut.text == "Play") {
                        playBut.text = "Pause"

                        mPlayer.start()
                    } else {
                        mPlayer.pause()
                        playBut.text = "Play"

                    }
                }
                var list=this.activity.findViewById(R.id.listView3) as ListView

                var adaprter=ArrayAdapter<Comments>(list.context,
                        android.R.layout.simple_list_item_1,
                        ArrayList<Comments>());
                list.adapter=adaprter

                val commentsTask = ShowCommentsTask(adaprter, id)
                commentsTask.execute()



                seekBar.max = duration
                seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    internal var progress = 0

                    override fun onProgressChanged(seekBar: SeekBar, progresValue: Int, fromUser: Boolean) {
                        progress = progresValue
                        Log.d("progress", progress.toString())
                        mPlayer.seekTo(progress)

                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {
                    }
                })








                //  mPlayer.seekTo(789);
            } catch (ex: Exception) {
                Log.d("d", ex.toString())

            }

        }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.item_detail, container, false)
        tvw = rootView
        Log.d("bossss", tvw.toString())



        if (nume != null) {
            (rootView.findViewById(R.id.item_detail) as TextView).text = nume
        } else {
            Log.d("null", "null")
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        val ARG_ITEM_ID = "item_id"
    }
}
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
