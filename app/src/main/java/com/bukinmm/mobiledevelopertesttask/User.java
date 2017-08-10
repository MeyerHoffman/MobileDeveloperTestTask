package com.bukinmm.mobiledevelopertesttask;


import android.content.ContentValues;

import com.bukinmm.mobiledevelopertesttask.database.DBCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID mId;

    public User(){
        this(UUID.randomUUID());
    }

    public User(UUID id){
        mId = id;
    }



    public User GetUser(UUID id){

        return null;
    }

    public String getId(){
        String id = "";

        return id;
    }

    public String getLogin(){
        String login = "";

        return login;
    }

    public String getPassword(){
        String password = "";

        return password;
    }

    public void setName(String userName){


    }

    public void setPassword(String userPassword){

    }


}


