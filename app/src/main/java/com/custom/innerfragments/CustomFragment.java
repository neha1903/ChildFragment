package com.custom.innerfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class CustomFragment extends Fragment {

    ArrayList<container> arrayList = new ArrayList<container>();
    ArrayList<container> innerArrayList = new ArrayList<container>();
    SectionsStatePagerAdapter adapter;
    private CustomViewPager customViewPager;
    ImageView leftNav;
    ImageView rightNav;
    LinearLayout viewLayout;
    int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle extras = getArguments();
        arrayList  = extras.getParcelableArrayList("arraylist");
        View v = inflater.inflate(R.layout.cutom_fragment, null);
        leftNav = (ImageView) v.findViewById(R.id.leftNav);
        rightNav = (ImageView) v.findViewById(R.id.rightNav);
        viewLayout = (LinearLayout) v.findViewById(R.id.viewPagerContainer);
        adapter = new SectionsStatePagerAdapter(getChildFragmentManager());
        innerArrayList = new ArrayList<container>();
        customViewPager = v.findViewById(R.id.inCustomViewPager);
        int a = 1, i = 0;
        for(;i<arrayList.size(); i++){
            if(a == 10){
                InnerCustomFragment fragment = new InnerCustomFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("innerArrayList", innerArrayList);
                fragment.setArguments(bundle);
                adapter.addFragment(fragment, "F" + i);
                innerArrayList = new ArrayList<container>();
                a = 1;
            }
            innerArrayList.add(arrayList.get(i));
            a++;
        }

        if(arrayList.size() > 0 ){
            InnerCustomFragment fragment = new InnerCustomFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("innerArrayList", innerArrayList);
            fragment.setArguments(bundle);
            adapter.addFragment(fragment, "F" + i);
        }

        count = adapter.getCount();
        Log.i("COUNT", String.valueOf(count));

        if(count == 1){
            leftNav.setVisibility(View.GONE);
            rightNav.setVisibility(View.GONE);
        }


        customViewPager.setAdapter(adapter);
        customViewPager.setPagingEnabled(true);

        setRightAndLeftNav();

        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Constants.selectTab = customViewPager.getCurrentItem();
                if(i == 0){
                    leftNav.setVisibility(View.GONE);
                    rightNav.setVisibility(View.VISIBLE);
                }else if(i > 0){
                    leftNav.setVisibility(View.VISIBLE);
                    rightNav.setVisibility(View.VISIBLE);
                    if(i == count-1){
                        rightNav.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return v;
    }

    public void setRightAndLeftNav(){
        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = customViewPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    customViewPager.setCurrentItem(tab);
                    rightNav.setVisibility(View.VISIBLE);
                } else if (tab == 0) {
                    customViewPager.setCurrentItem(tab);
                }if(tab == 0){
                    leftNav.setVisibility(View.GONE);
                }
            }
        });

        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = customViewPager.getCurrentItem();
                tab++;
                customViewPager.setCurrentItem(tab);
                if(tab > 0 ){
                    leftNav.setVisibility(View.VISIBLE);
                }if(tab == count-1){
                    rightNav.setVisibility(View.GONE);
                }
            }
        });





    }





}
