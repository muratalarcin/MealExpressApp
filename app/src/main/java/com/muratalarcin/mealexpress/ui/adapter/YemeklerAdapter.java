package com.muratalarcin.mealexpress.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.data.entity.Yemekler;
import com.muratalarcin.mealexpress.databinding.AnasayfaRowBinding;
import com.muratalarcin.mealexpress.ui.fragment.app.AnasayfaFragmentDirections;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.AnasayfaViewModel;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.DetayViewModel;

import java.util.List;

public class YemeklerAdapter extends RecyclerView.Adapter<YemeklerAdapter.AnasayfaRowHolder> {
    private List<Yemekler> yemeklerListesi;
    private Context mContext;
    private AnasayfaViewModel viewModel;

    public YemeklerAdapter(List<Yemekler> yemeklerListesi, Context mContext, AnasayfaViewModel viewModel) {
        this.yemeklerListesi = yemeklerListesi;
        this.mContext = mContext;
        this.viewModel = viewModel;
    }

    public class AnasayfaRowHolder extends RecyclerView.ViewHolder {
        private AnasayfaRowBinding tasarim;
        public AnasayfaRowHolder(AnasayfaRowBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @NonNull
    @Override
    public AnasayfaRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AnasayfaRowBinding binding =
                AnasayfaRowBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new AnasayfaRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnasayfaRowHolder holder, int position) {
        Yemekler yemek = yemeklerListesi.get(position);
        AnasayfaRowBinding t = holder.tasarim;

        String resimAdi = yemek.getYemek_resim_adi();
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimAdi;
        Glide.with(mContext).load(url).override(300, 300).into(t.imageViewYemek);

        t.textViewAd.setText(yemek.getYemek_adi());
        t.textViewFiyat.setText(String.valueOf(yemek.getYemek_fiyat() + "₺"));

        t.buttonSepet.setOnClickListener(view -> {
                String yemek_adi = yemek.getYemek_adi();
                String yemek_resim_adi = resimAdi;
                int yemek_fiyat = yemek.getYemek_fiyat();
                int yemek_siparis_adet = 1;
                String kullanici_adi = "murat";

                viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi);

        });

        t.cardViewYemek.setOnClickListener(view -> {
            AnasayfaFragmentDirections.DetayGecis gecis = AnasayfaFragmentDirections.detayGecis(yemek);
            Navigation.findNavController(view).navigate(gecis);
        });

    }

    @Override
    public int getItemCount() {
        return yemeklerListesi.size();
    }
}
