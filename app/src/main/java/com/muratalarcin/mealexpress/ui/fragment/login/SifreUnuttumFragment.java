package com.muratalarcin.mealexpress.ui.fragment.login;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.muratalarcin.mealexpress.R;
import com.muratalarcin.mealexpress.databinding.FragmentSifreUnuttumBinding;

import java.util.Objects;

public class SifreUnuttumFragment extends Fragment {
    private FragmentSifreUnuttumBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSifreUnuttumBinding.inflate(inflater, container, false);

        binding.buttonKayitOl.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.sifredenKayita);
        });

        binding.buttonGirisYap.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.sifredenGirise);
        });

        binding.buttonSifreYenile.setOnClickListener(view -> {
            String userEmail = Objects.requireNonNull(binding.textInputLayoutEmail.getEditText()).getText().toString();
            FirebaseAuth auth = FirebaseAuth.getInstance();

            auth.sendPasswordResetEmail(userEmail)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Şifre sıfırlama e-postası gönderildi. Lütfen e-posta adresinizi kontrol edin.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "Şifre sıfırlama işlemi başarısız. Lütfen tekrar deneyin.", Toast.LENGTH_LONG).show();

                            // Hata ayrıntılarını logcat'e yazdırma
                            if (task.getException() != null) {
                                task.getException().printStackTrace();
                            }
                        }
                    });
        });



        return binding.getRoot();
    }
}