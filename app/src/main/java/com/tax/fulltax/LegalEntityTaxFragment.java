package com.tax.fulltax;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tax.fulltax.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LegalEntityTaxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LegalEntityTaxFragment extends Fragment {
    TextView txt_taxPercentage;
    TextView txt_taxValue;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LegalEntityTaxFragment() {
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
    public static LegalEntityTaxFragment newInstance(String param1, String param2) {
        LegalEntityTaxFragment fragment = new LegalEntityTaxFragment();
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

        return inflater.inflate(R.layout.fragment_legal_entity_tax, container, false);
        //get references to the controls.
        //Calculate the tax
        //
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // EditText txtNetProfit = findViewById(R.id.txtNetProfit);

    }
    @Override
    public void onActivityCreated(Bundle SavedInstanceState){
        super.onActivityCreated(SavedInstanceState);
        txt_taxPercentage=getActivity().findViewById(R.id.lblResTaxRatio_FragLegal);
        txt_taxValue=getActivity().findViewById(R.id.lblResTaxValue_FragLegal);
    }
    public void update_Tax_Texts(double taxPercentage, double taxValue)
    {
        txt_taxPercentage.setText(String.format("%.2f%%",taxPercentage));
        txt_taxValue.setText(String.format("%.2f",taxValue));
    }
}