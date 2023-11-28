package com.muratalarcin.mealexpress.ui.fragment.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentAnasayfaBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.AnasayfaViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}