package com.muratalarcin.mealexpress.ui.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.MainActivity;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentSepetBinding;
import com.muratalarcin.mealexpress.ui.fragment.BottomSheetFragment;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.SepetViewModel;

import java.util.concurrent.atomic.AtomicReference;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetFragment extends Fragment {
    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSepetBinding.inflate(inflater, container, false);

        binding.returnIcon.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });

        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getParentFragmentManager(), bottomSheetFragment.getTag());


        ((MainActivity) requireActivity()).setBottomNavigationVisibility(false);

        AtomicReference<Float> startY = new AtomicReference<>((float) 0);
        float MIN_SWIPE_DISTANCE = 100;

        binding.imageButton.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Başlangıç noktasını al
                    startY.set(event.getRawY());
                    return true;

                case MotionEvent.ACTION_UP:
                    // Bitiş noktasını al
                    float endY = event.getRawY();

                    // Yukarıya doğru sürüklendiyse işlemi gerçekleştir
                    if (startY.get() - endY > MIN_SWIPE_DISTANCE) {
                        Navigation.findNavController(v).navigate(R.id.bottomSheet_gecis);
                    }

                    return true;

                default:
                    return false;
            }
        });





        return binding.getRoot();
    }
}