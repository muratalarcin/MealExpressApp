package com.muratalarcin.mealexpress.ui.fragment.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentKayitOlBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.loginviewmodel.KayitOlViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class KayitOlFragment extends Fragment {
    private FragmentKayitOlBinding binding;
    private KayitOlViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentKayitOlBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}