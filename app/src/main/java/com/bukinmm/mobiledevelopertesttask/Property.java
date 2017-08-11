package com.bukinmm.mobiledevelopertesttask;


import java.util.UUID;

public class Property {

    private UUID mId;
    private String mAddress = "";
    private float mArea = 0.0f;
    private float mPrise = 0.0f;
    private int mNumberOfRooms = 0;
    private float mPricePerMeter = 0.0f;
    private int mFloor = 0;

    public Property(){
        this(UUID.randomUUID());
//        mId = UUID.randomUUID();
    }

    public Property(UUID id){
        mId = id;
    }


    // SETTERS. START
    public void setId(UUID id){


    }
    public void setAddress(String address){
        mAddress = address;
    }
    public void setArea(float area){
        mArea = area;
    }
    public void setPrice(float price){
        mPrise = price;
    }
    public void setNumberOfRooms(int numberOfRooms){
        mNumberOfRooms = numberOfRooms;
    }
    private void setPricePerMeter(){
        if(mArea > 0){
            mPricePerMeter = Math.round(mPrise / mArea);
        } else {
            mPricePerMeter = 0.0f;
        }
    }
    public void setFloor(int floor){
        mFloor = floor;
    }
    // SETTERS. END

    // GETTERS. START
    public UUID getId(){
        return mId;
    }
    public String getAddress(){
        return mAddress;
    }
    public float getArea(){
        return mArea;
    }
    public float getPrice(){
        return mPrise;
    }
    public int getNumberOfRooms(){
        return mNumberOfRooms;
    }
    public float getPricePerMeter(){
        if(mArea > 0){
            return (mPricePerMeter = Math.round(mPrise / mArea));
        } else {
            return 0.0f;
        }
    }
    public int getFloor(){
        return mFloor;
    }
    // GETTERS. END
}
