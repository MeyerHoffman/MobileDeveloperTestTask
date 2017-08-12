package com.bukinmm.mobiledevelopertesttask;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EditObjectActivity extends Activity{

    private UUID mPropertyId;
    private Property mProperty;

    private EditText mAreaEditText;
    private EditText mPriceEditText;
    private EditText mNumberOfRoomEditText;
    private EditText mFloorEditText;

    private Button mBtSaveNewProperty;
    private Boolean mAllValuesCorrect = true;
    private List<String> mIncorrectValueMsg = new ArrayList<>();

    private static final String ARG_PROPERTY_ID = "property_id";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        setContentView(R.layout.edit_object_layout);

        mPropertyId = UUID.fromString(getIntent().getStringExtra(ARG_PROPERTY_ID));

        mProperty = PropertyStorage.get(this).getProperty(mPropertyId);

        mAreaEditText = (EditText) findViewById(R.id.txtAreaInput);
        mPriceEditText = (EditText) findViewById(R.id.txtPriceInput);
        mNumberOfRoomEditText = (EditText) findViewById(R.id.txtNumberOfRoomsInput);
        mFloorEditText = (EditText) findViewById(R.id.txtFlootInput);

        mAreaEditText.setText(Float.toString(mProperty.getArea()));
        mPriceEditText.setText(Float.toString(mProperty.getPrice()));
        mNumberOfRoomEditText.setText(Integer.toString(mProperty.getNumberOfRooms()));
        mFloorEditText.setText(Integer.toString(mProperty.getFloor()));

        mBtSaveNewProperty = (Button) findViewById(R.id.btSave);

        mBtSaveNewProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckFillingAllValues();

                if(mAllValuesCorrect == true){
                    UpdateProperty();
                    //TODO: обновить UI!!

                    finish();
                } else {

                    ShowDialogErrorInputValues(v);

                    mAllValuesCorrect = true;
                }
            }
        });
    }

    private void CheckFillingAllValues(){

        if((mAreaEditText.length() == 0) || (mAreaEditText.getText().equals(R.string.txtZeroFloat)) ||
                (mAreaEditText.getText().toString() == "0.0")       ||
                (mAreaEditText.getText().toString() == "0"          ||
                        (Double.parseDouble(mAreaEditText.getText().toString()) <= 0.0)))
        {
            mAllValuesCorrect = false;
            mIncorrectValueMsg.add(getString(R.string.errorInputArea));
        } else {
            mProperty.setArea(Float.parseFloat(mAreaEditText.getText().toString()));
        }

        if((mPriceEditText.length() == 0) || (mPriceEditText.getText().equals(R.string.txtZeroFloat)) ||
                (mPriceEditText.getText().toString() == "0.0")    ||
                (mPriceEditText.getText().toString() == "0"       ||
                        (Double.parseDouble(mPriceEditText.getText().toString()) <= 0.0)))
        {
            mAllValuesCorrect = false;
            mIncorrectValueMsg.add(getString(R.string.errorInputPrice));
        } else {
            mProperty.setPrice(Float.parseFloat(mPriceEditText.getText().toString()));
        }

        if((mNumberOfRoomEditText.length() == 0) || (mNumberOfRoomEditText.getText().equals(R.string.txtZeroInt)) ||
                (Integer.parseInt(mNumberOfRoomEditText.getText().toString()) <= 0))
        {
            mAllValuesCorrect = false;
            mIncorrectValueMsg.add(getString(R.string.errorInputNumberOFRooms));
        } else {
            mProperty.setNumberOfRooms(Integer.parseInt(mNumberOfRoomEditText.getText().toString()));
        }

        if((mFloorEditText.length() == 0) || (mFloorEditText.getText().equals(R.string.txtZeroInt)) ||
                (Integer.parseInt(mFloorEditText.getText().toString()) <= 0))
        {
            mAllValuesCorrect = false;
            mIncorrectValueMsg.add(getString(R.string.errorInputFloor));
        } else {
            mProperty.setFloor(Integer.parseInt(mFloorEditText.getText().toString()));
        }
    }

    private void ShowDialogErrorInputValues(View view){
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(EditObjectActivity.this);

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

    private void UpdateProperty(){
        PropertyStorage.get(this).updateProperty(mProperty);
    }

}
