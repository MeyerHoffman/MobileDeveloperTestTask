package com.bukinmm.mobiledevelopertesttask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bukinmm.mobiledevelopertesttask.database.DBCreateProperty;
import com.bukinmm.mobiledevelopertesttask.database.DBCreateUser;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.login_layout);

        CreateDefaultData();
    }


    public void btLogin(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                R.string.txtIncorrectLogin, Toast.LENGTH_SHORT);
        toast.show();

        // Start next activity
        Intent intent = new Intent(this, PropertyListActivity.class);
        startActivity(intent);
    }

    private void CreateDefaultData(){
        CreateDefaultUser();
        CreateDafaultRecords();
    }

    private void CreateDefaultUser(){
        final String USER_NAME = "Юрий";
        final String USER_PASSWORD = "123456";
        DBCreateUser createUser = new DBCreateUser(getApplicationContext(), USER_NAME, USER_PASSWORD);

        Toast toast = Toast.makeText(getApplicationContext(),
                createUser.resultCreateUserMsg, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void CreateDafaultRecords(){

        int propCount = PropertyDepot.get(getApplicationContext()).getProperties().size();

        if(PropertyDepot.get(getApplicationContext()).getProperties().size() == 0){
            final int NUMBER_DEFAULT_RECORDS = 3;
            List<Property> defProperties = new ArrayList<>();

            // RECORD 1. START
            final String ADRESS_1 = "ЖК «Квартет», ГП-236, ул. Широтная, 213";
            final float AREA_1 = 48.0f;
            final float PRICE_1 =  2416181.0f;
            final int NUMBER_OF_ROOMS_1 = 3;
            final int FLOOR_1 = 3;

            float price_per_squarel_meter_1 = PRICE_1 / AREA_1;

            Property propertDef_1 = new Property();
            propertDef_1.setAddress(ADRESS_1);
            propertDef_1.setArea(AREA_1);
            propertDef_1.setPrice(PRICE_1);
            propertDef_1.setNumberOfRooms(NUMBER_OF_ROOMS_1);
            propertDef_1.setFloor(FLOOR_1);

            defProperties.add(propertDef_1);
            // RECORD 1. END

            // RECORD 2. START
            final String ADRESS_2 = "ЖК Серебряные ключи-2, ул. Малая Боровская (2 очередь)";
            final float AREA_2 = 52.0f;
            final float PRICE_2 = 1750000.0f;
            final int NUMBER_OF_ROOMS_2 = 2;
            final int FLOOR_2 = 6;

            float price_per_squarel_meter_2 = PRICE_2 / AREA_2;

            Property propertDef_2 = new Property();
            propertDef_2.setAddress(ADRESS_2);
            propertDef_2.setArea(AREA_2);
            propertDef_2.setPrice(PRICE_2);
            propertDef_2.setNumberOfRooms(NUMBER_OF_ROOMS_2);
            propertDef_2.setFloor(FLOOR_2);

            defProperties.add(propertDef_2);
            // RECORD 2. END

            // RECORD 3. START
            final String ADRESS_3 = "Дом ГП-1, ул.Восстания, 19, корп.2";
            final float AREA_3 = 64.0f;
            final float PRICE_3 = 2084000.0f;
            final int NUMBER_OF_ROOMS_3 = 4;
            final int FLOOR_3 = 5;

            float price_per_squarel_meter_3 = PRICE_3 / AREA_3;

            Property propertDef_3 = new Property();
            propertDef_3.setAddress(ADRESS_3);
            propertDef_3.setArea(AREA_3);
            propertDef_3.setPrice(PRICE_3);
            propertDef_3.setNumberOfRooms(NUMBER_OF_ROOMS_3);
            propertDef_3.setFloor(FLOOR_3);

            defProperties.add(propertDef_1);
            // RECORD 3. END

            for (Property currProp : defProperties) {
                DBCreateProperty createProperty = new DBCreateProperty(getApplicationContext(),
                        currProp.getAddress(), currProp.getArea(), currProp.getPrice(),
                        currProp.getNumberOfRooms(), currProp.getFloor());
            }

            for (Property currProp : defProperties) {
                PropertyDepot.get(getApplicationContext()).addProperties(currProp);
            }
        }

    }
}



















































