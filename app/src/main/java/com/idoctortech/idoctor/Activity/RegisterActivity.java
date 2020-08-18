package com.idoctortech.idoctor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.databinding.ActivityMainBinding;
import com.idoctortech.idoctor.databinding.ActivityRegisterBinding;

public class RegisterActivity extends ActivityBase {
    private ActivityRegisterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.registerBut.setOnClickListener(view -> {


        });
        binding.close.setOnClickListener(view -> {
            onBackPressed();
        });



    }
}