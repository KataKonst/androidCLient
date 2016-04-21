package com.example.katakonst.licenta.Dao

import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.JsonModels.Comments
import com.example.katakonst.licenta.JsonModels.LikeNr
import com.example.katakonst.licenta.JsonParse.CommentsParser
import com.example.katakonst.licenta.JsonParse.LikeNrParser
import com.example.katakonst.licenta.jsonRequests.TrackRequest
import org.apache.commons.codec.net.URLCodec

/**
 * Created by katakonst on 4/18/16.
 */


class CommentsDao{

    fun getComments(trackId:String?): List<Comments>
    {
        val commentsJson= TrackRequest(Constants.ip + "/getComments?trackId=" + URLCodec().encode(trackId)).tracks
        var LikesNr= CommentsParser().getComments(commentsJson)
        return  LikesNr

    }



}