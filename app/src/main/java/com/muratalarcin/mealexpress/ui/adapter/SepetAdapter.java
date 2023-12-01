package com.muratalarcin.mealexpress.ui.adapter;

import android.content.Context;
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
    private Context context;
    private SepetViewModel viewModel;

    public SepetAdapter(List<Sepet> sepetListesi, Context context, SepetViewModel viewModel) {
        this.sepetListesi = sepetListesi;
        this.context = context;
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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SepetRowHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return sepetListesi.size();
    }
}
