package com.example.katakonst.licenta.JsonModels

import java.io.Serializable

/**
 * Created by katakonst on 4/17/16.
 */


class Comments(val id: String, val date: String, val text: String, val photoLink:String, authorId:String,authorName:String ) : Serializable {
    companion object {
        val Id="id"
        val Date= "date"
        val Text="Text"
        val PhotoLink ="photoLink"
        val AuthorId="author_id"
        val AuthorName="authorName"

    }

    override fun toString(): String {
        return this.text
    }

}
