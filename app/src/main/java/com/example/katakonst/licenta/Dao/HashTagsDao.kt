package com.example.katakonst.licenta.Dao

import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.HashTags
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.JsonParse.CommentsParser
import com.example.katakonst.licenta.JsonParse.HashTagsParser
import com.example.katakonst.licenta.JsonParse.TrackParser
import com.example.katakonst.licenta.jsonRequests.TrackRequest
import org.apache.commons.codec.net.URLCodec

/**
 * Created by katakonst on 4/21/16.
 */

class HashTagsDao{

    fun getHashTags(): List<HashTags>
    {
        val hashTagsJson= TrackRequest(Constants.ip + "/getAllHashs").tracks
        var hashTags= HashTagsParser().getHashTags(hashTagsJson)
        return  hashTags

    }

    fun getTracksByHashTags(hashTagId:String?):List<Tracks>
    {
        val hashTagsJson= TrackRequest(Constants.ip + "/getTracksOfHash"+"?hashId=" + URLCodec().encode(hashTagId)).tracks
        var tracks= TrackParser().getTracks(hashTagsJson)
        return  tracks

    }

    fun getHashTagsOfTrack(trackId:String?):List<HashTags>
    {
        val hashTagsJson= TrackRequest(Constants.ip + "/getHashOfTrack"+"?trackId=" + URLCodec().encode(trackId)).tracks
        var hashs=  HashTagsParser().getHashTags(hashTagsJson)
        return  hashs

    }

}