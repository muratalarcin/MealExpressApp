package com.muratalarcin.mealexpress.ui.fragment.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.muratalarcin.mealexpress.MainActivity;
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

        binding.toggleGroup.check(R.id.buttonGirisYap);

        ((MainActivity) requireActivity()).setBottomNavigationVisibility(false);

        binding.buttonKayitOl.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.giristen_kayita);
        });

        binding.buttonUygulamayaGirisYap.setOnClickListener(view -> {
            if (binding.textInputLayoutSifre.getEditText().toString().equals("admin")) {
                Navigation.findNavController(view).navigate(R.id.giristenAnasayfaya);
            }
        });


        return binding.getRoot();
    }
}