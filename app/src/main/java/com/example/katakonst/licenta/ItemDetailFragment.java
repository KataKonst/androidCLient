package com.example.katakonst.licenta;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.katakonst.licenta.JsonModels.Tracks;
import com.example.katakonst.licenta.dummy.DummyContent;

import org.apache.commons.codec.net.URLCodec;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private  String nume;
    /**
     * The dummy content this fragment is presenting.
     */
    private Tracks mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    public String getNume()
    {
        return  nume;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
             mItem = (Tracks)getArguments().get("track");
             nume=getArguments().getString("nume");
           if(nume==null) {
               Bundle args = getActivity().getIntent().getExtras();
               nume = args.getString("nume");
           }
Log.d("s",nume);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(nume);
            }

            try {
                String url = "http://192.168.1.24:2000?test="+new URLCodec().encode(nume);
                Log.d("S",url);
                MediaPlayer mPlayer = new MediaPlayer();
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.setDataSource(url);
                mPlayer.prepare();
                mPlayer.start();
              //  mPlayer.seekTo(789);
            }
            catch(Exception ex){
                Log.d("d",ex.toString());

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (nume != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(nume);
        }
        else {
Log.d("null","null");
        }

        return rootView;
    }
}
