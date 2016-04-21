package com.example.katakonst.licenta.JsonModels

import java.io.Serializable

/**
 * Created by katakonst on 3/5/16.
 */
class Tracks(val name: String, val link: String, val id: String,val photoLink:String) : Serializable {
    companion object {
        val Name = "nume"
        val Link = "link"
        val Id = "id"
        val PhotoLink="photoLink"
    }

    override fun toString(): String {
        return this.name
    }


}
