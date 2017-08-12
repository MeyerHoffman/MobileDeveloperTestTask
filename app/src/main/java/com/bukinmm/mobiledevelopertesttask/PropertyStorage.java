package com.bukinmm.mobiledevelopertesttask;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bukinmm.mobiledevelopertesttask.database.DBCursorWrapper;
import com.bukinmm.mobiledevelopertesttask.database.DBHelper;
import com.bukinmm.mobiledevelopertesttask.database.DBSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PropertyStorage {

    private static PropertyStorage sPropertyStorage;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PropertyStorage get (Context context){
        if(sPropertyStorage == null){
            sPropertyStorage = new PropertyStorage(context);
        }
        return sPropertyStorage;
    }

    private PropertyStorage(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DBHelper(mContext).getWritableDatabase();

    }

    public void addProperties(Property property){
        ContentValues values = getContentValues(property);
        mDatabase.insert(DBSchema.PropertiesTable.NAME, null, values);
    }

    public void updateProperty(Property property){
        String uuidString = property.getId().toString();
        ContentValues values = getContentValues(property);

            mDatabase.update(DBSchema.PropertiesTable.NAME,
                    values,
                    DBSchema.PropertiesTable.Cols.UUID + " = ?",
                    new String[] { uuidString });
    }

    public List<Property> getProperties(){
        List<Property> properties = new ArrayList<>();
        DBCursorWrapper cursor = queryProperies(null, null);

        try{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                properties.add(cursor.getProperty());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return properties;
    }

    public Property getProperty(UUID id){
        DBCursorWrapper cursor = queryProperies(
                DBSchema.PropertiesTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try{
            if(cursor.getCount() == 0){
                return  null;
            }

            cursor.moveToFirst();

            return cursor.getProperty();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Property property){
        ContentValues values = new ContentValues();

        values.put(DBSchema.PropertiesTable.Cols.UUID,              property.getId().toString());
        values.put(DBSchema.PropertiesTable.Cols.ADRESS,            property.getAddress());
        values.put(DBSchema.PropertiesTable.Cols.AREA,              property.getArea());
        values.put(DBSchema.PropertiesTable.Cols.PRICE,             property.getPrice());
        values.put(DBSchema.PropertiesTable.Cols.NUMBER_OF_ROOMS,   property.getNumberOfRooms());
        values.put(DBSchema.PropertiesTable.Cols.PRICE_PER_METER,   property.getPricePerMeter());
        values.put(DBSchema.PropertiesTable.Cols.FLOOR,             property.getFloor());

        return values;
    }

    private DBCursorWrapper queryProperies(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                DBSchema.PropertiesTable.NAME,
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
