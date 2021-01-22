package com.tax.fulltax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class PayRoll extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<String> fromMonth;
    ArrayList<String> toMonth;
    ArrayAdapter<Integer> adptryears ;
    ArrayAdapter<String> adptrfromMonth ;
    ArrayAdapter<String> adptrtoMonth ;
    Spinner yearsSpinner ;
    Spinner fromonthSpinner ;
    Spinner toMonthSpinner ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_roll);

         yearsSpinner = findViewById(R.id.spnPayRollYear);
         fromonthSpinner = findViewById(R.id.spnPayRollFromMonth);
         toMonthSpinner = findViewById(R.id.spnPayRollToMonth);

        ArrayList<Integer> years = new ArrayList<Integer>();
        fromMonth = new ArrayList<String>() {
            {
                add("يناير");
                add("فبراير");
                add("مارس");
                add("إبريل");
                add("مايو");
                add("يونية");
                add("يوليو");
                add("أغسطس");
                add("سبتمبر");
                add("أكتوبر");
                add("نوفمبر");
                add("ديسمبر");
            }
        };
        toMonth = new ArrayList<String>();

        for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= 2021; i--) {
            years.add(i);
        }
        for (int i = 2020; i >= 1995; i--) {
            years.add(i);
        }


        adptryears = new ArrayAdapter<Integer>(this, R.layout.custom_list_item, years);
        adptrfromMonth = new ArrayAdapter<String>(this, R.layout.custom_list_item, fromMonth);


        yearsSpinner.setAdapter(adptryears);
        fromonthSpinner.setAdapter(adptrfromMonth);
        //     toMonthSpinner.setAdapter(adptrtoMonth);

        fromonthSpinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        toMonth.clear();
        toMonth.addAll(fromMonth.subList(pos,fromMonth.size()));
       adptrtoMonth = new ArrayAdapter<String>(this, R.layout.custom_list_item, toMonth);

     /* adptrtoMonth = ArrayAdapter.createFromResource(this,
                null, android.R.layout.simple_spinner_item);
        adptrtoMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        toMonthSpinner.setAdapter(adptrtoMonth);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}