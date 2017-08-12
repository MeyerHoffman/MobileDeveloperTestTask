package com.bukinmm.mobiledevelopertesttask;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddNewActivity extends Activity{

    private EditText mAddressEditText;
    private EditText mAreaEditText;
    private EditText mPriceEditText;
    private EditText mNumberOfRoomEditText;
    private EditText mFloorEditText;

    private Button mBtSaveNewProperty;
    private Boolean mAllValuesCorrect = true;
    private List<String> mIncorrectValueMsg = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_new_object_layout);

        mAddressEditText = (EditText) findViewById(R.id.txtAddressInput);
        mAreaEditText = (EditText) findViewById(R.id.txtAreaInput);
        mPriceEditText = (EditText) findViewById(R.id.txtPriceInput);
        mNumberOfRoomEditText = (EditText) findViewById(R.id.txtNumberOfRoomsInput);
        mFloorEditText = (EditText) findViewById(R.id.txtFlootInput);

        mBtSaveNewProperty = (Button) findViewById(R.id.btSave);

        mBtSaveNewProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckFillingAllValues();

                if(mAllValuesCorrect == true){
                    CreateNewProperty();
                    finish();
                } else {

                    ShowDialogErrorInputValues(v);

                    mAllValuesCorrect = true;
                }
            }
        });

    }

    private void CheckFillingAllValues(){
            if(             (mAddressEditText.length() == 0) ||
                    (mAddressEditText.getText().equals(R.string.emptyTxt)) ||
                    ((mAddressEditText.getText().toString()) == ""))
            {
                mAllValuesCorrect = false;
                mIncorrectValueMsg.add(getString(R.string.errorInputAdress));
            }


        if((mAreaEditText.length() == 0) || (mAreaEditText.getText().equals(R.string.txtZeroFloat)) ||
                                                (mAreaEditText.getText().toString() == "0.0")       ||
                                                (mAreaEditText.getText().toString() == "0"          ||
                                                        (Double.parseDouble(mAreaEditText.getText().toString()) <= 0.0)))
        {
            mAllValuesCorrect = false;
            mIncorrectValueMsg.add(getString(R.string.errorInputArea));
        }

        if((mPriceEditText.length() == 0) || (mPriceEditText.getText().equals(R.string.txtZeroFloat)) ||
                                                    (mPriceEditText.getText().toString() == "0.0")    ||
                                                    (mPriceEditText.getText().toString() == "0"       ||
                                                            (Double.parseDouble(mPriceEditText.getText().toString()) <= 0.0)))
        {
            mAllValuesCorrect = false;
            mIncorrectValueMsg.add(getString(R.string.errorInputPrice));
        }

        if((mNumberOfRoomEditText.length() == 0) || (mNumberOfRoomEditText.getText().equals(R.string.txtZeroInt)) ||
                (Integer.parseInt(mNumberOfRoomEditText.getText().toString()) <= 0))
        {
            mAllValuesCorrect = false;
            mIncorrectValueMsg.add(getString(R.string.errorInputNumberOFRooms));
        }

        if((mFloorEditText.length() == 0) || (mFloorEditText.getText().equals(R.string.txtZeroInt)) ||
                (Integer.parseInt(mFloorEditText.getText().toString()) <= 0))
        {
            mAllValuesCorrect = false;
            mIncorrectValueMsg.add(getString(R.string.errorInputFloor));
        }
    }

    private void CreateNewProperty(){
        Property newProperty = new Property();
        newProperty.setAddress(mAddressEditText.getText().toString());
        newProperty.setArea(Float.parseFloat(mAreaEditText.getText().toString()));
        newProperty.setPrice(Float.parseFloat(mPriceEditText.getText().toString()));
        newProperty.setNumberOfRooms(Integer.parseInt(mNumberOfRoomEditText.getText().toString()));
        newProperty.setFloor(Integer.parseInt(mFloorEditText.getText().toString()));

        PropertyStorage.get(getApplicationContext()).addProperties(newProperty);
    }

    private void ShowDialogErrorInputValues(View view){
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(AddNewActivity.this);

        String msg = "";

        for(String currentMsg : mIncorrectValueMsg){
            msg = msg.concat(currentMsg + "\n"  + "\n");
        }

        adBuilder.setTitle(getString(R.string.warningMsgCreateNewProperty))
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton(getString(R.string.txtOk), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alert = adBuilder.create();
        alert.show();
    }
}
