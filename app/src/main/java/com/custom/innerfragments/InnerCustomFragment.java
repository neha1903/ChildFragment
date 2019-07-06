package com.custom.innerfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class InnerCustomFragment extends Fragment {

    GridView grid;
    ArrayList<container> innerArrayList = new ArrayList<container>();
    CustomInnerGridAdapter customInnerGridAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle extras = getArguments();
        innerArrayList  = extras.getParcelableArrayList("innerArrayList");
        View v = inflater.inflate(R.layout.custom_fragment_layout, null);


        grid = (GridView) v.findViewById(R.id.gridview);
        customInnerGridAdapter = new CustomInnerGridAdapter(getContext(), innerArrayList);
        grid.setAdapter(customInnerGridAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelectView(view, position);

            }
        });

        return v;
    }

    public void setSelectView(View view, int position){
        view.setBackgroundResource(R.drawable.black_border_grey_bg);
        Constants.recentPosition = position;
        if(Constants.previousSelectedPosition == Constants.recentPosition ){
            if(Constants.selectTab !=Constants. prevSelectTab){
                if(Constants.previousSelectedPosition != -1){
                    Constants.previousSelectedView.setBackgroundResource(R.drawable.maintenance_bg);
                }
                Constants.previousSelectedView = view;
                Constants.previousSelectedPosition = position;
                Constants.prevSelectTab =  Constants.selectTab;
                Constants.prevSelectPage = Constants.selectPage;
            }else{
                if(Constants.selectPage != Constants.prevSelectPage){
                    if(Constants.previousSelectedPosition != -1){
                        Constants.previousSelectedView.setBackgroundResource(R.drawable.maintenance_bg);
                    }
                    Constants.previousSelectedView = view;
                    Constants.previousSelectedPosition = position;
                    Constants.prevSelectTab =  Constants.selectTab;
                    Constants.prevSelectPage = Constants.selectPage;
                } //Outer fragments  also be considered
            }
        }else{
            if(Constants.previousSelectedPosition != -1){
                Constants.previousSelectedView.setBackgroundResource(R.drawable.maintenance_bg);
            }
            Constants.previousSelectedView = view;
            Constants.previousSelectedPosition = position;
            Constants.prevSelectTab =  Constants.selectTab;
            Constants.prevSelectPage = Constants.selectPage;
        }
    }

}

