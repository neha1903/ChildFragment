package com.custom.innerfragments;

import android.os.Parcel;
import android.os.Parcelable;

public class container implements Parcelable {

    private String label = " ";
    private int id = 1;


    public container(String label, int id) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public int getId() {
        return id;
    }

    protected container(Parcel in) {
        label = in.readString();
        id = in.readInt();
    }

    public static final Creator<container> CREATOR = new Creator<container>() {
        @Override
        public container createFromParcel(Parcel in) {
            return new container(in);
        }

        @Override
        public container[] newArray(int size) {
            return new container[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label);
        dest.writeInt(id);
    }
}
