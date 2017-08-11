package com.bukinmm.mobiledevelopertesttask;


import android.content.ContentValues;

import com.bukinmm.mobiledevelopertesttask.database.DBCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID mId;
    private String mLogin;
    private String mPassword;

    public User(){
        this(UUID.randomUUID());
    }

    public User(UUID id){
        mId = id;
    }

    // SETTERS. START
    public void setName(String userName){
        mLogin = userName;
    }

    public void setPassword(String userPassword){
        mPassword = userPassword;
    }

    // SETTERS. END

    // GETTERS. START

    public UUID getId(){
        return mId;
    }

    public String getLogin(){
        return mLogin;
    }

    public String getPassword(){
        return mPassword;
    }

    // GETTERS. END

}


