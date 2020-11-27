package com.tax.fulltax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.lang.reflect.Field;

public class mosahmatakafoliaActivity extends AppCompatActivity {
    TextView tvResult;
    EditText inputTotalIncome;
    private AdView mAdView;
    MainActivity.MyReviewRating reviewRating = new MainActivity.MyReviewRating();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosahmatakafolia);
        tvResult = (TextView) findViewById(R.id.lblResultValue);
        inputTotalIncome = (EditText) findViewById(R.id.editTotalIncom);

        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        try {
            this.getSupportActionBar().hide();
        } catch (Exception e) {

        }
    }

    public void calcMosahmaTakafoliabtn(View v) {
        try {
            double dblTemp = Double.parseDouble(inputTotalIncome.getText().toString());
            double result = dblTemp * 2.5 / 1000;
            tvResult.setText(String.format("%.2f", result));

            closeKB();
            trackWhenShowRating();
        } catch (NumberFormatException ex) {
            //Custom Toast
            showCustomToast("من فضلك إجمالى الإيراد بإسلوب صخيخ");
        }
    }

    void trackWhenShowRating() {
        if (reviewRating.getCounter() >= 5) {
            reviewRating.setAskforReview(true);
        } else {
            reviewRating.incCounter();
        }
    }

    @SuppressLint("RestrictedApi")
    public void fabShowMenu(View fanMenu) {
        //  this.openOptionsMenu();
        PopupMenu popup = new PopupMenu(this, fanMenu);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.navmenu, popup.getMenu());
        popup.show();
        try {
            Field mFieldPopup = popup.getClass().getDeclaredField("mPopup");
            mFieldPopup.setAccessible(true);
            MenuPopupHelper mPopup = (MenuPopupHelper) mFieldPopup.get(popup);
            mPopup.setForceShowIcon(true);
        } catch (Exception e) {

        }
        popup.getMenu().getItem(1).setVisible(false);
    }

    ////////////////////////////////////////////////////////////////////////
    //Menu Functions
    public boolean openFullTax(MenuItem v) {
       /* Intent mainActivity=new Intent(this,MainActivity.class);
        startActivity(mainActivity);*/
        finish();
        return true;
    }

    public boolean openPayroll(MenuItem v) {
        return true;
    }

    public boolean openMosahmaTakafolia(MenuItem v) {
        return true;
    }

    public void closeKB() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
}