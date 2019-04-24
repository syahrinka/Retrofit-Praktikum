package com.example.syahrinka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syahrinka.generator.ServiceGenerator;
import com.example.syahrinka.model.Fox;
import com.example.syahrinka.service.FoxesService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private ImageView iconImage;
//    private TextView foxText;
    private FoxesService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconImage = findViewById(R.id.image_icon);
//        foxText = findViewById(R.id.text_joke);
        service = ServiceGenerator.createService(FoxesService.class);

        moreFox();

        Button moreButton = findViewById(R.id.button_more);

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreFox();

            }
        });
    }

    private void moreFox() {
        Call<Fox> foxResponse = service.getRandomFox();
        foxResponse.enqueue(new Callback<Fox>() {
            @Override
            public void onResponse(Call<Fox> call, Response<Fox> response) {

                Fox fox = response.body();
                Picasso.get().load(fox.getImage()).into(iconImage);
//                foxText.setText(fox.getValue());
            }

            @Override
            public void onFailure(Call<Fox> call, Throwable t) {

                Log.e(TAG, t.toString());
                String message = "Failed to get more joke, please check your connection.";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

