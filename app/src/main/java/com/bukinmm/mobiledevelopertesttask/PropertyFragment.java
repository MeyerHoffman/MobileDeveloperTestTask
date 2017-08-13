package com.bukinmm.mobiledevelopertesttask;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

public class PropertyFragment extends Fragment {

    private Property mProperty;
    private TextView mAddress;
    private TextView mArea;
    private TextView mPrice;
    private TextView mNumberOfRooms;
    private TextView mPricePerSquarelMeter;
    private TextView mFloor;
    private UUID mPropertyId;

    private Button mBtEdit;
    private Button mBtBack;

    private static final String ARG_PROPERTY_ID = "property_id";

    public static PropertyFragment newInstanse(UUID propertyId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROPERTY_ID, propertyId);

        PropertyFragment fragment = new PropertyFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID propertyId = (UUID)getArguments().getSerializable(ARG_PROPERTY_ID);
        mPropertyId = propertyId;
        mProperty = PropertyStorage.get(getActivity()).getProperty(propertyId);
    }

    @Override
    public void onPause(){
        super.onPause();
        mProperty = PropertyStorage.get(getActivity()).getProperty(mPropertyId);
        PropertyStorage.get(getActivity()).updateProperty(mProperty);
    }

    @Override
    public void onResume(){
        super.onResume();
        mProperty = PropertyStorage.get(getActivity()).getProperty(mPropertyId);
        PropertyStorage.get(getActivity()).updateProperty(mProperty);
        SetText();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                          Bundle savedInstanceState){

        mProperty = PropertyStorage.get(getActivity()).getProperty(mPropertyId);
        PropertyStorage.get(getActivity()).updateProperty(mProperty);

        View view = inflater.inflate(R.layout.fragment_property, container, false);

        mBtEdit = (Button)view.findViewById(R.id.btEdit);
        mBtBack = (Button) view.findViewById(R.id.btBack);

        mBtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickbtEdit(view);
            }
        });

        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });



        mAddress = (TextView)view.findViewById(R.id.txtAddress);
        mArea = (TextView)view.findViewById(R.id.txtArea);
        mPrice = (TextView)view.findViewById(R.id.txtPrice);
        mNumberOfRooms = (TextView)view.findViewById(R.id.txtNumberOfRooms);
        mPricePerSquarelMeter= (TextView)view.findViewById(R.id.txtPricePerSquareMeter);
        mFloor = (TextView)view.findViewById(R.id.txtFloor);

        SetText();

        return view;
    }


    private void SetText(){
        mAddress.setText(mProperty.getAddress());
        mArea.setText(Float.toString(mProperty.getArea()) + " " + this.getString(R.string.dimensionSquarelMeter));
        mPrice.setText(Float.toString(mProperty.getPrice()) + " " + this.getString(R.string.dimensionThousandRub));
        mNumberOfRooms.setText(Integer.toString(mProperty.getNumberOfRooms()));
        mPricePerSquarelMeter.setText(Float.toString(mProperty.getPricePerMeter()) + " " + this.getString(R.string.dimensionThousandRub));
        mFloor.setText(Integer.toString(mProperty.getFloor()));
    }

    // HANDLERS. START

    public void onClickbtEdit(View view){

        Intent intent = new Intent(getContext(), EditObjectActivity.class);
        intent.putExtra(ARG_PROPERTY_ID, mProperty.getId().toString());
        startActivity(intent);
    }


    // HANDLERS. END



}
