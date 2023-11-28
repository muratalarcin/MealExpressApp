package com.muratalarcin.mealexpress.ui.fragment.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentGirisYapBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.loginviewmodel.GirisYapViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GirisYapFragment extends Fragment {
    private FragmentGirisYapBinding binding;
    private GirisYapViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGirisYapBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}