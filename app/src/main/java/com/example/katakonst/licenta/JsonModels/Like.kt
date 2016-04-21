package com.example.katakonst.licenta.JsonModels

import java.io.Serializable

/**
 * Created by katakonst on 4/17/16.
 */


class Like(val trackId: String, val authorid: String, val id: String) : Serializable {
    companion object {
        val TrackId = "TrackId"
        val Authorid = "Authorid"
        val Id = "id"
    }

}
