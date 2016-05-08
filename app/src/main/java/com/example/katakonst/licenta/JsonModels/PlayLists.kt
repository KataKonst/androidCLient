package com.example.katakonst.licenta.JsonModels

import java.io.Serializable

/**
 * Created by katakonst on 4/24/16.
 */

class PlayLists(val id: String, val authorid: String, val nume: String, val date:String) : Serializable {
    companion object {
        val Id="id"
        val Authorid= "authorid"
        val Nume="nume"
        val Date="date"

    }

    override fun toString(): String {
        return this.nume
    }

}
