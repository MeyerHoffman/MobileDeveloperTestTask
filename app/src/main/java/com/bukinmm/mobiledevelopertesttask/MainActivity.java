package com.bukinmm.mobiledevelopertesttask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity{

    private EditText mLoginInput;
    private EditText mPasswordInput;
    private Button mBtEntrance;

    private SharedPreferences settingsUser;
    private final String M_USER_LOGIN_PREF = "userLogin";
    private final String M_USER_PASS_HASH = "userPassHash";

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        settingsUser = getSharedPreferences("userPref", MODE_PRIVATE);

        if(!DoIneedEnterPassword()){
            finish();
        }

        setContentView(R.layout.login_layout);


        mLoginInput = (EditText)findViewById(R.id.loginTextInput);
        mPasswordInput = (EditText) findViewById(R.id.passwordTextInput);
        mBtEntrance = (Button) findViewById(R.id.btEntrance);

        mBtEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificationUserData(mLoginInput.getText().toString(),
                        mPasswordInput.getText().toString());
            }
        });

        CreateDefaultData();
    }


    private void VerificationUserData(String login, String password){

        User currentUser;

        currentUser = UserStorage.get(this).
                getUsetByNameAndPass(login, password);


        if(currentUser != null){

            // Set user preferences:
            SharedPreferences.Editor prefEditor = settingsUser.edit();

            prefEditor.putString(M_USER_LOGIN_PREF, login);
            prefEditor.apply();

            prefEditor.putString(M_USER_PASS_HASH, Integer.toString(password.hashCode()));
            prefEditor.apply();

            Intent intent = new Intent(this, PropertyListActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    getString(R.string.txtIncorrectLogin), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private boolean DoIneedEnterPassword(){
        String userLogin = settingsUser.getString(M_USER_LOGIN_PREF, "").toString();
        String userPassHash = settingsUser.getString(M_USER_PASS_HASH, "").toString();

        User currentUser;

        currentUser = UserStorage.get(this).
                getUserByNameAndHash(userLogin, userPassHash);


        if(currentUser != null){
            Intent intent = new Intent(this, PropertyListActivity.class);
            startActivity(intent);
            return false;
        } else {
            return  true;
        }
    }

    private void CreateDefaultData(){
        CreateDefaultUser();
        CreateDafaultRecords();
    }

    private void CreateDefaultUser(){

        int userCount = UserStorage.get(getApplicationContext()).getUsers().size();

        if(userCount == 0){
            final String USER_NAME = "Юрий";
            final String USER_PASSWORD = "123456";

            User defUser = new User();
            defUser.setName(USER_NAME);
//            defUser.setPasswordForDefUser(USER_PASSWORD);
            defUser.setPassword(USER_PASSWORD);

            UserStorage.get(getApplicationContext()).addUser(defUser);
        }
    }

    private void CreateDafaultRecords(){

        int propCount = PropertyStorage.get(getApplicationContext()).getProperties().size();

        if(propCount == 0){
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

            defProperties.add(propertDef_3);
            // RECORD 3. END

            for (Property currProp : defProperties) {
                PropertyStorage.get(getApplicationContext()).addProperties(currProp);
            }
        }

    }
}



















































