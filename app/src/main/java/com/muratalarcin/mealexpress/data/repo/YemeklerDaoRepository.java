package com.muratalarcin.mealexpress.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.muratalarcin.mealexpress.data.entity.Yemekler;

import java.util.ArrayList;
import java.util.List;

public class YemeklerDaoRepository {
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData<>();
    public void yemekleriYukle() {
        ArrayList<Yemekler> liste = new ArrayList<>();
        Yemekler y1 = new Yemekler(1, "Ayran", "caney.jpg", 50, 1, 55, "murat");
        Yemekler y2 = new Yemekler(2, "sss", "sevenler.jpg", 50, 2, 5, "ss");
        Yemekler y3 = new Yemekler(2, "sss", "sevenler.jpg", 50, 2, 5, "ss");
        Yemekler y4 = new Yemekler(2, "sss", "sevenler.jpg", 50, 2, 5, "ss");

        liste.add(y1);
        liste.add(y2);
        liste.add(y3);
        liste.add(y4);
        yemeklerListesi.setValue(liste);
    }
}
