package com.muratalarcin.mealexpress.ui.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.muratalarcin.mealexpress.MainActivity;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentProfilBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.ProfilViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfilFragment extends Fragment {
    private FragmentProfilBinding binding;
    private ProfilViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfilBinding.inflate(inflater, container, false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        viewModel = new ViewModelProvider(this).get(ProfilViewModel.class);
        viewModel.getAdSoyad().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String adSoyad) {
                binding.textViewAdSoyad.setText(adSoyad);
            }
        });

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("My Pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        binding.buttonGuncelle.setOnClickListener(view -> {
            String adres = binding.editTextAdres.getText().toString();
            String adresBaslik = Objects.requireNonNull(binding.textInputLayoutAdresBaslik.getEditText()).getText().toString();
            editor.putString("adres", adres);
            editor.putString("adresBaslik", adresBaslik);
            editor.apply();
            Toast.makeText(getContext(), "Adres Bilgileri Güncellendi.", Toast.LENGTH_SHORT).show();
        });

        String kaydedilenAdres = sharedPreferences.getString("adres", "");
        String kaydedilenAdresBaslik = sharedPreferences.getString("adresBaslik", "");

        binding.editTextAdresBaslik.setText(kaydedilenAdresBaslik);
        binding.editTextAdres.setText(kaydedilenAdres);


        binding.buttonCikisYap.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();

            // Uygulamayı tekrar başlatmak için yeni bir Intent oluşturun
            Intent intent = new Intent(requireContext(), MainActivity.class); // YourMainActivity, uygulamanın başlangıç aktivitesinin adıdır.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            // Intent'i başlatın
            startActivity(intent);
            requireActivity().finish(); // fragmentin bağlı olduğu aktiviteyi sonlandırabilirsiniz.
        });


        return binding.getRoot();
    }
    // Geri tuşu dinleyicisi
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            // Geri tuşu işlemi
            // Bu bloğu boş bırakarak geri tuşunu devre dışı bırakabilirsiniz
        }
    };
}