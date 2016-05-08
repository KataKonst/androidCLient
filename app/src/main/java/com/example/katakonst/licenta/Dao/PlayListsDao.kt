package com.example.katakonst.licenta.Dao

import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.JsonModels.PlayLists
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.JsonParse.PlayListsParser
import com.example.katakonst.licenta.JsonParse.TrackParser
import com.example.katakonst.licenta.jsonRequests.TrackRequest
import org.apache.commons.codec.net.URLCodec

/**
 * Created by katakonst on 4/24/16.
 */
class PlayListsDao{


    fun getUserPlayList(userId:String?):List<PlayLists>
    {
        val playListsJson= TrackRequest(Constants.ip + "/userPlayLists?userId=" + URLCodec().encode(userId)).tracks
        var tracks= PlayListsParser().getPlayLists(playListsJson)
        return  tracks
    }

    fun PlayListTracks(userId:String?):List<Tracks>
    {
        val playListsJson= TrackRequest(Constants.ip + "/tracksofPlayList?playId=" + URLCodec().encode(userId)).tracks
        var tracks= TrackParser().getTracks(playListsJson)
        return  tracks
    }

}