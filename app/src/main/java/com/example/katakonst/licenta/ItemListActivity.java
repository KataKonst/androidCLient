package com.example.katakonst.licenta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.katakonst.licenta.AsyncTask.TracksTask;
import com.example.katakonst.licenta.JsonModels.Tracks;
import com.example.katakonst.licenta.JsonParse.TrackParser;
import com.example.katakonst.licenta.TrackLIst.SimpleItemRecyclerViewAdapter;
import com.example.katakonst.licenta.dummy.DummyContent;
import com.example.katakonst.licenta.jsonRequests.TrackRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {

        String  match="";
        Intent args = getIntent();
        if(args!=null)
          match = args.getStringExtra("match");
        else
        {
            Log.d("s","null");
        }

        if(match==null) {
            Log.d("s","Sssss");

            List<Tracks> pl = new ArrayList<Tracks>();
            pl.add(new Tracks("dd", "dd", 1));
            SimpleItemRecyclerViewAdapter adapter = new SimpleItemRecyclerViewAdapter(pl, mTwoPane, this);
            recyclerView.setAdapter(adapter);
            TracksTask task = new TracksTask(adapter,"http://192.168.1.24:8080/tracks");
            task.execute((Void) null);
        }
        else{
            Log.d("s",match);
            List<Tracks> pl = new ArrayList<Tracks>();
            pl.add(new Tracks("dd", "dd", 1));
            SimpleItemRecyclerViewAdapter adapter = new SimpleItemRecyclerViewAdapter(pl, mTwoPane, this);
            recyclerView.setAdapter(adapter);
            TracksTask task = new TracksTask(adapter,"http://192.168.1.24:8080/tracks");
            task.execute((Void) null);

        }




    }

}
