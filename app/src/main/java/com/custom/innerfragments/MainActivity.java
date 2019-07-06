package com.custom.innerfragments;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    SectionsStatePagerAdapter adapterIsoTank = new SectionsStatePagerAdapter(getSupportFragmentManager());
    private CustomViewPager viewPager;
    public HashMap<String, ArrayList<container>> Groups = new HashMap<String, ArrayList<container>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.customViewPager);
        addInList();
        setPage();
    }

    public void addInList(){
        ArrayList<container> groupAsset = new ArrayList<container>();
        groupAsset.add(new container("label", 1));
        groupAsset.add(new container("label", 2));
        groupAsset.add(new container("label", 3));
        groupAsset.add(new container("label", 4));
        groupAsset.add(new container("label", 5));
        groupAsset.add(new container("label", 6));
        groupAsset.add(new container("label", 7));
        groupAsset.add(new container("label", 8));
        groupAsset.add(new container("label", 9));
        groupAsset.add(new container("label", 10));
        groupAsset.add(new container("label", 11));
        groupAsset.add(new container("label", 12));
        groupAsset.add(new container("label", 13));
        groupAsset.add(new container("label", 14));
        groupAsset.add(new container("label", 15));
        groupAsset.add(new container("label", 16));
        groupAsset.add(new container("label", 17));
        groupAsset.add(new container("label", 18));
        groupAsset.add(new container("label", 19));
        groupAsset.add(new container("label", 20));

        Groups.put("TAB1", groupAsset);
        Groups.put("TAB2", groupAsset);
        Groups.put("TAB3", groupAsset);
        Groups.put("TAB4", groupAsset);
        Groups.put("TAB5", groupAsset);
        Groups.put("TAB6", groupAsset);
        Groups.put("TAB7", groupAsset);
        Groups.put("TAB8", groupAsset);
        Groups.put("TAB9", groupAsset);
    }

    public void setPage(){
        LinearLayout ln = (LinearLayout) this.findViewById(R.id.Button);
        ln.removeAllViews();
        int i = 0;
        for(Map.Entry<String, ArrayList<container>> m:Groups.entrySet()){
            final Button btn = new Button(this);
            btn.setId(i);
            btn.setTag(m.getKey().toString());
            final int id_ = btn.getId();
            LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams( 235 , 120);
            btnParams.setMargins(10, 0, 10, 0);
            btnParams.gravity = Gravity.CENTER;
            btn.setText(m.getKey().toString());
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            btn.setGravity(Gravity.CENTER);
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundResource(R.drawable.tab_button);
            if(i==0){
                btn.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btn.setBackgroundResource(R.drawable.tab_button_selected);
            }else{
                btn.setTextColor(getResources().getColor(R.color.white));
                btn.setBackgroundResource(R.drawable.tab_button);
            }

            CustomFragment fragment = new CustomFragment();
            ArrayList<container> arraylist = new ArrayList<container>();
            arraylist = m.getValue();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("arraylist", arraylist);
            fragment.setArguments(bundle);
            final int f = i;
            adapterIsoTank.addFragment(fragment, "f" + i);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b;
                    for(int a=0; a<Groups.size(); a++ ){
                        b = findViewById(a);
                        b.setTextColor(getResources().getColor(R.color.white));
                        b.setBackgroundResource(R.drawable.tab_button);
                    }
                    btn.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    btn.setBackgroundResource(R.drawable.tab_button_selected);
                    viewPager.setCurrentItem(f, true);
                }
            });

            ln.addView(btn, btnParams);
            i++;
        }
        viewPager.setAdapter(adapterIsoTank);
        viewPager.setPagingEnabled(false);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Constants.selectPage = viewPager.getCurrentItem();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
