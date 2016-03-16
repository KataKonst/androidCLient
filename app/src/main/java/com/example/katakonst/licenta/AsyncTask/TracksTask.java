package com.example.katakonst.licenta.AsyncTask;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.katakonst.licenta.ItemListActivity;
import com.example.katakonst.licenta.JsonModels.Tracks;
import com.example.katakonst.licenta.JsonParse.TrackParser;
import com.example.katakonst.licenta.Login;
import com.example.katakonst.licenta.R;
import com.example.katakonst.licenta.TrackLIst.SimpleItemRecyclerViewAdapter;
import com.example.katakonst.licenta.jsonRequests.TrackRequest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * Created by katakonst on 3/5/16.
 */
public class TracksTask extends AsyncTask<Void, Void, Boolean> {
    RecyclerView  mRecyCler;
    List<Tracks> mList;
    private  boolean mTwoPane;
    private  SimpleItemRecyclerViewAdapter mAdapter;
    private String mUrl;





    public TracksTask(@NonNull final SimpleItemRecyclerViewAdapter adapter,String url)
    {

        mAdapter=adapter;
        mUrl=url;

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        String json= new TrackRequest(mUrl).getTracks();
        mList=new TrackParser().getTracks(json);
        return  true;


    }

    @Override
    protected void onPostExecute(final Boolean success) {

        for (Tracks tr:mList
             ) {
            mAdapter.addItem(tr);


        }


    }

    @Override
    protected void onCancelled() {

    }



}
