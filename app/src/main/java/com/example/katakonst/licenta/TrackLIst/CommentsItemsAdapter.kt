package com.example.katakonst.licenta.TrackLIst

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.katakonst.licenta.*
import com.example.katakonst.licenta.JsonModels.Tracks

/**
 * Created by katakonst on 4/18/16.
 */

class CommentsItemsAdapter(private val mValues: MutableList<Tracks>, private val mTwoPane: Boolean, private val mActivity: AppCompatActivity) : RecyclerView.Adapter<CommentsHolder>() {


    fun addItem(track: Tracks) {
        mValues.add(track)
        notifyItemChanged(mValues.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_content, parent, false)
        return CommentsHolder(view)

    }

    override fun onBindViewHolder(holder: CommentsHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mValues.size
    }
}