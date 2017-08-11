package com.bukinmm.mobiledevelopertesttask;

//TODO: удалить не используемые импорты

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

public class PropertyActivity extends SingleFragmentActivity{

    Button mBtEdit;
    Button mBtSave;

    private static final String EXTRA_PROPERTY_ID =
            "com.bukinmm.mobiledevelopertesttask.android.propertyintent.property_id";


    public static Intent newIntent(Context packageContext, UUID propertyId){
        Intent intent = new Intent(packageContext, PropertyActivity.class);
        intent.putExtra(EXTRA_PROPERTY_ID, propertyId);
        return intent;
    }


    @Override
    protected Fragment createFragment(){

        UUID propertyId = (UUID)getIntent().getSerializableExtra(EXTRA_PROPERTY_ID);

        return PropertyFragment.newInstanse(propertyId);
    }

    // HANDLERS. START
    public void btEdit(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Вызов функции редактирования", Toast.LENGTH_LONG);
        toast.show();

        mBtEdit = (Button) view.findViewById(R.id.btEdit);
        mBtEdit.setVisibility(view.INVISIBLE);

//        mBtSave = (Button) view.findViewById(R.id.btSave);
//        mBtSave.setVisibility(view.VISIBLE);
    }

    public void btSave(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Вызов функции сохранения", Toast.LENGTH_LONG);
        toast.show();

//        mBtEdit = (Button) view.findViewById(R.id.btEdit);
//        mBtEdit.setVisibility(view.VISIBLE);
//
//        mBtSave = (Button) view.findViewById(R.id.btSave);
//        mBtSave.setVisibility(view.INVISIBLE);
    }

    // HANDLERS. END
}
