package com.example.fulltax;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.completefactory.ConcreteTaxYearEntityFactory;
import com.example.completefactory.ITax;
import com.example.completefactory.ITaxYearEntity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ITax theTaxEntity = null;
    private ITaxYearEntity taxEntityFactory = new ConcreteTaxYearEntityFactory();
    private FragmentIndex fragment_Index = new FragmentIndex();
    private YearsFragment yearsFragment;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Spinner yearsSpinner = (Spinner) findViewById(R.id.yearsSpinner);
        List<String> yearsList = new ArrayList<String>();
        ArrayAdapter<String> arAdapterYearSpinner = new ArrayAdapter<String>(this,
                R.layout.custom_list_item, yearsList);
        for (int i = 1981; i <= 2020; i++) {
            yearsList.add(Integer.toString(i));
        }
        for (int i = 2021; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
            yearsList.add(Integer.toString(i));
        }
        Collections.reverse(yearsList);  //Make the current and most recent years on the top

        yearsSpinner.setAdapter(arAdapterYearSpinner);

        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int taxYear = currentYear - i;

                if (theTaxEntity == null) {
                    theTaxEntity = taxEntityFactory.createTaxYearEntity(taxYear);
                    int k = 0;
                    while (taxYear > fragment_Index.fragmentIndex.get(k).toYear) {
                        k++;
                    }
                    yearsFragment = fragment_Index.fragmentIndex.get(k);
                    Log.i("Debug-Year", Integer.toString(taxYear));
                } else {
                    theTaxEntity = null;
                    yearsFragment = null;
                    theTaxEntity = taxEntityFactory.createTaxYearEntity(taxYear);
                    int k = 0;
                    while (taxYear > fragment_Index.fragmentIndex.get(k).toYear) {
                        k++;
                    }
                    yearsFragment = fragment_Index.fragmentIndex.get(k);
                    Log.i("Debug-Year", Integer.toString(taxYear));
                }
                configTabLayout();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    void configTabLayout() {
        final ViewPager vPager = (ViewPager) findViewById(R.id.viewPagerTab);
        final TabLayout tLayout = (TabLayout) findViewById(R.id.tabPersonTypes);

        FragmentStatePagerAdapter fpageAdapter = new MyTabViewPager(this.getSupportFragmentManager(),
                this.yearsFragment,this.theTaxEntity.getTabHeaders());

        vPager.setAdapter(fpageAdapter);
        tLayout.setupWithViewPager(vPager);

        // vPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tLayout));
    }
    public void calcLegalEntityTax(View view){
//Check if textbox is empty
        LegalEntityTaxFragment legal_entity_frag=(LegalEntityTaxFragment) this.yearsFragment.fragments.get(1);
        EditText taxBase=(EditText) findViewById(R.id.txtNetProfit);
        theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));
        legal_entity_frag.update_Tax_Texts(theTaxEntity.getTaxRatioLegalEntity(),theTaxEntity.getTax_LegalPerson());
    }
}