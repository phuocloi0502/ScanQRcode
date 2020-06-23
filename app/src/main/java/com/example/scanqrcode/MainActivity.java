package com.example.scanqrcode;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.OutputStream;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    EditText edtTexto;
    Button btnGerar,btnSaveQR;
    ImageView ivQRCode;
    OutputStream outputStream;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciliarComponentes();
        clickButton();

    }

    private void clickButton() {
        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerarQRCode();
            }
        });
        btnSaveQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveimg();
            }
        });
    }

    private void saveimg() {
        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null);
        Toast.makeText(getApplicationContext(), "Save successfuly", Toast.LENGTH_SHORT).show();
    }

    private void gerarQRCode() {
        String texto = edtTexto.getText().toString().trim();
        //
        if (texto.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    texto, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                ivQRCode.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show();
        }
        //
//        MultiFormatWriter multi = new MultiFormatWriter();
//        try {
//            BitMatrix bit = multi.encode(texto, BarcodeFormat.QR_CODE,200,200);
//            BarcodeEncoder bar = new BarcodeEncoder();
//            Bitmap bitMap = bar.createBitmap(bit);
//            ivQRCode.setImageBitmap(bitMap);
//
//        }catch (WriterException e){
//            e.printStackTrace();
//        }
    }

    private void iniciliarComponentes() {
        edtTexto = (EditText) findViewById(R.id.edtTexto);
        btnGerar = (Button) findViewById(R.id.btnGerar);
        ivQRCode = (ImageView) findViewById(R.id.ivQRCode);
        btnSaveQR=(Button) findViewById(R.id.btnsaveQR);

    }
}
