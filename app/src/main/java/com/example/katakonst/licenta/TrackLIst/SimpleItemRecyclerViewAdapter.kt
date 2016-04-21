package com.example.katakonst.licenta.TrackLIst

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.katakonst.licenta.ItemDetailActivity
import com.example.katakonst.licenta.ItemDetailFragment
import com.example.katakonst.licenta.ItemListActivity
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.R
import com.example.katakonst.licenta.ViewHolder

/**
 * Created by katakonst on 3/5/16.
 */
class SimpleItemRecyclerViewAdapter(private val mValues: MutableList<Tracks>, private val mTwoPane: Boolean, private val mActivity: AppCompatActivity) : RecyclerView.Adapter<ViewHolder>() {


    fun addItem(track: Tracks) {
        mValues.add(track)
        notifyItemChanged(mValues.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mContentView.text = mValues[position].name

        holder.mView.setOnClickListener { v ->
            if (mTwoPane) {
                val arguments = Bundle()
                arguments.putString(Tracks.Companion.Id, holder.mItem.id.toString())
                arguments.putString(Tracks.Companion.Name, holder.mItem.name)
                arguments.putString(Tracks.Companion.Link, holder.mItem.link)
                arguments.putString(Tracks.Companion.Id, holder.mItem.id)

                Log.d("s", holder.mItem.name)
                // arguments.putExtra("track", holder.mItem);

                val fragment = ItemDetailFragment()
                fragment.arguments = arguments
                mActivity.supportFragmentManager.beginTransaction().replace(R.id.item_detail_container, fragment).commit()
            } else {
                Log.d("s", holder.mItem.name)

                val context = v.context
                val intent = Intent(context, ItemDetailActivity::class.java)
                intent.putExtra(Tracks.Companion.Id, holder.mItem.id.toString())
                intent.putExtra(Tracks.Companion.Name, holder.mItem.name)
                intent.putExtra(Tracks.Companion.Link, holder.mItem.link)
                intent.putExtra(Tracks.Companion.Id, holder.mItem.id)
                intent.putExtra(Tracks.Companion.PhotoLink, holder.mItem.photoLink)

                intent.putExtra("track", holder.mItem)




                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }


}
