package com.muratalarcin.mealexpress.ui.fragment.app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
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
            String yemek_adi = binding.twAd.getText().toString();
           // String yemek_resim_adi = Sepet.getYemek_resim_adi();
            int yemek_fiyat = Integer.parseInt(String.valueOf(binding.twFiyat.getText()));
            int yemek_siparis_adet = Integer.parseInt(String.valueOf(binding.twAdet.getText()));
            String kullanici_adi = "murat";

            viewModel.sepeteEkle(yemek_adi, "Ayran.png", yemek_fiyat, yemek_siparis_adet, "murat");

        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetayViewModel.class);

    }
}