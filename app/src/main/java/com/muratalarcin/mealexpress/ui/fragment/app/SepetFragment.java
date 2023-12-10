package com.muratalarcin.mealexpress.ui.fragment.app;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.MainActivity;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.data.entity.Sepet;
import com.muratalarcin.mealexpress.data.entity.SepetString;
import com.muratalarcin.mealexpress.databinding.FragmentSepetBinding;
import com.muratalarcin.mealexpress.ui.adapter.SepetAdapter;
import com.muratalarcin.mealexpress.ui.fragment.BottomSheetFragment;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.SepetViewModel;

import java.util.concurrent.atomic.AtomicReference;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetFragment extends Fragment {
    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;
    private SepetString sepet;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSepetBinding.inflate(inflater, container, false);

        binding.returnIcon.setOnClickListener(view -> {
            requireActivity().onBackPressed();
            //Navigation.findNavController(view).navigate(R.id.sepettenAnasayfaya);
        });

        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        //bottomSheetFragment.setCancelable(false); // Kapatılamaz yap
        //bottomSheetFragment.show(getParentFragmentManager(), bottomSheetFragment.getTag());


        ((MainActivity) requireActivity()).setBottomNavigationVisibility(false);

        binding.rvSepet.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.sepetListesi.observe(getViewLifecycleOwner(), sepetListesi -> {
            SepetAdapter adapter = new SepetAdapter(sepetListesi,requireContext(),viewModel);
            binding.rvSepet.setAdapter(adapter);
        });

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("kullanici", MODE_PRIVATE);
        String kulanici = sharedPreferences.getString("kullanici", "");

        viewModel.sepetiYukle(kulanici);



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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SepetViewModel.class);
    }
}