package com.bukinmm.mobiledevelopertesttask.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bukinmm.mobiledevelopertesttask.Property;
import com.bukinmm.mobiledevelopertesttask.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBCreateProperty {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private String mAdress = "";
    private float mArea = 0.0f;
    private float mPrice = 0.0f;
    private int mNumberOfRoom = 0;
    private int mFloor = 0;
    private float mPricePerSquarelMeter = 0.0f;
    public  String msgResultOperation = "";

    public DBCreateProperty(Context context, String adress, float area, float price,
                            int numberOfRooms, int floor){

        mContext = context;

        if(adress != ""){
            mAdress = adress;
        } else {
            // TODO: вынести строку в ресурс
            msgResultOperation = mContext.getString(R.string.errorInputAdress);

        }

        if(area > 0){
            mArea = area;
        } else {
            // TODO: вынести строку в ресурс
            msgResultOperation = mContext.getString(R.string.errorInputArea);
        }

        if(price >  0){
            mPrice = price;
        } else {
            // TODO: вынести строку в ресурс
            msgResultOperation = mContext.getString(R.string.errorInputPrice);
        }

        if(numberOfRooms > 0){
            mNumberOfRoom = numberOfRooms;
        } else {
            // TODO: вынести строку в ресурс
            msgResultOperation = mContext.getString(R.string.errorInputNumberOFRooms);
        }

        if(floor != 0){
            mFloor = floor;
        } else {
            // TODO: вынести строку в ресурс
            msgResultOperation = mContext.getString(R.string.errorInputFloor);
        }

        if(msgResultOperation == "") {
            mDatabase = new DBHelper(mContext).getWritableDatabase();
        }

    }

    public void addProperty(Property property, String adress, float area, float price,
                             int numberOfRooms, int floor){
        ContentValues values = getContentValues(property);

        mDatabase.insert(DBSchema.PropertiesTable.NAME, null, values);
    }

    public void updateProperty(Property property){
        String uuidString = property.getId().toString();
        ContentValues values = getContentValues(property);

        mDatabase.update(DBSchema.PropertiesTable.NAME, values,
                DBSchema.PropertiesTable.Cols.UUID +
                " = ?", new String[] { uuidString });
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

    private float CalculationOfPricePerSquareMeter(float area, float price){
        float result = 0.0f;

        return  result = price / area;
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
