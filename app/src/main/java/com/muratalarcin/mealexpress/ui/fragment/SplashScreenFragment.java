package com.muratalarcin.mealexpress.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.muratalarcin.mealexpress.MainActivity;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentSplashScreenBinding;


public class SplashScreenFragment extends Fragment {

    private static final int SPLASH_DURATION = 4000; // saniye
    private FragmentSplashScreenBinding binding; // ViewBinding nesnesi

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false);

        ((MainActivity) requireActivity()).setBottomNavigationVisibility(false);

        View rootView = binding.getRoot();

        ImageView imageView = rootView.findViewById(R.id.imageViewAnim);

        Animation animation = AnimationUtils.loadAnimation(requireContext(), R.anim.blings);
        imageView.startAnimation(animation);

        new Handler(Looper.getMainLooper()).postDelayed(this::startNextFragment, SPLASH_DURATION);

        return binding.getRoot();
    }
    private void startNextFragment() {
        Navigation.findNavController(requireView()).navigate(R.id.acilis);
    }
}

