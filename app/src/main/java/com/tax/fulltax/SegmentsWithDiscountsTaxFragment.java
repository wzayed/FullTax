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
import com.tax.fulltax.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SegmentsWithDiscountsTaxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SegmentsWithDiscountsTaxFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ToggleButton tgl_isExempted;
    TextView tvTaxwithDiscount,tvTaxDiscount,tvTaxWithoutDiscount,tvTaxPercentageUnexempted;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AdView mAdView3;
    private AdView mAdView;
    public SegmentsWithDiscountsTaxFragment() {
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
    public static SegmentsWithDiscountsTaxFragment newInstance(String param1, String param2) {
        SegmentsWithDiscountsTaxFragment fragment = new SegmentsWithDiscountsTaxFragment();
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
        View v= inflater.inflate(R.layout.fragment_with_discount_tax, container, false);

        return v;
                //inflater.inflate(R.layout.fragment_Commercial_tax, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tgl_isExempted= (ToggleButton) (getActivity().findViewById(R.id.tgl_isExempted_discount));

        tgl_isExempted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout frm_NON_Exempted= getActivity().findViewById(R.id.frm_UnExempted_discount);
                ConstraintLayout frm_Exempted= getActivity().findViewById(R.id.frm_Exempted_discount);
                if(tgl_isExempted.isChecked()){
                    frm_NON_Exempted.setVisibility(View.INVISIBLE);
                    frm_Exempted.setVisibility(View.VISIBLE);
                }
                else
                {
                    frm_NON_Exempted.setVisibility(View.VISIBLE);
                    frm_Exempted.setVisibility(View.INVISIBLE);
                }
            }
        });



        mAdView3 = getActivity().findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView3.loadAd(adRequest);
        switchAdsOn();

    }

    public void update_Tax_Texts(double taxValue, double taxDiscount, int exemptioneState){
        ToggleButton tgl_isExempted = getActivity().findViewById(R.id.tgl_isExempted_discount);
        if(tgl_isExempted.isChecked()){

             TextView tvTaxwithDiscount= getActivity().findViewById(R.id.lblResTaxValue2_discount);
            TextView tvTaxDiscount= getActivity().findViewById(R.id.lblResTaxRatio_FragSmpl_discount);
            TextView tvTaxWithoutDiscount= getActivity().findViewById(R.id.lblResTaxValue2_discount2);

            tvTaxwithDiscount.setText(String.format("%,.2f",taxValue ));
            tvTaxDiscount.setText(String.format("%.2f",taxDiscount ));
            tvTaxWithoutDiscount.setText(String.format("%,.2f",(taxValue*100/(100-taxDiscount) )));

        }
        else{
            TextView tvText= getActivity().findViewById(R.id.lblResTaxValue_FragSmpl_discount);
            tvTaxPercentageUnexempted= getActivity().findViewById(R.id.lblResTaxPercentage_FragSmpl_discount);

            tvText.setText(String.format("%,.2f",taxValue ));
            tvTaxPercentageUnexempted.setText(String.format("%,.2f",taxDiscount ));
        }
    }
    void switchAdsOn(){
        mAdView = getActivity().findViewById(R.id.adView_disc);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        tgl_isExempted.setChecked(true);
    }
}