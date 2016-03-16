package com.example.katakonst.licenta.TrackLIst;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katakonst.licenta.ItemDetailActivity;
import com.example.katakonst.licenta.ItemDetailFragment;
import com.example.katakonst.licenta.ItemListActivity;
import com.example.katakonst.licenta.JsonModels.Tracks;
import com.example.katakonst.licenta.R;
import com.example.katakonst.licenta.ViewHolder;

import java.util.List;

/**
 * Created by katakonst on 3/5/16.
 */
public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<ViewHolder> {

    private final List<Tracks> mValues;
    private boolean mTwoPane;
    private AppCompatActivity mActivity;




    public SimpleItemRecyclerViewAdapter(List<Tracks> items,boolean twpPane,AppCompatActivity activity) {
        mValues = items;
        mTwoPane=twpPane;
        mActivity=activity;
    }



    public void addItem(final Tracks track) {
        mValues.add(track);
        notifyItemChanged(mValues.size());
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).getLink());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, String.valueOf(holder.mItem.getId()));
                    arguments.putString("nume", holder.mItem.getName());
                    arguments.putString("link", holder.mItem.getLink());
                    Log.d("s", holder.mItem.getName());
                   // arguments.putExtra("track", holder.mItem);

                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Log.d("s", holder.mItem.getName());

                    Context context = v.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, String.valueOf(holder.mItem.getId()));
                    intent.putExtra("nume", holder.mItem.getName());
                    intent.putExtra("link", holder.mItem.getLink());
                    intent.putExtra("track", holder.mItem);




                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}
