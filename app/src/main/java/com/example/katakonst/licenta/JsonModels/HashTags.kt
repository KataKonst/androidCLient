package com.example.katakonst.licenta.JsonModels

import java.io.Serializable

/**
 * Created by katakonst on 4/21/16.
 */
class HashTags(val id: String, val description: String) : Serializable {
    companion object {
        val Id="id"
        val Description= "description"
    }

    override fun toString(): String {
        return this.description
    }

}
