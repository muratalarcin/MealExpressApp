package com.muratalarcin.mealexpress.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.data.entity.Sepet;
import com.muratalarcin.mealexpress.data.entity.SepetString;
import com.muratalarcin.mealexpress.data.entity.YemekAdetBilgisi;
import com.muratalarcin.mealexpress.databinding.SepetRowBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.SepetViewModel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SepetAdapter extends RecyclerView.Adapter<SepetAdapter.SepetRowHolder> {
    private List<SepetString> sepetListesi;
    private Context mContext;
    private SepetViewModel viewModel;
    Map<String, String > toplamSiparisAdetleri = new HashMap<>();
    List<YemekAdetBilgisi> yemekAdetBilgisiList = new ArrayList<>();

    public SepetAdapter(List<SepetString> sepetListesi, Context mContext, SepetViewModel viewModel) {
        this.sepetListesi = sepetListesi;
        this.mContext = mContext;
        this.viewModel = viewModel;

        // Sipariş adetlerini topla
        toplamSiparisAdetleri.clear();
        for (SepetString sepet : sepetListesi) {
            String yemekAdi = sepet.getYemek_adi();
            int siparisAdet = Integer.parseInt(sepet.getYemek_siparis_adet());

            if (toplamSiparisAdetleri.containsKey(yemekAdi)) {
                int mevcutAdet = Integer.parseInt(toplamSiparisAdetleri.get(yemekAdi));
                String toplamAdet = String.valueOf(mevcutAdet + siparisAdet);
                toplamSiparisAdetleri.put(yemekAdi, toplamAdet);
            } else {
                toplamSiparisAdetleri.put(yemekAdi, String.valueOf(siparisAdet));
            }
        }

        yemekAdetBilgisiList.clear();
        for (Map.Entry<String, String> entry : toplamSiparisAdetleri.entrySet()) {
            String yemekAdi = entry.getKey();
            String toplamAdet = entry.getValue();
            String yemekFiyat = sepetListesi.stream().filter(sepet -> sepet.getYemek_adi().equals(yemekAdi)).findFirst().get().getYemek_fiyat();
            String yemekResimAdi = sepetListesi.stream().filter(sepet -> sepet.getYemek_adi().equals(yemekAdi)).findFirst().get().getYemek_resim_adi();
            String yemek_id = sepetListesi.stream().filter(sepet -> sepet.getYemek_adi().equals(yemekAdi)).findFirst().get().getSepet_yemek_id();
            String kullaniciAdi = sepetListesi.stream().filter(sepet -> sepet.getYemek_adi().equals(yemekAdi)).findFirst().get().getKullanici_adi();
            YemekAdetBilgisi yemekAdetBilgisi = new YemekAdetBilgisi(yemekAdi, toplamAdet, yemekFiyat, yemekResimAdi, yemek_id, kullaniciAdi);
            yemekAdetBilgisiList.add(yemekAdetBilgisi);
        }
    }

    public class SepetRowHolder extends RecyclerView.ViewHolder {
        private SepetRowBinding tasarim;

        public SepetRowHolder(SepetRowBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @NonNull
    @Override
    public SepetRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SepetRowBinding binding = SepetRowBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new SepetRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SepetRowHolder holder, int position) {
        YemekAdetBilgisi yemekAdetBilgisi = yemekAdetBilgisiList.get(position);
        SepetRowBinding t = holder.tasarim;

        String resimAdi = yemekAdetBilgisi.getYemekResimAdi();
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimAdi;
        Glide.with(mContext).load(url).into(t.imageViewSepet);

        t.tvSepetYemekAd.setText(yemekAdetBilgisi.getYemekAdi());
        t.tvSepetYemekAdet.setText(String.valueOf(yemekAdetBilgisi.getToplamAdet()));
        t.tvSepetYemekFiyat.setText(yemekAdetBilgisi.getYemekFiyat());

        t.imageViewSil.setOnClickListener(view -> {
            int backgrounColor = ContextCompat.getColor(mContext, R.color.md_theme_dark_error);
            int textColor = ContextCompat.getColor(mContext, R.color.md_theme_light_onSecondaryContainer);
            int actionTextColor = ContextCompat.getColor(mContext, R.color.md_theme_light_error);


            Snackbar.make(view, "Ürünü silmek ister misiniz?", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(backgrounColor)
                    .setTextColor(textColor)
                    .setActionTextColor(actionTextColor)
                    .setAction("Evet", view1 -> {
                        viewModel.sepettenSil(yemekAdetBilgisi.getSepet_yemek_id(), yemekAdetBilgisi.getKullanici_adi());
                    })
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return yemekAdetBilgisiList.size();
    }
}
