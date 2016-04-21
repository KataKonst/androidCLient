package com.example.katakonst.licenta.JsonModels

import java.io.Serializable

/**
 * Created by katakonst on 4/17/16.
 */

class Users(val id: String,val nume:String,val photoLink:String): Serializable {
    companion object {
        val id = "id"
        val nume="nume"
        val photoLink="photoLink"
    }

    override fun toString(): String {
        return this.nume
    }

}