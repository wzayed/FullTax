package com.tax.fulltax;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.tax.completefactory.SocialStatus;
import com.tax.fulltax.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UniformTaxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UniformTaxFragment extends Fragment {
    ToggleButton tgl_isExempted;
    TextView Exempted_TVtaxVaue;
    TextView UnExempted_TVtaxVaue,UnExempted_TVtaxRatio;
    AdView mAdView6;
    AdView mAdView7;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner socialStatus;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UniformTaxFragment() {
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
    public static UniformTaxFragment newInstance(String param1, String param2) {
        UniformTaxFragment fragment = new UniformTaxFragment();
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
        return inflater.inflate(R.layout.fragment_uniform_tax, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UnExempted_TVtaxVaue = getActivity().findViewById(R.id.lblResTaxValue_Unexempted_uniform);
        Exempted_TVtaxVaue = getActivity().findViewById(R.id.lblResTaxValue_exempted_uniform);
        UnExempted_TVtaxRatio = getActivity().findViewById(R.id.lblResTaxRatio_Unexempted_uniform);
        socialStatus= getActivity().findViewById(R.id.socialStatus_WithoutCommercialComp);

        ArrayList<String> socialStatusAL=new ArrayList<String>();

        socialStatusAL.add("أعزب");
        socialStatusAL.add("متزوج لا يعول");
        socialStatusAL.add("غير متزوج و يعول");
        socialStatusAL.add("متزوج و يعول");

    /*    ArrayList<SocialStatus> socialStatusAL=new ArrayList<SocialStatus>();
        socialStatusAL.add(SocialStatus.Single);
        socialStatusAL.add(SocialStatus.MarriedNotSponsoring);
        socialStatusAL.add(SocialStatus.NotMarriedSponsor);
        socialStatusAL.add(SocialStatus.MarriedSponsor);*/

        ArrayAdapter<String> scAdapter=
                new ArrayAdapter<String>(getActivity(),R.layout.custom_list_item_20dp, socialStatusAL);
        socialStatus.setAdapter(scAdapter);

        tgl_isExempted= getActivity().findViewById(R.id.tgl_isExempted_uniform);
        tgl_isExempted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout frm_NON_Exempted= getActivity().findViewById(R.id.frm_UnExempted_uniform);
                ConstraintLayout frm_Exempted= getActivity().findViewById(R.id.frm_Exempted_uniform);
                if(tgl_isExempted.isChecked()){
                    frm_NON_Exempted.setVisibility(View.INVISIBLE);
                    frm_Exempted.setVisibility(View.VISIBLE);
                    socialStatus.setVisibility(View.VISIBLE);
                }
                else
                {
                    frm_NON_Exempted.setVisibility(View.VISIBLE);
                    frm_Exempted.setVisibility(View.INVISIBLE);
                    socialStatus.setVisibility(View.INVISIBLE);
                }
            }
        });
        mAdView6 = getActivity().findViewById(R.id.adView6);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView6.loadAd(adRequest);

        mAdView7 = getActivity().findViewById(R.id.adView_uniform);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView7.loadAd(adRequest2);
    }
    public void update_Tax_Texts(double taxVale,double taxRatio){
        if(tgl_isExempted.isChecked()) {
            Exempted_TVtaxVaue.setText(String.format("%,.2f", taxVale));
        }
        else{
            UnExempted_TVtaxVaue.setText(String.format("%,.2f", taxVale));
            UnExempted_TVtaxRatio.setText(String.format("%,.2f", taxRatio));
        }

    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        tgl_isExempted.setChecked(true);
    }
}