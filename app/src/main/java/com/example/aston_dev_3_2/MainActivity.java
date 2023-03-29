package com.example.aston_dev_3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.aston_dev_3_2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button.setOnClickListener(this);
    }


    private void loadImageFromURL(String url) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button:
                loadImageFromURL(binding.editText.getText().toString());
        }
    }
}