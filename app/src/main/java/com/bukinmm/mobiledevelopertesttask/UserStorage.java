package com.bukinmm.mobiledevelopertesttask;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bukinmm.mobiledevelopertesttask.database.DBCursorWrapper;
import com.bukinmm.mobiledevelopertesttask.database.DBHelper;
import com.bukinmm.mobiledevelopertesttask.database.DBSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserStorage {
    private static UserStorage sUserStorage;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static UserStorage get (Context context){
        if(sUserStorage == null){
            sUserStorage = new UserStorage(context);
        }
        return sUserStorage;
    }

    private UserStorage(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DBHelper(mContext).getWritableDatabase();
    }

    public void addUser(User user){
        ContentValues values = getContentValues(user);
        mDatabase.insert(DBSchema.UsersTable.NAME, null, values);
    }

    public void updateUser(User user){
        String uuidString = user.getId().toString();
        ContentValues values = getContentValues(user);

        mDatabase.update(DBSchema.UsersTable.NAME, values,
                DBSchema.UsersTable.Cols.UUID + " = ?", new String[] {  uuidString});
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
        DBCursorWrapper cursor = queryUsers(DBSchema.UsersTable.Cols.UUID + " = ?",
                new String[] { id.toString() });

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

    public User getUsetByNameAndPass(String name, String password){
        String mName = name;
        String mPassHash = Integer.toString(password.hashCode());

        DBCursorWrapper cursor = queryUsers(DBSchema.UsersTable.Cols.LOGIN +
                " = ? AND " + DBSchema.UsersTable.Cols.PASSWORD +
                " = ? ", new String[] { mName, mPassHash });

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

    public User getUserByNameAndHash(String name, String hash){
        String mName = name;
        String mPassHash = hash;

        DBCursorWrapper cursor = queryUsers(DBSchema.UsersTable.Cols.LOGIN +
                " = ? AND " + DBSchema.UsersTable.Cols.PASSWORD +
                " = ? ", new String[] { mName, mPassHash });

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


    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();

        values.put(DBSchema.UsersTable.Cols.UUID,     user.getId().toString());
        values.put(DBSchema.UsersTable.Cols.LOGIN,    user.getLogin());
        values.put(DBSchema.UsersTable.Cols.PASSWORD, user.getPassword());

        return values;
    }

    private DBCursorWrapper queryUsers(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                DBSchema.UsersTable.NAME,
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
