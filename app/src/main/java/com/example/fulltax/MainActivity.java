package com.example.fulltax;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.completefactory.ConcreteTaxYearEntityFactory;
import com.example.completefactory.ITax;
import com.example.completefactory.ITaxYearEntity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ITax theTaxEntity=null;
    private ITaxYearEntity taxEntityFactory= new ConcreteTaxYearEntityFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Spinner yearsSpinner=(Spinner) findViewById(R.id.yearsSpinner);
        List<Integer> yearsList=new ArrayList<Integer>();
        ArrayAdapter<Integer> arAdapterYearSpinner=new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_single_choice,yearsList);
        for(int i =1981;i<=2020;i++){
            yearsList.add(i);
        }
        for(int i = 2021; i <= Calendar.getInstance().get(Calendar.YEAR); i++){
            yearsList.add(i);
        }
        yearsSpinner.setAdapter(arAdapterYearSpinner);

        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(theTaxEntity ==null){
                    theTaxEntity= taxEntityFactory.createTaxYearEntity(1981+i);
                }
                else{
                    theTaxEntity=null;
                    theTaxEntity= taxEntityFactory.createTaxYearEntity(1981+i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        configTabLayout();
    }

    void configTabLayout(){
        ViewPager vPager=(ViewPager) findViewById(R.id.viewPagerTab);
        TabLayout tLayout=(TabLayout) findViewById(R.id.tabPersonTypes);
        
    }

}