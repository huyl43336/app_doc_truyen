package com.example.app_doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_doctruyen.database.databasedoctruyen;
import com.example.app_doctruyen.model.Truyen;

public class ManDangBai extends AppCompatActivity {

    EditText edtTenTruyen,edtNoiDung,edtAnh;
    Button btnDangBai;
    databasedoctruyen databasedoctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_bai);

        edtAnh = findViewById(R.id.dbimg);
        edtTenTruyen = findViewById(R.id.dbTentruyen);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);


        databasedoctruyen = new databasedoctruyen(this);

        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentruyen = edtTenTruyen.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Truyen truyen = CreatTruyen();

                if(tentruyen.equals("") || noidung.equals("")|| img.equals("")){
                    Toast.makeText(ManDangBai.this, "Yeu cau nhap day du", Toast.LENGTH_SHORT).show();
                    Log.e("ERR","nhap day du thong tin");

                }
                else {
                    databasedoctruyen.AddTruyen(truyen);

                    Intent intent = new Intent(ManDangBai.this,ManAdmin.class);
                    startActivity(intent);

                }


            }
        });

    }
    // phuong thuc tao truyen
    private Truyen CreatTruyen(){
        String tentruyen = edtTenTruyen.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();

        int id = intent.getIntExtra("Id",0);

        Truyen truyen = new Truyen(tentruyen,noidung,img,id);

        return truyen;

    }
}