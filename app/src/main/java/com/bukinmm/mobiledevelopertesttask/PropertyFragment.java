package com.bukinmm.mobiledevelopertesttask;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class PropertyFragment extends Fragment {

    private Property mProperty;
    private TextView mAddress;
    private TextView mArea;
    private TextView mPrice;
    private TextView mNumberOfRooms;
    private TextView mPricePerSquarelMeter;
    private TextView mFloor;

    private Button mBtEdit;
    private Button mBtSave;


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
        mProperty = PropertyStorage.get(getActivity()).getProperty(propertyId);
    }

    @Override
    public void onPause(){
        super.onPause();

        PropertyStorage.get(getActivity()).updateProperty(mProperty);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                          Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_property, container, false);



        mBtEdit = (Button)view.findViewById(R.id.btEdit);
        mBtSave = (Button)view.findViewById(R.id.btSave);

        mBtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickbtEdit(view);
            }
        });

        mBtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickbtSave(view);
            }
        });

        mAddress = (TextView)view.findViewById(R.id.txtAddress);
        mArea = (TextView)view.findViewById(R.id.txtArea);
        mPrice = (TextView)view.findViewById(R.id.txtPrice);
        mNumberOfRooms = (TextView)view.findViewById(R.id.txtNumberOfRooms);
        mPricePerSquarelMeter= (TextView)view.findViewById(R.id.txtPricePerSquareMeter);
        mFloor = (TextView)view.findViewById(R.id.txtFloor);

        mAddress.setText(mProperty.getAddress());
        mArea.setText(Float.toString(mProperty.getArea()) + " " + this.getString(R.string.dimensionSquarelMeter));
        mPrice.setText(Float.toString(mProperty.getPrice()) + " " + this.getString(R.string.dimensionThousandRub));
        mNumberOfRooms.setText(Integer.toString(mProperty.getNumberOfRooms()));
        mPricePerSquarelMeter.setText(Float.toString(mProperty.getPricePerMeter()) + " " + this.getString(R.string.dimensionThousandRub));
        mFloor.setText(Integer.toString(mProperty.getFloor()));


        return view;
    }

    // HANDLERS. START
    // TODO: удалить вызов сообщения в методах
    public void onClickbtEdit(View view){
        Toast toast = Toast.makeText(getContext(),
                "Вызов функции редактирования", Toast.LENGTH_SHORT);
        toast.show();

        mBtEdit.setVisibility(view.INVISIBLE);
        mBtSave.setVisibility(view.VISIBLE);
    }

    public void onClickbtSave(View view){
        Toast toast = Toast.makeText(getContext(),
                "Вызов функции сохранения", Toast.LENGTH_SHORT);
        toast.show();

        mBtEdit.setVisibility(view.VISIBLE);
        mBtSave.setVisibility(view.INVISIBLE);
    }

    // HANDLERS. END



}
