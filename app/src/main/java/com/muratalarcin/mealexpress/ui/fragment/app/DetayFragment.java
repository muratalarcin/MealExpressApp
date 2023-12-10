package com.muratalarcin.mealexpress.ui.fragment.app;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muratalarcin.mealexpress.MainActivity;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.data.entity.Sepet;
import com.muratalarcin.mealexpress.data.entity.Yemekler;
import com.muratalarcin.mealexpress.databinding.FragmentDetayBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.DetayViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetayFragment extends Fragment {
    private FragmentDetayBinding binding;
    private DetayViewModel viewModel;
    private Sepet Sepet;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetayBinding.inflate(inflater, container, false);

        DetayFragmentArgs bundle = DetayFragmentArgs.fromBundle(getArguments());
        Yemekler gelenYemek = bundle.getYemek();

        ((MainActivity) requireActivity()).setBottomNavigationVisibility(false);


        binding.returnIcon.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });


        String resimAdi = gelenYemek.getYemek_resim_adi();
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimAdi;
        Glide.with(this).load(url).override(300, 300).into(binding.imageViewDetay);

        binding.twAd.setText(gelenYemek.getYemek_adi());
        binding.twAdet.setText(String.valueOf(gelenYemek.getYemek_siparis_adet()));
        binding.twFiyat.setText(String.valueOf(gelenYemek.getYemek_fiyat()));

        binding.plusFab.setOnClickListener(view -> {
            viewModel.artirAdet();
        });

        binding.minusFab.setOnClickListener(view -> {
            viewModel.azaltAdet();
        });

        // LiveData'ı dinleyerek güncellemeleri alın
        viewModel.getSiparisAdet().observe(getViewLifecycleOwner(), siparisAdet -> {
            binding.twAdet.setText(String.valueOf(siparisAdet));
        });

        viewModel.getToplamFiyat().observe(getViewLifecycleOwner(), toplamFiyat -> {
            binding.twToplam.setText(String.valueOf(toplamFiyat));
        });

        viewModel.init(gelenYemek);

        //Yemekler yemek = yemeklerListesi.get(position);
        binding.buttonSepeteEkle.setOnClickListener(view -> {
            int backgrounColor = ContextCompat.getColor(getContext(), R.color.md_theme_dark_error);
            int textColor = ContextCompat.getColor(getContext(), R.color.md_theme_light_onSecondaryContainer);
            int actionTextColor = ContextCompat.getColor(getContext(), R.color.md_theme_light_error);

            if(Integer.parseInt((String) binding.twAdet.getText()) == 0) {
                Snackbar.make(view, "Lütfen ürün miktarı seçiniz.", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(backgrounColor)
                        .setTextColor(textColor)
                        .setActionTextColor(actionTextColor)
                        .show();
            } else {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("kullanici", MODE_PRIVATE);
                String kulanici = sharedPreferences.getString("kullanici", "");

                String yemek_adi = binding.twAd.getText().toString();
                String yemek_resim_adi = resimAdi;
                int yemek_fiyat = Integer.parseInt(String.valueOf(binding.twFiyat.getText()));
                int yemek_siparis_adet = Integer.parseInt(String.valueOf(binding.twAdet.getText()));
                String kullanici_adi = kulanici;

                viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi);

                requireActivity().onBackPressed();
                Toast.makeText(getContext(), "Sepete eklendi..", Toast.LENGTH_SHORT).show();
            }

        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetayViewModel.class);

    }
}