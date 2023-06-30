package com.example.app_doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_doctruyen.database.databasedoctruyen;
import com.example.app_doctruyen.model.TaiKhoan;

public class ManDangKy extends AppCompatActivity {
    EditText edtDKTaiKhoan,edtDKMatKhau,edtDKEmail;
    Button btnDKDangKy,btnDKDangNhap;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_ky);

        databasedoctruyen = new databasedoctruyen(this);

        AnhXa();
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();
                TaiKhoan taiKhoan1 = CreateTaiKhoan();
                if (taikhoan.equals("") || matkhau.equals("") || email.equals("")) {
                    Log.e("Thong bao : ", "Chua nhap du thong tin");
                }
                // neu day du thong tin afo thi add tk vao database
                else {
                    databasedoctruyen.AddTaiKhoan(taiKhoan1);
                    Toast.makeText(ManDangKy.this, "Dang Ky thanh cong", Toast.LENGTH_LONG).show();

                }
            }
        });
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;

        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);
        return tk;
    }
    private void AnhXa(){
        edtDKEmail = findViewById(R.id.dkemail);
        edtDKTaiKhoan = findViewById(R.id.dktaikhoan);
        edtDKMatKhau = findViewById(R.id.dkmatkhau);
        btnDKDangKy = findViewById(R.id.dkdangky);
        btnDKDangNhap = findViewById(R.id.dkdangnhap);
    }
}