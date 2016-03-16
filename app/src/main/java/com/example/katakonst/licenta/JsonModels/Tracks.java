package com.example.katakonst.licenta.JsonModels;

import java.io.Serializable;

/**
 * Created by katakonst on 3/5/16.
 */
public class Tracks implements Serializable {

    private  String mName;
    private String mLink;
    private int mId;


    public Tracks(String Name, String Link,int id)
    {
        this.mName=Name;
        this.mLink=Link;
        this.mId=id;

    }

    public  String getName()
    {
        return  mName;
    }

    public  String getLink()
    {
        return  mLink;
    }

    public  int getId()
    {
        return  mId;
    }


}
