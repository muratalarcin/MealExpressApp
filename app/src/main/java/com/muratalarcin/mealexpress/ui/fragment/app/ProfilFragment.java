package com.muratalarcin.mealexpress.ui.fragment.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentProfilBinding;

public class ProfilFragment extends Fragment {
    private FragmentProfilBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfilBinding.inflate(inflater, container, false);

        binding.buttonCikisYap.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Navigation.findNavController(view).navigate(R.id.girisYapFragment);
        });


        return binding.getRoot();
    }
}