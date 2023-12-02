package com.muratalarcin.mealexpress.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.muratalarcin.mealexpress.data.entity.Sepet;
import com.muratalarcin.mealexpress.data.entity.SepetString;
import com.muratalarcin.mealexpress.databinding.SepetRowBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.SepetViewModel;

import java.util.List;


public class SepetAdapter extends RecyclerView.Adapter<SepetAdapter.SepetRowHolder> {
    private List<SepetString> sepetListesi;
    private Context mContext;
    private SepetViewModel viewModel;

    public SepetAdapter(List<SepetString> sepetListesi, Context mContext, SepetViewModel viewModel) {
        this.sepetListesi = sepetListesi;
        this.mContext = mContext;
        this.viewModel = viewModel;
    }

    public class SepetRowHolder extends RecyclerView.ViewHolder{
        private SepetRowBinding tasarim;

        public SepetRowHolder(SepetRowBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @NonNull
    @Override
    public SepetRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SepetRowBinding binding =
                SepetRowBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new SepetRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SepetRowHolder holder, int position) {
        SepetString sepet = sepetListesi.get(position);
        SepetRowBinding t = holder.tasarim;

        String resimAdi = sepet.getYemek_resim_adi();
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimAdi;
        Glide.with(mContext).load(url)/*.override(300, 300)*/.into(t.imageViewSepet);

        t.tvSepetYemekAd.setText(sepet.getYemek_adi());
        t.tvSepetYemekAdet.setText(sepet.getYemek_siparis_adet());
        t.tvSepetYemekFiyat.setText(sepet.getYemek_fiyat());




    }

    @Override
    public int getItemCount() {
        return sepetListesi.size();
    }
}
