package com.example.katakonst.licenta

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.example.katakonst.licenta.JsonModels.Tracks
import org.w3c.dom.Comment

/**
 * Created by katakonst on 3/5/16.
 */

class CommentsHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    val mIdView: TextView
    val mContentView: TextView
    internal lateinit var mItem: Comment

    init {
        mIdView = mView.findViewById(R.id.id) as TextView
        mContentView = mView.findViewById(R.id.content) as TextView
    }

    override fun toString(): String {
        return super.toString() + " '" + mContentView.text + "'"
    }
}
