package com.tax.fulltax;
//In version 2.2 we add
//In-App user reviews.
//Messaging and communication between users
//Parse and firebase tools

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.tax.completefactory.TaxLayersStructure2020;
import com.tax.completefactory.TaxStructure_1981_2004_Inclusive;
import com.tax.completefactory.TaxStructure_2005_2016_Inclusive;
import com.tax.completefactory.TaxStructure_2017_2019_Inclusive;
import com.tax.completefactory.TaxStructure_2020_Inclusive;
import com.tax.completefactory.Tax_2020_Above_Inclusive;
import com.tax.fulltax.R;
import com.google.android.material.tabs.TabLayout;
import com.tax.completefactory.ConcreteTaxYearEntityFactory;
import com.tax.completefactory.ITax;
import com.tax.completefactory.ITaxYearEntity;
import com.tax.completefactory.SocialStatus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static com.tax.completefactory.Constants.MAX_VALUE_INT;

public class MainActivity extends FragmentActivity {
    public static int counterToViewRatingAfter = 0;
    public static boolean askForReview = false;

    public static class MyReviewRating {
        public int getCounter() {
            return counterToViewRatingAfter;
        }

        public void incCounter() {
            counterToViewRatingAfter++;
        }

        public boolean getAskForReview() {
            return askForReview;
        }

        public void setAskforReview(boolean bVal) {
            askForReview = bVal;
        }
    }


