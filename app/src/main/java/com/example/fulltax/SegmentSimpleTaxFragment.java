package com.example.fulltax;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import static android.widget.CompoundButton.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfessionalsTaxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SegmentSimpleTaxFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        CheckBox chkNotEcempted= (CheckBox) getActivity().findViewById(R.id.notExempted);

        chkNotEcempted.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ConstraintLayout frm= (ConstraintLayout) getActivity().findViewById(R.id.frm_Non_Exempted);
                ConstraintLayout frm2= (ConstraintLayout) getActivity().findViewById(R.id.frm_Exempted);
                if(isChecked){
                    frm.setVisibility(View.VISIBLE);
                    frm2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    frm.setVisibility(View.INVISIBLE);
                    frm2.setVisibility(View.VISIBLE);
                }
            }});
    }
    public void update_Tax_Texts(double taxPercentage, double taxValueExempted, double taxValueUnExempted)
    {
        CheckBox chkIsNotExempted=(CheckBox) getActivity().findViewById(R.id.notExempted);
        if(chkIsNotExempted.isChecked()){
            TextView txt_taxValue=(TextView) getActivity().findViewById(R.id.lblResTaxValue);
            TextView txt_taxPercentage=(TextView) getActivity().findViewById(R.id.lblResTaxRatio);
            txt_taxPercentage.setText(String.format("%.2f%%",taxPercentage));
            txt_taxValue.setText(String.format("%.2f",taxValueUnExempted));
        }
        else{
            TextView txt_taxValue2=(TextView) getActivity().findViewById(R.id.lblResTaxValue2);
            txt_taxValue2.setText(String.format("%.2f",taxValueExempted));
        }

    }
}