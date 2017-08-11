package com.bukinmm.mobiledevelopertesttask;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

public class PropertyListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        return new PropertyListFragment();
    }

    // HANDLERS. START
    public void btGetInfo(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Вызов справки", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void btLogout(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Выход из учетной записи", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void btAddNew(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Вызов функции создания новой учетной записи", Toast.LENGTH_LONG);
        toast.show();
    }


    // HANDLERS. END

}
