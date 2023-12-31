package com.muratalarcin.mealexpress.ui.fragment.login;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muratalarcin.mealexpress.MainActivity;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentKayitOlBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.loginviewmodel.KayitOlViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class KayitOlFragment extends Fragment {
    private FragmentKayitOlBinding binding;
    private KayitOlViewModel viewModel;
    private FirebaseAuth mAuth;
    private static final String TAG = "KayitOlFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentKayitOlBinding.inflate(inflater, container, false);

        binding.toggleGroup.check(R.id.buttonKayitOl);

        ((MainActivity) requireActivity()).setBottomNavigationVisibility(false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        mAuth = FirebaseAuth.getInstance();

        binding.imageViewGozKayit1.setOnClickListener(view -> {
            togglePasswordVisibility(Objects.requireNonNull(binding.textInputLayoutSifre.getEditText()));
        });

        binding.imageViewGozKayit2.setOnClickListener(view -> {
            togglePasswordVisibility(Objects.requireNonNull(binding.textInputLayoutSifreTekrarla.getEditText()));
        });

        binding.buttonUygulamayaKayitOl.setOnClickListener(view -> {
            String adSoyad = Objects.requireNonNull(binding.textInputLayoutAd.getEditText()).getText().toString();
            String email = Objects.requireNonNull(binding.textInputLayoutEmail.getEditText()).getText().toString();
            String sifre = Objects.requireNonNull(binding.textInputLayoutSifre.getEditText()).getText().toString();
            String sifreTekrar = Objects.requireNonNull(binding.textInputLayoutSifreTekrarla.getEditText()).getText().toString();


            viewModel = new ViewModelProvider(this).get(KayitOlViewModel.class);
            viewModel.updateAdSoyad(adSoyad);

/*
            if (viewModel != null) {
                viewModel.updateAdSoyad(adSoyad);
            } else {
                Log.e(TAG, "viewModel is null");
            }*/

            // Firebase Authentication ile kayıt olma
            mAuth.createUserWithEmailAndPassword(email, sifre)
                    .addOnCompleteListener(requireActivity(), task -> {
                        if (task.isSuccessful()) {
                            // Kayıt işlemi başarılı ise
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // Kayıt işlemi başarısız ise
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Kayıt olma başarısız.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        });



        binding.buttonGirisYap.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.kayittanGirise);
        });

        return binding.getRoot();
    }
    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            // Geri tuşu işlemi
            // Bu bloğu boş bırakarak geri tuşunu devre dışı bırakabilirsiniz
        }
    };

    private void updateUI(FirebaseUser currentUser) {
        // Kullanıcı arayüzünü güncelle
        if (currentUser != null) {
            // Kullanıcı kayıt olduysa yapılacak işlemler
            Navigation.findNavController(requireView()).navigate(R.id.kayittanAnasayfaya);
            Toast.makeText(getContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
        } else {
            // Kullanıcı kayıt olmadıysa yapılacak işlemler
        }
    }
    private void togglePasswordVisibility(EditText editText) {
        int inputType = editText.getInputType();
        int newInputType = (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) ?
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD :
                InputType.TYPE_TEXT_VARIATION_PASSWORD;

        editText.setInputType(newInputType);

        // Parolanın sonuna git
        editText.setSelection(editText.length());
    }
}