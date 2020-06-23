package com.example.scanqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnCreateQR, btnScanQR,btnSanQRImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnCreateQR = (Button) findViewById(R.id.btnCrateQR);
        btnScanQR = (Button) findViewById(R.id.btnScanQR);
        btnSanQRImg=(Button) findViewById(R.id.btnScanQRimg);

        btnCreateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ReaderQRActivity.class);
                startActivity(intent);
            }
        });
        btnSanQRImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SCanIMGActivity.class);
                startActivity(intent);
            }
        });
    }
}