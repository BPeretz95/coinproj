package com.example.bar.coinproj6;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;

/**
 * Created by Bar Peretz on 4/18/2018.
 */

public class WelcomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        ImageButton welcome = (ImageButton) findViewById(R.id.imageButtonwelcome);

        welcome.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(WelcomeActivity.this, CoinActivity.class);
                view.getContext().startActivity(intent1);
            }

});}}
