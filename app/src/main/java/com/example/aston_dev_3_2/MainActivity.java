package com.example.aston_dev_3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.aston_dev_3_2.databinding.ActivityMainBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

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
        Picasso.get()
                .load(url)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .error(R.drawable.image_not_available)
                .fit()
                .into(binding.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        toastShow("Ошибка: " + e.getMessage());
                    }
                });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button:
                if (!binding.editText.getText().toString().equals("")) {
                    loadImageFromURL(binding.editText.getText().toString());
                } else {
                    toastShow(getString(R.string.error_empty_url));
                }
        }
    }

    private void toastShow(String textToast) {
        Toast toast = Toast.makeText(
                this, textToast,
                Toast.LENGTH_SHORT
        );
        toast.show();
    }
}