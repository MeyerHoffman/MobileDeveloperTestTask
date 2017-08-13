package com.bukinmm.mobiledevelopertesttask;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PropertyListFragment extends Fragment{

    private RecyclerView mPropertRecyclerView;
    private PropertyAdapter mAdapter;
    SharedPreferences settingsUser;

    private Button mBtLogout;
    private Button mBtInfo;
    private Button mBtAddNew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.main_layout, container, false);

        mBtLogout = (Button)view.findViewById(R.id.btLogout);
        mBtAddNew = (Button)view.findViewById(R.id.btAddObject);
        mBtInfo = (Button)view.findViewById(R.id.btInfo);

        mBtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btLogout(view);
            }
        });

        mBtAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btAddNew(view);
            }
        });

        mBtInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btGetInfo(view);
            }
        });

        mPropertRecyclerView = (RecyclerView)view.findViewById(R.id.property_recycler_view);
        mPropertRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    private void updateInformation(){
//        PropertyStorage propertyStorage = PropertyStorage.get(getActivity());
//
//
//        AppCompatActivity activity = (AppCompatActivity) getActivity();

        // TODO: метод не дописан
    }

    private void  updateUI(){
        PropertyStorage propertyStorage = PropertyStorage.get(getActivity());
        List<Property> properties = propertyStorage.getProperties();


        if(mAdapter == null){
            mAdapter = new PropertyAdapter(properties);
            mPropertRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setProperties(properties);
            mAdapter.notifyDataSetChanged();
        }

        updateInformation();
    }

    private class PropertyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mAddress;
        private TextView mArea;
        private TextView mPrice;

        private Property mProperty;

        public PropertyHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);

            mAddress = (TextView)itemView.findViewById(R.id.txtAddress);
            mArea = (TextView)itemView.findViewById(R.id.txtArea);
            mPrice = (TextView)itemView.findViewById(R.id.txtPrice);
        }

        public void bindProperty(Property property){

            mProperty = property;
            mAddress.setText(mProperty.getAddress());
            mArea.setText(Float.toString(mProperty.getArea()) + " " + getString(R.string.dimensionSquarelMeter));
            mPrice.setText(Float.toString(mProperty.getPrice()) + " " + getString(R.string.dimensionThousandRub));

        }

        @Override
        public void onClick(View view){
            Intent intent = PropertyActivity.newIntent(getActivity(), mProperty.getId());

            startActivity(intent);
        }
    }

    private class PropertyAdapter extends RecyclerView.Adapter<PropertyHolder>{
        private List<Property> mProperties;

        public PropertyAdapter(List<Property> properties){
            mProperties = properties;
        }

        @Override
        public PropertyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_property, parent, false);

            return new PropertyHolder(view);
        }

        @Override
        public void onBindViewHolder(PropertyHolder holder, int position){
            Property property = mProperties.get(position);
            holder.bindProperty(property);
        }

        @Override
        public int getItemCount(){
            return  mProperties.size();
        }

        public void setProperties(List<Property> properties){
            mProperties = properties;
        }
    }

    // HANDLERS. START
    // TODO: удалить вызов сообщения в методах
    public void btGetInfo(View view){
        Toast toast = Toast.makeText(getContext(),
                "Вызов справки", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void btLogout(View view){
        Toast toast = Toast.makeText(getContext(),
                "Выход из учетной записи", Toast.LENGTH_SHORT);
        toast.show();


        // Clear user preferences:

        settingsUser = getActivity().getSharedPreferences("userPref",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settingsUser.edit();
        prefEditor.clear();
        prefEditor.apply();


        // Start next activity
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void btAddNew(View view){
        Toast toast = Toast.makeText(getContext(),
                "Вызов функции создания новой учетной записи", Toast.LENGTH_LONG);
        toast.show();

        Intent intent = new Intent(getActivity(), AddNewActivity.class);
        startActivity(intent);
    }

    // HANDLERS. END

}
