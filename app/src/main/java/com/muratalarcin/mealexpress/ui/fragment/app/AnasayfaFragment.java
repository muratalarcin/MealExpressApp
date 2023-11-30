package com.muratalarcin.mealexpress.ui.fragment.app;

import static android.view.View.GONE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentAnasayfaBinding;
import com.muratalarcin.mealexpress.ui.adapter.YemeklerAdapter;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.AnasayfaViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);

        binding.rwAnasayfa.setLayoutManager(//yan yana 2 tane row olma muhabbeti
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(), yemeklerListesi -> {
            YemeklerAdapter adapter = new YemeklerAdapter(yemeklerListesi, requireContext(), viewModel);
            binding.rwAnasayfa.setAdapter(adapter);
        } );

        binding.rwAnasayfa.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private static final int SCROLL_THRESHOLD = 50;
            private int scrolledDistance = 0;
            private boolean isSearchViewVisible = true;
            private final Handler scrollHandler = new Handler();
            private Runnable scrollRunnable;
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // Eğer hala bir işlem varsa, bekleyin
                if (scrollRunnable != null) {
                    scrollHandler.removeCallbacks(scrollRunnable);
                }

                // Scroll işlemi gecikmeli bir şekilde kontrol ediliyor
                scrollRunnable = () -> {
                    // Aşağı kaydırılıyorsa ve arama çubuğu görünüyorsa gizle
                    if (isSearchViewVisible && scrolledDistance > SCROLL_THRESHOLD) {
                        binding.searchView.setVisibility(View.GONE);
                        isSearchViewVisible = false;
                        scrolledDistance = 0;
                    }
                    // Yukarı çekiliyorsa ve arama çubuğu gizliyse göster
                    else if (!isSearchViewVisible && scrolledDistance < -SCROLL_THRESHOLD) {
                        binding.searchView.setVisibility(View.VISIBLE);
                        isSearchViewVisible = true;
                        scrolledDistance = 0;
                    }
                };

                // Belirli bir süre sonra işlemi gerçekleştir
                scrollHandler.postDelayed(scrollRunnable, 50);

                // scrolledDistance'ı güncelle
                if ((isSearchViewVisible && dy > 0) || (!isSearchViewVisible && dy < 0)) {
                    scrolledDistance += dy;
                }
            }
        });




        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
        // AnasayfaFragment'ta onCreateView içinde
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.yemekleriYukle();
    }
}