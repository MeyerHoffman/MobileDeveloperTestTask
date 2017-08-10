package com.bukinmm.mobiledevelopertesttask;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PropertyDepot {

    private static PropertyDepot sPropertyDepot;

    private List<Property> mProperties;

    public static PropertyDepot get (Context context){
        if(sPropertyDepot == null){
            sPropertyDepot = new PropertyDepot(context);
        }
        return sPropertyDepot;
    }

    private PropertyDepot(Context context){
        mProperties = new ArrayList<>();

        for(int i = 0; i < 100; i++){
            Property property = new Property();
            property.setAddress("Новый Адресс " + i);
            property.setArea(i);
            property.setPrice(i + 100);
            mProperties.add(property);
        }
    }

    public List<Property> getProperties(){
        return mProperties;
    }

    public Property getProperty(UUID id){
        for(Property currentProperty : mProperties){
            if(currentProperty.getId().equals(id)){
                return currentProperty;
            }
        }
        return null;
    }
}
