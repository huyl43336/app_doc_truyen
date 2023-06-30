package com.example.app_doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ManThongTin extends AppCompatActivity {

    TextView txtThongtinapp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_thong_tin);
        txtThongtinapp = findViewById(R.id.textviewthongtin);
        String thongtin = "Ứng dụng được phát triển bởi nhóm 21_62TH2_Andorid_2023 \n"+"Chi tiết liên hệ : \n"
                +"SĐT:0989977224 \n"+"Gmail:hul43336@gmail.com";
        txtThongtinapp.setText(thongtin);
    }
}