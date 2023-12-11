package com.muratalarcin.mealexpress.ui.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.muratalarcin.mealexpress.MainActivity;
import android.Manifest;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentProfilBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.ProfilViewModel;
import com.muratalarcin.mealexpress.ui.viewmodel.appviewmodel.SepetViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfilFragment extends Fragment {
    private FragmentProfilBinding binding;
    private ProfilViewModel viewModel;
    private static final int PERMISSION_REQUEST_CODE = 123;
    private static final int PICK_IMAGE_REQUEST_CODE = 456;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfilBinding.inflate(inflater, container, false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        viewModel.getAdSoyad().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String adSoyad) {
                binding.textViewAdSoyad.setText(adSoyad);
            }
        });

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("My Pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        binding.buttonGuncelle.setOnClickListener(view -> {
            String adres = Objects.requireNonNull(binding.editTextAdres.getEditText()).getText().toString();
            String adresBaslik = Objects.requireNonNull(binding.textInputLayoutAdresBaslik.getEditText()).getText().toString();
            editor.putString("adres", adres);
            editor.putString("adresBaslik", adresBaslik);
            editor.apply();
            Toast.makeText(getContext(), "Adres Bilgileri Güncellendi.", Toast.LENGTH_SHORT).show();
        });

        String kaydedilenAdres = sharedPreferences.getString("adres", "");
        String kaydedilenAdresBaslik = sharedPreferences.getString("adresBaslik", "");

        binding.baslik.setText(kaydedilenAdresBaslik);
        binding.bilgi.setText(kaydedilenAdres);


        binding.buttonCikisYap.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();

            // Uygulamayı tekrar başlatmak için yeni bir Intent oluşturun
            Intent intent = new Intent(requireContext(), MainActivity.class); // YourMainActivity, uygulamanın başlangıç aktivitesinin adıdır.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            // Intent'i başlatın
            startActivity(intent);
            requireActivity().finish(); // fragmentin bağlı olduğu aktiviteyi sonlandırabilirsiniz.
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }

        // ImageView'a tıklanınca galeriyi aç
        binding.imageViewAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });



        return binding.getRoot();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermissions() {
        // Gerekli izinlerin verilip verilmediğini kontrol et
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // İzin verilmemişse kullanıcıdan izin iste
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // İzin verildiyse galeriye erişim sağla
                openGallery();
            } else {
                // İzin reddedildiyse kullanıcıya bilgi ver
                Toast.makeText(requireContext(), "Galeri erişim izni reddedildi", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
    }
    // Geri tuşu dinleyicisi
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            // Geri tuşu işlemi
            // Bu bloğu boş bırakarak geri tuşunu devre dışı bırakabilirsiniz
        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            // Seçilen fotoğrafın URI'sini al ve ImageView'a ayarla
            Uri selectedImageUri = data.getData();
            binding.imageViewPP.setImageURI(selectedImageUri);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProfilViewModel.class);
    }
}