    private FloatingActionButton fabMenu;
    private ITax theTaxEntity = null;
    private final ITaxYearEntity taxEntityFactory = new ConcreteTaxYearEntityFactory();
    private final FragmentIndex fragment_Index = new FragmentIndex();
    private YearsFragment yearsFragment;
    public boolean isAdsOn = false;
    public boolean isReviewsOn = false;
    TextView lblTaxLaw;
    ReviewManager reviewManager;
    ReviewInfo reviewInfo = null;
    private AdView mAdView;
    MyReviewRating reviewRating = new MyReviewRating();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        lblTaxLaw = (TextView) findViewById(R.id.lblTaxLaw);
        fabMenu=(FloatingActionButton)findViewById(R.id.fabdrawerMenu);
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
        yearsSpinner.setSelection(yearsSpinner.getCount() - 1);
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
                    isAdsOn = true;
                }
                lblTaxLaw.setText("طبقا ل : " + theTaxEntity.get_WhichRuleAmI());
                configTabLayout();
                if(reviewRating.getAskForReview()==true)
                    openReview();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /// Initialize adMob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        initReviewInfo();// initialize ReviewInfo

    }

    void initReviewInfo() {
        reviewManager = ReviewManagerFactory.create(this);
        //  reviewManager=new FakeReviewManager(this);
        Task<ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    reviewInfo = task.getResult();

                } else {
                }

            }
        });
        openReview();
    }

    public void openReview() {
        if (reviewInfo != null) {

            Task<Void> flow = reviewManager.launchReviewFlow(this, reviewInfo);
            flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {

                }
            });
        }
    }

    void configTabLayout() {
        final ViewPager vPager = findViewById(R.id.viewPagerTab);
        final TabLayout tLayout = findViewById(R.id.tabPersonTypes);

        FragmentStatePagerAdapter fpageAdapter = new MyTabViewPager(this.getSupportFragmentManager(),
                this.yearsFragment, this.theTaxEntity.getTabHeaders());

        vPager.setAdapter(fpageAdapter);
        tLayout.setupWithViewPager(vPager);

        vPager.beginFakeDrag(); // To disable swiping of the page viewer
        // vPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tLayout));
    }

    public void calcLegalEntityTax(View view) {
        //Check if textbox is empty
        try {
            LegalEntityTaxFragment legal_entity_frag = (LegalEntityTaxFragment) this.yearsFragment.fragments.get(1);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));
            legal_entity_frag.update_Tax_Texts(theTaxEntity.getTaxRatioLegalEntity(), theTaxEntity.getTax_LegalPerson());
            closeKB();
            trackWhenShowRating();
        } catch (NumberFormatException ex) {
            //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }

    }

    public void calcTax_NoDiscount(View view) {
        try {
            SegmentSimpleTaxFragment Segment_Simple_frag = (SegmentSimpleTaxFragment) this.yearsFragment.fragments.get(0);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));
            //Add Marital Status if the year is <=2005
            Segment_Simple_frag.update_Tax_Texts(theTaxEntity.getTaxRatioNormalPerson_Without_exemption(),
                    theTaxEntity.getTax_NormalPerson_WithExemption(12), theTaxEntity.getTax_NormalPersonWithout_Exemption(12));
            //END of Showtax without details

            //BEGIN ofShowing the details of exempted entities tax
            //We need to get the list inside the taxrule

            //  get the list of TextViews in the container and access the array
            TableLayout tblLayout = (TableLayout) findViewById(R.id.tblTaxDetails);
            ArrayList<TextView> tvArrayinTable = getTVArrayinTable(tblLayout);

            //If year 2020 and above gerList<Tax2020>
            Spinner yearSpinner = (Spinner) findViewById(R.id.yearsSpinner);

            ArrayList<TaxStructure_2020_Inclusive> taxstructure2020;
            ArrayList<TaxStructure_2005_2016_Inclusive> taxstructure;
            TextView tvTaxLayer = (TextView) findViewById(R.id.taxlayer);

            if (Integer.parseInt(yearSpinner.getSelectedItem().toString()) >= 2020) {
                //Make 6th and 7th row visible
                TableRow tr6 = (TableRow) findViewById(R.id.sixthRowColor);
                TableRow tr7 = (TableRow) findViewById(R.id.seventhRow);

                tr6.setVisibility(View.VISIBLE);
                tr7.setVisibility(View.VISIBLE);

                TaxLayersStructure2020 TLayer2020 = ((Tax_2020_Above_Inclusive) theTaxEntity).getTaxLayer();
                int[] currentLayer = TLayer2020.getLayerInfo();
                int currentLayerIndex = currentLayer[2];

                taxstructure2020 = theTaxEntity.getTaxStructure();  // contains the data

                for (int i = 0; i < taxstructure2020.size(); i++) {
                    if (i >= currentLayerIndex) {
                        TaxStructure_2020_Inclusive mdl2020 = taxstructure2020.get(i);
                        tvArrayinTable.get(i * 5).setText(String.format("%,.1f", mdl2020.getTaxValueInThisSegment()));
                        tvArrayinTable.get(i * 5 + 1).setText(Double.toString(mdl2020.getTaxPercentageInThisSegment()));
                        tvArrayinTable.get(i * 5 + 2).setText((mdl2020.getToAmount() == MAX_VALUE_INT) ? "-" : String.format("%,d", mdl2020.getToAmount()));
                        tvArrayinTable.get(i * 5 + 3).setText((i == currentLayerIndex) ? "1" : String.format("%,d", mdl2020.getFromAmount()));
                    } else {
                        TaxStructure_2020_Inclusive mdl2020 = taxstructure2020.get(i);
                        tvArrayinTable.get(i * 5).setText("");
                        tvArrayinTable.get(i * 5 + 1).setText(Double.toString(mdl2020.getTaxPercentageInThisSegment()));
                        tvArrayinTable.get(i * 5 + 2).setText("");
                        tvArrayinTable.get(i * 5 + 3).setText("");
                    }
                    tvTaxLayer.setVisibility(View.VISIBLE);
                    tvTaxLayer.setText("طبقة الضريبة من " + String.format("%,d", currentLayer[0]) + "إلى " + ((currentLayer[1] == MAX_VALUE_INT) ? "-" : String.format("%,d", currentLayer[1])));
                }
            } else {
                //Else get the list of 2005 to 2016 Inclusive
                taxstructure = theTaxEntity.getTaxStructure();

                for (int i = 0; i < taxstructure.size(); i++) {
                    TaxStructure_2005_2016_Inclusive mdl20052016 = taxstructure.get(i);
                    tvArrayinTable.get(i * 5).setText(String.format("%,.1f", mdl20052016.getTaxValueInThisSegment()));
                    tvArrayinTable.get(i * 5 + 1).setText(Double.toString(mdl20052016.getTaxPercentageInThisSegment()));
                    tvArrayinTable.get(i * 5 + 2).setText((mdl20052016.getToAmount() == MAX_VALUE_INT) ? "-" : String.format("%,d", mdl20052016.getToAmount()));
                    tvArrayinTable.get(i * 5 + 3).setText(String.format("%,d", mdl20052016.getFromAmount()));
                    // tvArrayinTable.get(i*5+4).setText(); // This one contains the segment
                }
                tvTaxLayer.setVisibility(View.GONE);
                //  tvTaxLayer.setText("");
            }
            //Fill The Array of TextViews we got before from the table
            closeKB();

           if (isAdsOn == false) {
                isAdsOn = true;
                mAdView = findViewById(R.id.adView);
                AdRequest adRequest2 = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest2);
            }

            trackWhenShowRating();

        } catch (NumberFormatException ex) {
            //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }
    }


    public void calcTax_Uniform_NoDiscount_Commercial(View view) {
        // if year = 1981 or 1982 then show fragment that this rules applies from 1983
        //  closeKB();
        Spinner yearsSpinner = (Spinner) findViewById(R.id.yearsSpinner);

        if (yearsSpinner.getSelectedItem().toString().equals("1981") || yearsSpinner.getSelectedItem().toString().equals("1982")) {
            showCustomToast("هذا القانون ينطبق على الأرباح النجارية و الصناعية أعتبارا من السنة المالية 1983 بينما ينطبق على المهن الحرة إعتبارا من 1981");
            return;
        }
        try {
            UniformTaxFragment_Commercial uniformTaxSegmentCommercial = (UniformTaxFragment_Commercial)
                    this.yearsFragment.fragments.get(1);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));
            uniformTaxSegmentCommercial.update_Tax_Texts(theTaxEntity.getTax_LegalPerson());
            // Fill the tax details table
            //Fill The Tax details table
            TableLayout tblLayoutdetails = (TableLayout) findViewById(R.id.tblTaxDetails_uniform_Commercial);
            ArrayList<TextView> tvArrayinTable = getTVArrayinTable(tblLayoutdetails);   //use as container tblTaxDetails_discount
            ArrayList<TaxStructure_1981_2004_Inclusive> taxstructure;

            taxstructure = theTaxEntity.getTaxStructure();
            for (int i = 4; i < taxstructure.size(); i++) {
                TaxStructure_1981_2004_Inclusive mdl2020 = taxstructure.get(i);
                tvArrayinTable.get(i * 6 - 24).setText(String.format("%,.1f", mdl2020.getTaxValueInThisSegment()));
                tvArrayinTable.get(i * 6 - 24 + 1).setText(String.format("%,.1f", mdl2020.gettaxDiscountAmount()));
                tvArrayinTable.get(i * 6 - 24 + 2).setText(String.format("%,.1f", mdl2020.getTaxPercentageInThisSegment()));
                tvArrayinTable.get(i * 6 - 24 + 3).setText((mdl2020.getToAmount() == MAX_VALUE_INT) ? "-" : String.format("%,d", mdl2020.getToAmount()));
                tvArrayinTable.get(i * 6 - 24 + 4).setText(String.format("%,d", mdl2020.getFromAmount()));
            }
            closeKB();
            trackWhenShowRating();
        } catch (NumberFormatException ex) {
            //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }

    }

    public void calcTax_Uniform_NoDiscount(View view) {
        double taxVaue = 0.00, taxRatio = 0.00;
        try {
            UniformTaxFragment uniformTaxSegment = (UniformTaxFragment)
                    this.yearsFragment.fragments.get(0);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));

            ToggleButton tglbtn = findViewById(R.id.tgl_isExempted_uniform);
            if (tglbtn.isChecked()) {
                Spinner scSpinner = findViewById(R.id.socialStatus_WithoutCommercialComp);
                theTaxEntity.setSocialStatus(SocialStatus.values()[scSpinner.getSelectedItemPosition()]);
                taxVaue = theTaxEntity.getTax_NormalPerson_WithExemption(12);
                //Fill The Tax details table
                TableLayout tblLayoutdetails = (TableLayout) findViewById(R.id.tblTaxDetails_uniform_prof);
                ArrayList<TextView> tvArrayinTable = getTVArrayinTable(tblLayoutdetails);   //use as container tblTaxDetails_discount
                ArrayList<TaxStructure_1981_2004_Inclusive> taxstructure;

                taxstructure = theTaxEntity.getTaxStructure();
                for (int i = 0; i < 4; i++) {
                    TaxStructure_1981_2004_Inclusive mdl2020 = taxstructure.get(i);
                    tvArrayinTable.get(i * 6).setText(String.format("%,.1f", mdl2020.getTaxValueInThisSegment()));
                    tvArrayinTable.get(i * 6 + 1).setText(String.format("%,.1f", mdl2020.gettaxDiscountAmount()));
                    tvArrayinTable.get(i * 6 + 2).setText(String.format("%,.1f", mdl2020.getTaxPercentageInThisSegment()));
                    tvArrayinTable.get(i * 6 + 3).setText((mdl2020.getToAmount() == MAX_VALUE_INT) ? "-" : String.format("%,d", mdl2020.getToAmount()));
                    tvArrayinTable.get(i * 6 + 4).setText(String.format("%,d", mdl2020.getFromAmount()));
                }

            } else {
                taxVaue = theTaxEntity.getTax_NormalPersonWithout_Exemption(12);
                taxRatio = theTaxEntity.getTaxRatioNormalPerson_Without_exemption();
            }

            uniformTaxSegment.update_Tax_Texts(taxVaue, taxRatio);
            closeKB();
            trackWhenShowRating();
        } catch (NumberFormatException ex) {
            //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }

    }

    /////////////////////////////////
    //Calc tax with discount percentages 2017 to 2019 inclusive
    public void calcTax_Discount(View view) {
        double taxVaue = 0, taxDiscount = 0;
        int exemptioneState;
        try {
            SegmentsWithDiscountsTaxFragment withDiscountTaxSegment = (SegmentsWithDiscountsTaxFragment)
                    this.yearsFragment.fragments.get(0);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));

            ToggleButton tglbtn = findViewById(R.id.tgl_isExempted_discount);
            if (tglbtn.isChecked()) {
                //Use the with discount methods
                taxVaue = theTaxEntity.getTax_NormalPerson_WithDiscount(12);
                exemptioneState = 1;
                taxDiscount = theTaxEntity.getDiscount_NormalPerson_WithDiscount();

                //BEGIN ofShowing the details of exempted entities tax
                //We need to get the list inside the taxrule
                //  get the list of TextViews in the container and access the array
                TableLayout tblLayoutdetails = (TableLayout) findViewById(R.id.tblTaxDetails_discount);
                ArrayList<TextView> tvArrayinTable = getTVArrayinTable(tblLayoutdetails);   //use as container tblTaxDetails_discount
                ArrayList<TaxStructure_2017_2019_Inclusive> taxstructure;

                taxstructure = theTaxEntity.getTaxStructure();
                for (int i = 0; i < taxstructure.size(); i++) {
                    TaxStructure_2017_2019_Inclusive mdl2020 = taxstructure.get(i);
                    tvArrayinTable.get(i * 5).setText(String.format("%,.1f", mdl2020.getTaxValueInThisSegment()));
                    tvArrayinTable.get(i * 5 + 1).setText(String.format("%,.1f", mdl2020.getTaxPercentageInThisSegment()));
                    tvArrayinTable.get(i * 5 + 2).setText((mdl2020.getToAmount() == MAX_VALUE_INT) ? "-" : String.format("%,d", mdl2020.getToAmount()));
                    tvArrayinTable.get(i * 5 + 3).setText(String.format("%,d", mdl2020.getFromAmount()));
                }
            } else {
                // taxVaue=theTaxEntity.getTax_NormalPersonWithout_Exemption(12);
                taxVaue = theTaxEntity.getTax_NormalPersonWithout_Exemption(12);
                taxDiscount = theTaxEntity.getTaxRatioNormalPerson_Without_exemption();
                exemptioneState = 0;
            }

            withDiscountTaxSegment.update_Tax_Texts(taxVaue, taxDiscount, exemptioneState);
            closeKB();
            trackWhenShowRating();
        } catch (NumberFormatException ex) {
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        }

    }

    public void calcTax_Uniform_NoDiscount_Mowahada(View view) {
        //  closeKB();
        double taxValue = 0, taxRatio = 0;
        try {
            UniformTaxFragment_Mowahada TaxSegment_Mowahada = (UniformTaxFragment_Mowahada)
                    this.yearsFragment.fragments.get(0);
            EditText taxBase = findViewById(R.id.txtNetProfit);
            theTaxEntity.setTaxBase(Double.parseDouble(taxBase.getText().toString()));

            //get the spinner
            //if company redirect to getlegaltax else to get normaltax withExemption
            Spinner scSpinner = findViewById(R.id.socialStatus_WithoutCommercialComp_Mowahada);

            ToggleButton tglbtn = findViewById(R.id.tgl_isExempted_uniform_Mowahada);
            if (tglbtn.isChecked()) {
                if (scSpinner.getSelectedItemPosition() == 0) {
                    taxValue = theTaxEntity.getTax_LegalPerson();
                } else {
                    theTaxEntity.setSocialStatus(SocialStatus.values()[scSpinner.getSelectedItemPosition() - 1]);
                    taxValue = theTaxEntity.getTax_NormalPerson_WithExemption(12);
                }
                //Fill The Tax details table
                TableLayout tblLayoutdetails = (TableLayout) findViewById(R.id.tblTaxDetails_uniform_mowahada);
                ArrayList<TextView> tvArrayinTable = getTVArrayinTable(tblLayoutdetails);   //use as container tblTaxDetails_discount
                ArrayList<TaxStructure_1981_2004_Inclusive> taxstructure;

                taxstructure = theTaxEntity.getTaxStructure();
                for (int i = 0; i < taxstructure.size(); i++) {
                    TaxStructure_1981_2004_Inclusive mdl2020 = taxstructure.get(i);
                    tvArrayinTable.get(i * 6).setText(String.format("%,.1f", mdl2020.getTaxValueInThisSegment()));
                    tvArrayinTable.get(i * 6 + 1).setText(String.format("%,.1f", mdl2020.gettaxDiscountAmount()));
                    tvArrayinTable.get(i * 6 + 2).setText(String.format("%,.1f", mdl2020.getTaxPercentageInThisSegment()));
                    tvArrayinTable.get(i * 6 + 3).setText((mdl2020.getToAmount() == MAX_VALUE_INT) ? "-" : String.format("%,d", mdl2020.getToAmount()));
                    tvArrayinTable.get(i * 6 + 4).setText(String.format("%,d", mdl2020.getFromAmount()));
                }

            } else {
                taxValue = theTaxEntity.getTax_NormalPersonWithout_Exemption(12);
                taxRatio = theTaxEntity.getTaxRatioNormalPerson_Without_exemption();
            }

            TaxSegment_Mowahada.update_Tax_Texts(taxValue, taxRatio);
            closeKB();
            trackWhenShowRating();
        } catch (NumberFormatException ex) {
            //Custom Toast
            showCustomToast("من فضلك إدحل صافى الدخل بإسلوب صخيخ");
        } catch (NullPointerException ex) {
            showCustomToast("من فضلك أعد المحاولة لو تكرر الخطأ أرسل لقطة من الشاشة لنا");
        } catch (ClassCastException ex) {
            showCustomToast("من فضلك أرسل لقطة من الشاشة و البيانات التى تسبب الخطأ لنا");
        }

    }


    public void showCustomToast(String toastMsg) {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup layOut = (ViewGroup) inflater.inflate(R.layout.toastalert, (ViewGroup) findViewById(R.id.toastAlertContainer));

        TextView dynTV = new TextView(this);
        dynTV.setTextSize(20);
        dynTV.setBackgroundColor(Color.parseColor("#21D0F3"));
        dynTV.setTypeface(null, Typeface.BOLD);
        dynTV.setText(toastMsg);
        layOut.addView(dynTV);

  /*      TextView alertTV=(TextView) findViewById(R.id.alertToastTV);
        alertTV.setText(toastMsg);*/

        Toast alertToast = new Toast(getApplicationContext());
        alertToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        alertToast.setDuration(Toast.LENGTH_LONG);

        alertToast.setView(layOut);
        alertToast.show();

    }

    ArrayList<TextView> getTVArrayinTable(TableLayout tblLayout) {
        ArrayList<TextView> tvArray = new ArrayList<TextView>();

        //   TableLayout tblLayout= (TableLayout) findViewById(R.id.tblTaxDetails);
        boolean headerRow = true;
        for (int i = 0; i < tblLayout.getChildCount(); i++) {
            if (tblLayout.getChildAt(i) instanceof TableRow) {
                if (headerRow) headerRow = false;  // the header row
                else {
                    ViewGroup tblrow = ((ViewGroup) tblLayout.getChildAt(i));
                    for (int k = 0; k < tblrow.getChildCount(); k++) {
                        if (tblrow.getChildAt(k) instanceof TextView) {
                            tvArray.add((TextView) tblrow.getChildAt(k));
                        }
                    }
                }
            }
        }
        return tvArray;
    }

    public void closeKB() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {

        if(reviewRating.getAskForReview())  openReview();

        View alertDialogView = getLayoutInflater().inflate(R.layout.alertdialogsimple,null);
        new AlertDialog.Builder(this)
                .setView(alertDialogView)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                        finishAndRemoveTask();
                        System.exit(0);
                    }
                }).create().show();
      //  Log.i("TAG", "Add the cuasom layout");

    }

    void trackWhenShowRating() {
        if (reviewRating.getCounter() >= 5) {
            reviewRating.setAskforReview(true);
        } else {
            reviewRating.incCounter();
        }
    }


    @SuppressLint("RestrictedApi")
    public void fabShowMenu(View fanMenu){
      //  this.openOptionsMenu();
            PopupMenu popup = new PopupMenu(this, fanMenu);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.navmenu, popup.getMenu());

            popup.show();

        try {
            Field mFieldPopup=popup.getClass().getDeclaredField("mPopup");
            mFieldPopup.setAccessible(true);
            MenuPopupHelper mPopup = (MenuPopupHelper) mFieldPopup.get(popup);
            mPopup.setForceShowIcon(true);
        } catch (Exception e) {

        }
        popup.getMenu().getItem(0).setVisible(false);
    }


    ////////////////////////////////////////////////////////////////////////
    //Menu Functions
   public boolean openFullTax(MenuItem v){
        return true;
    }
    public boolean openPayroll(MenuItem v){
        return true;
    }
    public boolean openMosahmaTakafolia(MenuItem menu){
        Intent mosTakafoliaIntent=new Intent(this,mosahmatakafoliaActivity.class);
        startActivity(mosTakafoliaIntent);
        return true;
    }


} //End of the class