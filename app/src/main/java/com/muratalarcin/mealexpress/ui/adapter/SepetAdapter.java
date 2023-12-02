package com.muratalarcin.mealexpress.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muratalarcin.mealexpress.data.entity.Sepet;
import com.muratalarcin.mealexpress.databinding.SepetRowBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.SepetViewModel;

import java.util.List;


public class SepetAdapter extends RecyclerView.Adapter<SepetAdapter.SepetRowHolder> {
    private List<Sepet> sepetListesi;
    private Context mContext;
    private SepetViewModel viewModel;

    public SepetAdapter(List<Sepet> sepetListesi, Context mContext, SepetViewModel viewModel) {
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
        Sepet sepet = sepetListesi.get(position);
        SepetRowBinding t = holder.tasarim;



    }

    @Override
    public int getItemCount() {
        return sepetListesi.size();
    }
}
