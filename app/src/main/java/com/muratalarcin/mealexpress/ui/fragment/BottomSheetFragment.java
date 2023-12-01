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
}