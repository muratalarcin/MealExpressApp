package com.muratalarcin.mealexpress.ui.fragment.app;

import static android.view.View.GONE;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.muratalarcin.mealexpress.MainActivity;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.data.entity.Yemekler;
import com.muratalarcin.mealexpress.databinding.FragmentAnasayfaBinding;
import com.muratalarcin.mealexpress.ui.adapter.YemeklerAdapter;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.AnasayfaViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);

        binding.sepetImageView.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.anasayfadanSepete);
        });

        binding.rwAnasayfa.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(), yemeklerListesi -> {
            YemeklerAdapter adapter = new YemeklerAdapter(yemeklerListesi, requireContext(), viewModel);
            binding.rwAnasayfa.setAdapter(adapter);
        } );

        ((MainActivity) requireActivity()).setBottomNavigationVisibility(true);

        binding.rwAnasayfa.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private static final int SCROLL_THRESHOLD = 50;
            private int scrolledDistance = 0;
            private boolean isSearchViewVisible = true;
            private final Handler scrollHandler = new Handler();
            private Runnable scrollRunnable;
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (scrollRunnable != null) {
                    scrollHandler.removeCallbacks(scrollRunnable);
                }
                scrollRunnable = () -> {
                    if (isSearchViewVisible && scrolledDistance > SCROLL_THRESHOLD) {
                        binding.searchView.setVisibility(View.GONE);
                        isSearchViewVisible = false;
                        scrolledDistance = 0;
                    }
                    else if (!isSearchViewVisible && scrolledDistance < -SCROLL_THRESHOLD) {
                        binding.searchView.setVisibility(View.VISIBLE);
                        isSearchViewVisible = true;
                        scrolledDistance = 0;
                    }
                };
                scrollHandler.postDelayed(scrollRunnable, 50);
                if ((isSearchViewVisible && dy > 0) || (!isSearchViewVisible && dy < 0)) {
                    scrolledDistance += dy;
                }
            }
        });



        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            public boolean onQueryTextChange(String s) {
                viewModel.yemeklerListesi.observe(getViewLifecycleOwner(), yemeklerListesi -> {
                    // Kullanıcının girdisine göre filtreleme işlemi
                    List<Yemekler> filtrelenmisListe = viewModel.filtreleYemekler(yemeklerListesi, s);
                    YemeklerAdapter adapter = new YemeklerAdapter(filtrelenmisListe, requireContext(), viewModel);
                    binding.rwAnasayfa.setAdapter(adapter);
                });
                return true;
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
    }

    /*@Override
    public void onResume() {
        super.onResume();
        viewModel.yemekleriYukle();
    }*/
}