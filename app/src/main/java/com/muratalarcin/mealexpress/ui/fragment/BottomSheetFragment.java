package com.muratalarcin.mealexpress.ui.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.data.entity.SepetString;
import com.muratalarcin.mealexpress.data.entity.YemekAdetBilgisi;
import com.muratalarcin.mealexpress.databinding.FragmentBottomSheetBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BottomSheetFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    private FragmentBottomSheetBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false);

        SharedPreferences sharedpref = getContext().getSharedPreferences("Bilgi", MODE_PRIVATE);
        String toplam = sharedpref.getString("1", "");

        binding.textViewToplamFiyat.setText(toplam + "₺");

        return binding.getRoot();
    }
    /*@Override
    public void onStart() {
        super.onStart();
        // Bottom sheet'i fragment açıldığında otomatik olarak açık göstermek için
        if (getDialog() != null) {
            getDialog().getWindow().setDimAmount(0.0f); // Arka planı tamamen saydam yap
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }*/
}