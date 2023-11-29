package com.muratalarcin.mealexpress.ui.fragment.app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentAnasayfaBinding;
import com.muratalarcin.mealexpress.ui.adapter.YemeklerAdapter;
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

        binding.rwAnasayfa.setLayoutManager(//yan yana 2 tane row olma muhabbeti
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(), yemeklerListesi -> {
            YemeklerAdapter adapter = new YemeklerAdapter(yemeklerListesi, requireContext(), viewModel);
            binding.rwAnasayfa.setAdapter(adapter);
        } );



        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.yemekleriYukle();
    }
}