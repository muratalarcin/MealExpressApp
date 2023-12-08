package com.muratalarcin.mealexpress.ui.fragment.app;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentFavoriBinding;

public class FavoriFragment extends Fragment {
    private FragmentFavoriBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriBinding.inflate(inflater, container, false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

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