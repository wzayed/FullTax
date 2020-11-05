package com.tax.fulltax;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tax.fulltax.R;
import com.google.android.material.tabs.TabLayout;
import com.tax.completefactory.ConcreteTaxYearEntityFactory;
import com.tax.completefactory.ITax;
import com.tax.completefactory.ITaxYearEntity;
import com.tax.completefactory.SocialStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ITax theTaxEntity = null;
    private final ITaxYearEntity taxEntityFactory = new ConcreteTaxYearEntityFactory();
    private final FragmentIndex fragment_Index = new FragmentIndex();
    private YearsFragment yearsFragment;
    TextView lblTaxLaw;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        lblTaxLaw = (TextView) findViewById(R.id.lblTaxLaw);

        Spinner yearsSpinner = findViewById(R.id.yearsSpinner);

        List<String> yearsList = new ArrayList<String>();
        ArrayAdapter<String> arAdapterYearSpinner = new ArrayAdapter<String>(this,
                R.layout.custom_list_item, yearsList);
        for (int i = 1981; i <= 2020; i++) {
            yearsList.add(Integer.toString(i));
        }
        for (int i = 2021; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
            yearsList.add(Integer.toString(i));
        }
        //commented in update 1.5 according to Alaa requirement
      //  Collections.reverse(yearsList);  //Make the current and most recent years on the top

        yearsSpinner.setAdapter(arAdapterYearSpinner);
        yearsSpinner.setSelection(yearsSpinner.getCount()-1);
        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                ///////////////////////////////
                // According to update 1.5
               // int taxYear = currentYear - i;
                int taxYear = 1981 + i;

                if (theTaxEntity == null) {
                    int k = 0;
                    while (taxYear > fragment_Index.fragmentIndex.get(k).toYear) {
                        k++;
                    }
                    theTaxEntity = taxEntityFactory.createTaxYearEntity(taxYear);
                    yearsFragment = fragment_Index.fragmentIndex.get(k);
                } else {
                    theTaxEntity = null;
                    yearsFragment = null;
                    int k = 0;
                    theTaxEntity = taxEntityFactory.createTaxYearEntity(taxYear);

                    while (taxYear > fragment_Index.fragmentIndex.get(k).toYear) {
                        k++;
                    }
                    yearsFragment = fragment_Index.fragmentIndex.get(k);
                }
                lblTaxLaw.setText("طبقا ل : " + theTaxEntity.get_WhichRuleAmI());
                configTabLayout();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void configTabLayout() {
        final ViewPager vPager = findViewById(R.id.viewPagerTab);
        final TabLayout tLayout = findViewById(R.id.tabPersonTypes);

        FragmentStatePagerAdapter fpageAdapter = new MyTabViewPager(this.getSupportFragmentManager(),
                this.yearsFragment,this.theTaxEntity.getTabHeaders());

        vPager.setAdapter(fpageAdapter);
        tLayout.setupWithViewPager(vPager);

        // vPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tLayout));
    }
    public void calcLegalEntityTax(View view){
        //Check if textbox is empty
        try {
            LegalEntityTaxFragment legal_entity_frag = (LegalEntityTaxFragment) this.yearsFragment.fragments.get(1);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));
            legal_entity_frag.update_Tax_Texts(theTaxEntity.getTaxRatioLegalEntity(), theTaxEntity.getTax_LegalPerson());
        }
        catch(NumberFormatException ex){
            //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }
    }
    public void calcTax_NoDiscount(View view){
        try {
            SegmentSimpleTaxFragment Segment_Simple_frag = (SegmentSimpleTaxFragment) this.yearsFragment.fragments.get(0);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));
            //Add Marital Status if the year is <=2005
            Segment_Simple_frag.update_Tax_Texts(theTaxEntity.getTaxRatioNormalPerson_Without_exemption(),
                    theTaxEntity.getTax_NormalPerson_WithExemption(12), theTaxEntity.getTax_NormalPersonWithout_Exemption(12));
        }
        catch(NumberFormatException ex){
            //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }
    }
    public void calcTax_Uniform_NoDiscount_Commercial(View view){
        // if year = 1981 or 1982 then show fragment that this rules applies from 1983
        Spinner yearsSpinner=(Spinner) findViewById(R.id.yearsSpinner);

        if(yearsSpinner.getSelectedItem().toString().equals("1981") || yearsSpinner.getSelectedItem().toString().equals("1982")  ){
            showCustomToast("هذا القانون ينطبق على الأرباح النجارية و الصناعية أعتبارا من السنة المالية 1983 بينما ينطبق على المهن الحرة إعتبارا من 1981");
            return;
        }
        try {
            UniformTaxFragment_Commercial uniformTaxSegmentCommercial = (UniformTaxFragment_Commercial)
                    this.yearsFragment.fragments.get(1);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));
            uniformTaxSegmentCommercial.update_Tax_Texts(theTaxEntity.getTax_LegalPerson());
        }
        catch(NumberFormatException ex){
        //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }
    }

  public void calcTax_Uniform_NoDiscount(View view){
        double taxVaue;
      try {
          UniformTaxFragment uniformTaxSegment = (UniformTaxFragment)
                  this.yearsFragment.fragments.get(0);
          EditText taxBase = findViewById(R.id.txtNetProfit);
          theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));

          ToggleButton tglbtn= findViewById(R.id.tgl_isExempted_uniform);
          if(tglbtn.isChecked()){
              Spinner scSpinner= findViewById(R.id.socialStatus_WithoutCommercialComp);
              theTaxEntity.setSocialStatus((com.tax.completefactory.SocialStatus) scSpinner.getSelectedItem());
              taxVaue=theTaxEntity.getTax_NormalPerson_WithExemption(12);
          }
          else{
              taxVaue=theTaxEntity.getTax_NormalPersonWithout_Exemption(12);
          }

          uniformTaxSegment.update_Tax_Texts(taxVaue);
      }
      catch(NumberFormatException ex){
          //Custom Toast
          showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
      }
  }
    /////////////////////////////////
    //Calc tax with discount percentages 2017 to 2019 inclusive
    public void calcTax_Discount(View view){
        double taxVaue=0,taxDiscount=0;
        int exemptioneState;
        try {
            SegmentsWithDiscountsTaxFragment withDiscountTaxSegment = (SegmentsWithDiscountsTaxFragment)
                    this.yearsFragment.fragments.get(0);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));

            ToggleButton tglbtn= findViewById(R.id.tgl_isExempted_discount);
            if(tglbtn.isChecked()){
                //Use the with discount methods
                taxVaue = theTaxEntity.getTax_NormalPerson_WithDiscount(12);
                exemptioneState=1;
                taxDiscount=theTaxEntity.getDiscount_NormalPerson_WithDiscount();
            }
            else{
               // taxVaue=theTaxEntity.getTax_NormalPersonWithout_Exemption(12);
                taxVaue = theTaxEntity.getTax_NormalPersonWithout_Exemption( 12);
                taxDiscount=theTaxEntity.getTaxRatioNormalPerson_Without_exemption();
                exemptioneState=0;
            }

            withDiscountTaxSegment.update_Tax_Texts(taxVaue,taxDiscount,exemptioneState );
        }
        catch(NumberFormatException ex){
            //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }
    }

    public void calcTax_Uniform_NoDiscount_Mowahada(View view){
        double taxValue=0,taxRatio=0;
        try {
            UniformTaxFragment_Mowahada TaxSegment_Mowahada = (UniformTaxFragment_Mowahada)
                    this.yearsFragment.fragments.get(0);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));

            //get the spinner
            //if company redirect to getlegaltax else to get normaltax withExemption
            Spinner scSpinner = findViewById(R.id.socialStatus_WithoutCommercialComp_Mowahada);

            ToggleButton tglbtn= findViewById(R.id.tgl_isExempted_uniform_Mowahada);
            if(tglbtn.isChecked()){
                if(scSpinner.getSelectedItemPosition()==0){
                    taxValue = theTaxEntity.getTax_LegalPerson();
                }
                else{
                    theTaxEntity.setSocialStatus(SocialStatus.values()[scSpinner.getSelectedItemPosition()-1]);
                    taxValue=theTaxEntity.getTax_NormalPerson_WithExemption(12);
                }

            }
            else{
                taxValue=theTaxEntity.getTax_NormalPersonWithout_Exemption(12);
                taxRatio=theTaxEntity.getTaxRatioNormalPerson_Without_exemption();
            }

            TaxSegment_Mowahada.update_Tax_Texts(taxValue,taxRatio);
        }
        catch(NumberFormatException ex){
            //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }
        catch(NullPointerException ex){
            showCustomToast("من فضلك أعد المحاولة لو تكرر الخطأ أرسل لقطة من الشاشة لنا");
        }
        catch(ClassCastException ex){
            showCustomToast("من فضلك أرسل لقطة من الشاشة و البيانات التى تسبب الخطأ لنا");
        }

    }


    public void showCustomToast(String toastMsg){
        LayoutInflater inflater=getLayoutInflater();
        ViewGroup layOut=(ViewGroup) inflater.inflate(R.layout.toastalert,(ViewGroup) findViewById(R.id.toastAlertContainer));

        TextView dynTV = new TextView(this);
        dynTV.setTextSize(20);
        dynTV.setBackgroundColor(Color.parseColor("#21D0F3"));
        dynTV.setTypeface(null, Typeface.BOLD);
        dynTV.setText(toastMsg);
        layOut.addView(dynTV);

  /*      TextView alertTV=(TextView) findViewById(R.id.alertToastTV);
        alertTV.setText(toastMsg);*/

        Toast alertToast=new Toast(getApplicationContext());
        alertToast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        alertToast.setDuration(Toast.LENGTH_LONG);

        alertToast.setView(layOut);
        alertToast.show();

    }


}