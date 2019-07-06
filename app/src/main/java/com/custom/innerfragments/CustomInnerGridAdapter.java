package com.custom.innerfragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomInnerGridAdapter extends ArrayAdapter<container> {


    public CustomInnerGridAdapter(Context context, ArrayList<container> items) {
            super(context, 0, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            container con = getItem(position);


            if (listItemView == null) {
                listItemView = LayoutInflater.from(this.getContext())
                .inflate(R.layout.grid_item, parent, false);

            }


            TextView NView = (TextView) listItemView.findViewById(R.id.containerName);
            LinearLayout linearLayout = listItemView.findViewById(R.id.griditem);



            if(con != null) {
                NView.setText(con.getLabel());
                linearLayout.setBackgroundResource(R.drawable.maintenance_bg);

            }

            return listItemView;
    }

    public Object getItemPosition(int position){
        return getItem(position);
    }

    public Object getLabelString(int position){
        container con = getItem(position);
        assert con != null;
        if(con.getLabel() == null){
            return " ";
        }
        return (con.getLabel());
    }

   /* public Object getAsset(int position){
        container con = getItem(position);
        assert con != null;
        return con.getAssetId();
    }

    public Object getStatus(int position){
        container con = getItem(position);
        assert con != null;
        return con.getStatusId();
    }*/



}
