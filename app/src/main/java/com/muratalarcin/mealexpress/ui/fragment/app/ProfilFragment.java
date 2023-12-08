package com.muratalarcin.mealexpress.ui.fragment.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.muratalarcin.mealexpress.MainActivity;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentProfilBinding;

public class ProfilFragment extends Fragment {
    private FragmentProfilBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfilBinding.inflate(inflater, container, false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

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