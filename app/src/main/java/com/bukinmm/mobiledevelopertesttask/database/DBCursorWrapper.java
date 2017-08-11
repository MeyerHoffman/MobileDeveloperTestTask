package com.bukinmm.mobiledevelopertesttask.database;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.bukinmm.mobiledevelopertesttask.Property;
import com.bukinmm.mobiledevelopertesttask.User;

import java.util.UUID;

public class DBCursorWrapper extends CursorWrapper{


    public DBCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public User getUser(){
        String uuidString = getString(getColumnIndex(DBSchema.UsersTable.Cols.UUID));
        String loginString = getString(getColumnIndex(DBSchema.UsersTable.Cols.LOGIN));
        String passwordString = getString(getColumnIndex(DBSchema.UsersTable.Cols.PASSWORD));

        User user = new User(UUID.fromString(uuidString));
        user.setName(loginString);
        user.setPassword(passwordString);

        return user;
    }

    public Property getProperty(){
        String uuidString           = getString(getColumnIndex(DBSchema.PropertiesTable.Cols.UUID));
        String addressString        = getString(getColumnIndex(DBSchema.PropertiesTable.Cols.ADRESS));
        float areaFloat            = getFloat(getColumnIndex(DBSchema.PropertiesTable.Cols.AREA));
        float priceFloat            = getFloat(getColumnIndex(DBSchema.PropertiesTable.Cols.PRICE));
        int numberOfRoomsInt        = getInt(getColumnIndex(DBSchema.PropertiesTable.Cols.NUMBER_OF_ROOMS));
        // TODO: удалить строку если не нужна
        //float pricePerMeterFloat    = getFloat(getColumnIndex(DBSchema.PropertiesTable.Cols.PRICE_PER_METER));
        int floorInt                = getInt(getColumnIndex(DBSchema.PropertiesTable.Cols.FLOOR));

        Property property = new Property(UUID.fromString(uuidString));
        property.setAddress(addressString);
        property.setArea(areaFloat);
        property.setPrice(priceFloat);
        property.setNumberOfRooms(numberOfRoomsInt);
        property.setFloor(floorInt);

        return property;

    }
}
