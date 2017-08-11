package com.bukinmm.mobiledevelopertesttask;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;


public class PropertyListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){

        return new PropertyListFragment();
    }

}
