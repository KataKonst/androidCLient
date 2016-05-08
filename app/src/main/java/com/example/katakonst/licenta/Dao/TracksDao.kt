package com.example.katakonst.licenta.Dao

import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.JsonParse.TrackParser
import com.example.katakonst.licenta.jsonRequests.TrackRequest
import org.apache.commons.codec.net.URLCodec

/**
 * Created by katakonst on 4/20/16.
 */

class TracksDao{


    fun getTracksUploadedByUser(userId:String?):List<Tracks>
    {
        val tracksJson= TrackRequest(Constants.ip + "/userUploadedTracks?userId=" + URLCodec().encode(userId)).tracks
        var tracks=TrackParser().getTracks(tracksJson)
        return  tracks
    }

    fun searchTracks(searchString:String?):List<Tracks>
    {
        val tracksJson= TrackRequest(Constants.ip + "/search?nume=" + URLCodec().encode(searchString)).tracks
        var tracks=TrackParser().getTracks(tracksJson)
        return  tracks
    }

    fun getSimilarTracks(trackId:String?):List<Tracks>
    {
        val similarTracksJson= TrackRequest(Constants.ip + "/matchTrack?trackId=" + URLCodec().encode(trackId)).tracks
        var tracks=TrackParser().getTracks(similarTracksJson)
        return  tracks
    }
    fun getAllTracks():List<Tracks>
    {
        val similarTracksJson= TrackRequest(Constants.ip + "/tracks").tracks
        var tracks=TrackParser().getTracks(similarTracksJson)
        return  tracks
    }

    fun getTrackByMd5(md5:String?):List<Tracks>
    {
        val similarTracksJson= TrackRequest(Constants.ip + "/getTrackByMd5?md5="+md5).tracks
        var tracks=TrackParser().getTracks(similarTracksJson)
        return  tracks
    }





}