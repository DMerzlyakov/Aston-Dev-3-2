package com.example.aston_dev_3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aston_dev_3_2.databinding.ActivityMainBinding;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button:
                if (!binding.editText.getText().toString().equals("")) {
                    new ImageDownloader().execute(binding.editText.getText().toString());
                } else {
                    toastShow(getString(R.string.error_empty_url));
                }
        }
    }

    private void toastShow(String textToast) {
        Toast toast = Toast.makeText(this, textToast, Toast.LENGTH_SHORT);
        toast.show();
    }


    /**
     * Класс для загрузки изображений, который наследуется от AsyncTask,
     * позволяет не останавливать основной поток приложения
     */
    private class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    bitmap = BitmapFactory.decodeStream(inputStream);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap data) {
            if (data != null) {
                binding.imageView.setImageBitmap(data);
            } else {
                toastShow(getString(R.string.error_download));
                binding.imageView.setImageResource(R.drawable.image_not_available);
            }
        }
    }
}