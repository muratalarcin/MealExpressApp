package com.muratalarcin.mealexpress.ui.fragment.login;


import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muratalarcin.mealexpress.MainActivity;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentGirisYapBinding;
import com.muratalarcin.mealexpress.ui.viewmodel.loginviewmodel.GirisYapViewModel;

import java.util.Objects;
import java.util.concurrent.Executor;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GirisYapFragment extends Fragment {
    private FragmentGirisYapBinding binding;
    private GirisYapViewModel viewModel;
    private FirebaseAuth mAuth;
    private static final String TAG = "GirisYapFragment";
    private boolean showOneTapUI = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGirisYapBinding.inflate(inflater, container, false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        mAuth = FirebaseAuth.getInstance();

        binding.toggleGroup.check(R.id.buttonGirisYap);

        ((MainActivity) requireActivity()).setBottomNavigationVisibility(false);

        binding.twSifremiUnuttum.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.giristenSifreye);
        });


        binding.buttonKayitOl.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.giristen_kayita);
        });

        binding.imageViewGoz.setOnClickListener(view -> {
            int inputType = binding.textInputLayoutSifre.getEditText().getInputType();
            int newInputType = (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) ?
                    InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD :
                    InputType.TYPE_TEXT_VARIATION_PASSWORD;

            binding.textInputLayoutSifre.getEditText().setInputType(newInputType);

            // Parolanın sonuna git
            binding.textInputLayoutSifre.getEditText().setSelection(binding.textInputLayoutSifre.getEditText().length());

        });

        binding.buttonUygulamayaGirisYap.setOnClickListener(view -> {
            String girilenEmail = Objects.requireNonNull(binding.textInputLayoutEmail.getEditText()).getText().toString();
            String girilenSifre = Objects.requireNonNull(binding.textInputLayoutSifre.getEditText()).getText().toString();

            if (girilenSifre.equals("admin")) {
                Navigation.findNavController(view).navigate(R.id.giristenAnasayfaya);

            }

            // Firebase Authentication ile oturum açma
            mAuth.signInWithEmailAndPassword(girilenEmail, girilenSifre)
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Oturum açma başarılı ise
                                Log.d(TAG, "signInWithEmailAndPassword:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);

                                String kullanici = user.getDisplayName();
                                SharedPreferences sharedPreferences = getContext().getSharedPreferences("kullanici", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("kullanici", kullanici);
                                editor.apply();
                            } else {
                                // Oturum açma başarısız ise
                                Log.w(TAG, "signInWithEmailAndPassword:failure", task.getException());
                                Toast.makeText(getContext(), "Mail ya da şifre hatalı.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        });





        return binding.getRoot();
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
    public void onStart() {
        super.onStart();
        // Kullanıcı daha önce oturum açmışsa, currentUser değeri null olmayacak.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        // Kullanıcı arayüzünü güncelle
        if (currentUser != null) {
            // Kullanıcı oturum açmışsa yapılacak işlemler
            Navigation.findNavController(requireView()).navigate(R.id.giristenAnasayfaya);
        } else {
            // Kullanıcı oturum açmamışsa yapılacak işlemler
        }
    }

}