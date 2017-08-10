package com.bukinmm.mobiledevelopertesttask;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bukinmm.mobiledevelopertesttask.Property;
import com.bukinmm.mobiledevelopertesttask.R;

public class PropertyFragment extends Fragment {

    private Property mProperty;
    private TextView mAddress;
    private TextView mArea;
    private TextView mPrice;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mProperty = new Property();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                          Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_property, container, false);

        mAddress = (TextView)view.findViewById(R.id.txtAddress);

        return view;
    }

}
