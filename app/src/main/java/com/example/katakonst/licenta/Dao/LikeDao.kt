package com.example.katakonst.licenta.Dao

import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.JsonModels.LikeNr
import com.example.katakonst.licenta.JsonModels.Tracks
import com.example.katakonst.licenta.JsonModels.Users
import com.example.katakonst.licenta.JsonParse.LikeNrParser
import com.example.katakonst.licenta.JsonParse.TrackParser
import com.example.katakonst.licenta.JsonParse.UserPaser
import com.example.katakonst.licenta.jsonRequests.TrackRequest
import org.apache.commons.codec.net.URLCodec

/**
 * Created by katakonst on 4/17/16.
 */
class LikeDao{

    fun getLikesNr(trackId:String?):LikeNr
    {
            val likesJson=TrackRequest(Constants.ip + "/getTrackNrLikes?trackId=" + URLCodec().encode(trackId)).tracks
            var LikesNr= LikeNrParser().LikesNr(likesJson)
            return  LikesNr.first()

    }

    fun getUsersWhoLikedTrack(trackId:String?):List<Users>
    {
        val likesJson=TrackRequest(Constants.ip + "/getUserTrackLiked?trackId=" + URLCodec().encode(trackId)).tracks
        var LikesNr= UserPaser().getUsers(likesJson)
        return  LikesNr

    }

    fun getLikedTracksByUserk(userId:String?):List<Tracks>
    {
        val likedTracks=TrackRequest(Constants.ip + "/getUserLikedTracks?userId=" + URLCodec().encode(userId)).tracks
        var likedTracksList= TrackParser().getTracks(likedTracks)
        return  likedTracksList

    }



}