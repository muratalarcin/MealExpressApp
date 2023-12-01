package com.muratalarcin.mealexpress.ui.fragment;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.databinding.FragmentBottomSheetBinding;


public class BottomSheetFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    private FragmentBottomSheetBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false);




        return binding.getRoot();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Bottom sheet'i fragment açıldığında otomatik olarak açık göstermek için
        if (getDialog() != null) {
            getDialog().getWindow().setDimAmount(0.0f); // Arka planı tamamen saydam yap
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

}