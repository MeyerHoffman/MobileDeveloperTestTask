package com.bukinmm.mobiledevelopertesttask.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bukinmm.mobiledevelopertesttask.R;
import com.bukinmm.mobiledevelopertesttask.User;
import com.bukinmm.mobiledevelopertesttask.database.DBSchema.UsersTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class DBCreateUser {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private String mUserName = "";
    private String mUserPassword = "";

    public String resultCreateUserMsg = "";

    public DBCreateUser(Context context, String userName, String userPassword){

        mUserName = userName;
        mUserPassword = userPassword;

        if(mUserName != "" && mUserPassword != ""){
            mContext = context.getApplicationContext();
            mDatabase = new DBHelper(mContext).getWritableDatabase();

            resultCreateUserMsg = mContext.getString(R.string.successCreateNewUser);
        } else {
            resultCreateUserMsg = mContext.getString(R.string.failCreateNewUser);
        }

    }

    public void addUser(User user, String userName, String userPassword){
        ContentValues values = getContentValues(user);

        mDatabase.insert(UsersTable.NAME, null, values);
    }

    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();

        values.put(UsersTable.Cols.UUID, user.getId());
        values.put(UsersTable.Cols.LOGIN, user.getLogin());
        values.put(UsersTable.Cols.PASSWORD, user.getPassword());


        return values;
    }

    public void updateUser(User user){
        String uuidString = user.getId();
        ContentValues values = getContentValues(user);

        mDatabase.update(UsersTable.NAME, values, UsersTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        DBCursorWrapper cursor = queryUsers(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return users;
    }

    public User getUser(UUID id){
        DBCursorWrapper cursor = queryUsers(
                UsersTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try{
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();

            return cursor.getUser();
        } finally {
            cursor.close();
        }
    }

    private DBCursorWrapper queryUsers(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                UsersTable.NAME,
                null, // select all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );

        return new DBCursorWrapper(cursor);
    }

}















































