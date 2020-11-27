package com.tax.fulltax;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.tax.completefactory.ITax;
import com.tax.fulltax.R;

import static android.widget.CompoundButton.*;

public class SegmentSimpleTaxFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ToggleButton tgl_isExempted;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    AdView mAdView5,mAdView;
    public SegmentSimpleTaxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfessionalsTaxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SegmentSimpleTaxFragment newInstance(String param1, String param2) {
        SegmentSimpleTaxFragment fragment = new SegmentSimpleTaxFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_simple_tax, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tgl_isExempted = getActivity().findViewById(R.id.tgl_isExempted);
        tgl_isExempted.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout frm_NON_Exempted = getActivity().findViewById(R.id.frm_Non_Exempted);
                ConstraintLayout frm_Exempted = getActivity().findViewById(R.id.frm_Exempted);
                if (tgl_isExempted.isChecked()) {
                    frm_NON_Exempted.setVisibility(View.INVISIBLE);
                    frm_Exempted.setVisibility(View.VISIBLE);
                    //Show the Tax table here depending upon the year
                    //    MainActivity mainActivity= (MainActivity) getActivity();
                    //    ITax taxEntity=mainActivity.getTheTaxEntity();
                } else {
                    frm_NON_Exempted.setVisibility(View.VISIBLE);
                    frm_Exempted.setVisibility(View.INVISIBLE);
                }
            }
        });


        mAdView5 = getActivity().findViewById(R.id.adView5);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView5.loadAd(adRequest);
        if(((MainActivity)getActivity()).isAdsOn) {
            mAdView = getActivity().findViewById(R.id.adView);
            AdRequest adRequest2 = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest2);
        }
    }
    public void update_Tax_Texts(double taxPercentage, double taxValueExempted, double taxValueUnExempted)
    {
       // CheckBox chkIsNotExempted=(CheckBox) getActivity().findViewById(R.id.notExempted);
        ToggleButton tgl_isExempted= getActivity().findViewById(R.id.tgl_isExempted);
        if(tgl_isExempted.isChecked()){
            TextView txt_taxValue2= getActivity().findViewById(R.id.lblResTaxValue2);
            txt_taxValue2.setText(String.format("%,.2f",taxValueExempted));

        }
        else{
            TextView txt_taxValue= getActivity().findViewById(R.id.lblResTaxValue_FragSmpl);
            TextView txt_taxPercentage= getActivity().findViewById(R.id.lblResTaxRatio_FragSmpl);
            txt_taxPercentage.setText(String.format("%.2f%%",taxPercentage));
            txt_taxValue.setText(String.format("%,.2f",taxValueUnExempted));
        }
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        tgl_isExempted.setChecked(true);
    }
}