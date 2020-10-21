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

import com.tax.fulltax.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UniformTaxFragment_Mowahada#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UniformTaxFragment_Mowahada extends Fragment {
    ToggleButton tgl_isExempted;
    TextView Exempted_TVtaxVaue;
    TextView UnExempted_TVtaxVaue;
    TextView TaxRatioUnExempted;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner socialStatus;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UniformTaxFragment_Mowahada() {
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
    public static UniformTaxFragment_Mowahada newInstance(String param1, String param2) {
        UniformTaxFragment_Mowahada fragment = new UniformTaxFragment_Mowahada();
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
        return inflater.inflate(R.layout.fragment_uniform_mowahada_tax, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UnExempted_TVtaxVaue = getActivity().findViewById(R.id.lblResTaxValue_Unexempted_uniform_Mowahada);
        Exempted_TVtaxVaue = getActivity().findViewById(R.id.lblResTaxValue_exempted_uniform_Mowahada);
        TaxRatioUnExempted = getActivity().findViewById(R.id.lblResTaxRatio_Unexempted_uniform_Mowahada);

        socialStatus= getActivity().findViewById(R.id.socialStatus_WithoutCommercialComp_Mowahada);
        ArrayList<String> socialStatusAL=new ArrayList<String>();

        socialStatusAL.add("نشاط تجارى");
        socialStatusAL.add("أعزب");
        socialStatusAL.add("متزوج لا يعول");
        socialStatusAL.add("غير متزوج و يعول");
        socialStatusAL.add("متزوج و يعول");

        ArrayAdapter<String> scAdapter=
                new ArrayAdapter<String>(getActivity(),R.layout.custom_list_item_20dp, socialStatusAL);
        socialStatus.setAdapter(scAdapter);

        tgl_isExempted= getActivity().findViewById(R.id.tgl_isExempted_uniform_Mowahada);
        tgl_isExempted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout frm_NON_Exempted= getActivity().findViewById(R.id.frm_UnExempted_uniform_Mowahada);
                ConstraintLayout frm_Exempted= getActivity().findViewById(R.id.frm_Exempted_uniform_Mowahada);
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
    }
    public void update_Tax_Texts(double taxVale,double taxRatio){
        if(tgl_isExempted.isChecked()) {
            Exempted_TVtaxVaue.setText(String.format("%.2f", taxVale));
        }
        else{
            UnExempted_TVtaxVaue.setText(String.format("%.2f", taxVale));
            TaxRatioUnExempted.setText(String.format("%.2f", taxRatio));
        }
    }
}