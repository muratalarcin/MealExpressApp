package com.muratalarcin.mealexpress.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
    Map<String, Integer> toplamSiparisAdetleri = new HashMap<>();
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
                int mevcutAdet = toplamSiparisAdetleri.get(yemekAdi);
                toplamSiparisAdetleri.put(yemekAdi, mevcutAdet + siparisAdet);
            } else {
                toplamSiparisAdetleri.put(yemekAdi, siparisAdet);
            }
        }

        // Yemek adet bilgilerini oluştur
        yemekAdetBilgisiList.clear();
        for (Map.Entry<String, Integer> entry : toplamSiparisAdetleri.entrySet()) {
            String yemekAdi = entry.getKey();
            int toplamAdet = entry.getValue();
            YemekAdetBilgisi yemekAdetBilgisi = new YemekAdetBilgisi(yemekAdi, toplamAdet, yemekAdi);
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
        t.tvSepetYemekFiyat.setText(sepetListesi.get(position).getYemek_fiyat());
    }

    @Override
    public int getItemCount() {
        return yemekAdetBilgisiList.size();
    }
}
