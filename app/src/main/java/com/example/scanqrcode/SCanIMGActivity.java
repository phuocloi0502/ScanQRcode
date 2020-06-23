package com.example.scanqrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class SCanIMGActivity extends AppCompatActivity {
    Button btnIMG, btnScan;
    ImageView imageView;
    TextView txvrs;
    Bitmap imgqr, bitmap;
    BarcodeDetector barcodeDetector;
    int RQ_FD = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_can_i_m_g);
        btnIMG = (Button) findViewById(R.id.btnIMG);
        btnScan = (Button) findViewById(R.id.btnScan);
        imageView = (ImageView) findViewById(R.id.imageView);
        txvrs = (TextView) findViewById(R.id.txvrs);
        btnScan.setEnabled(false);
        btnIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, RQ_FD);
            }
        });
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                barcodeDetector = new BarcodeDetector.Builder(SCanIMGActivity.this).setBarcodeFormats(Barcode.QR_CODE).build();
                Frame frame = new Frame.Builder().setBitmap(imgqr).build();
                SparseArray<Barcode> barcodes = barcodeDetector.detect(frame);
                // Check if at least one barcode was detected
                if (barcodes.size() != 0) {
                    txvrs.setText(barcodes.valueAt(0).displayValue);
                }
                //
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RQ_FD && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                imgqr = BitmapFactory.decodeStream(inputStream);
                btnScan.setEnabled(true);
                imageView.setImageBitmap(imgqr);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}