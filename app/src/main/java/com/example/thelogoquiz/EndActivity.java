package com.example.thelogoquiz;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class EndActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        PDFView pdfView = findViewById(R.id.pdfView);
        super.onCreate(savedInstanceState);


        pdfView.fromAsset("Logos.pdf");

        pdfView.fromAsset("2020FRCGameSeasonManual.pdf");

        pdfView.fromAsset("Excel Project - Truth Tables.pdf");

        pdfView.fromAsset("All The Emoji Meanings You Should Know.pdf");
    }
}