package com.example.app_doctruyen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_doctruyen.database.databasedoctruyen;

public class ManDangNhap extends AppCompatActivity {
//tao bien cho man dang nhap
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap,btnDangKy;
    ImageButton imgBtnPower;
//tao doi tuong cho databasedoctruyen
  databasedoctruyen databasedoctruyen;
  TextView textViewdoimk;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_nhap);
        AnhXa();
        textViewdoimk=findViewById(R.id.tv_doimk);
        imgBtnPower=findViewById(R.id.imgBtnPower);

        // doi tuong database doc truyen
        databasedoctruyen = new databasedoctruyen(this);
        // tao su kien click chuyen sang man hinh dang ky voi intent

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManDangNhap.this,ManDangKy.class);
                startActivity(intent);
            }
        });
        imgBtnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongbaothoat();
            }
        });
        textViewdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ManDangNhap.this,DoiMatKhauActivity.class);
                startActivity(intent);
            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                    String tentaikhoan = edtTaiKhoan.getText().toString();
//                    String matkhau = edtMatKhau.getText().toString();


//                    if (tentaikhoan.equals("") || matkhau.equals("")) {
//                        Toast.makeText(ManDangNhap.this, "Bạn nhập thiếu thông tin vui lòng nhập lại!!! ", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Boolean checkuserpass = databasedoctruyen.checkusernamepassword(tentaikhoan, matkhau);
//                        if (checkuserpass == true) {
//                            if (tentaikhoan.equals("admin")) {
//                                Toast.makeText(ManDangNhap.this, "Admin đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(ManDangNhap.this, ManAdmin.class);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(ManDangNhap.this, "đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(ManDangNhap.this, MainActivity.class);
//                                startActivity(intent);
//
//                            }
//                        } else {
//
//                            Toast.makeText(ManDangNhap.this, "Tài khoản hoặc mật khẩu không chính xác, Xin vui lòng thử lại ", Toast.LENGTH_SHORT).show();
//                        }
//                    }

//                Cursor cursor = databasedoctruyen.getData();
//                //thuc hien vong lap de lay du lieu tu cursor voi movetonext() di chuyen tiep
//                while (cursor.moveToNext()){
//                    //du lieu tai khoan o o 1,mat khau o 2,0 id tai khoan , 3 emall,4 phanquyen
//                    String datatentaikhoan = cursor.getString(1);
//                    String datamatkhau = cursor.getString(2);
//
//                    if(datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)){
//                        int phanquyen = cursor.getInt(4);
//                        int idd = cursor.getInt(0);
//
//                        String email = cursor.getString(3);
//                        String tentk = cursor.getString(1);
//                        //chuyen qua mainactivity
//                        Intent intent = new Intent(ManDangNhap.this,MainActivity.class);
//                        //gui du lieu qua activity la mainactivity
//                        intent.putExtra("phanq",phanquyen);
//                        intent.putExtra("idd",idd);
//                        intent.putExtra("email",email);
//                        intent.putExtra("tentaikhoan",tentk);
//
//                        startActivity(intent);
//
//
//                    }
//
//                }
//                //thuc hien tra cursor ve dau
//                cursor.moveToFirst();
//                //dong khi k dung
//                cursor.close();
//            }



                    try {
                        String user = edtTaiKhoan.getText().toString();
                        String pass = edtMatKhau.getText().toString();
                        if (user.equals("") || pass.equals("")) {
                            Toast.makeText(ManDangNhap.this, "Bạn nhập thiếu thông tin vui lòng nhập lại!!! ", Toast.LENGTH_SHORT).show();
                        } else {
                            Boolean checkuserpass = databasedoctruyen.checkusernamepassword(user, pass);
                            if (checkuserpass == true) {
                                if(user.equals("admin")) {
                                    Toast.makeText(ManDangNhap.this, "Admin đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ManDangNhap.this, ManAdmin.class);
                                    startActivity(intent);
                                }
                                else  {
                                    Toast.makeText(ManDangNhap.this, "đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ManDangNhap.this, MainActivity.class);
                                    startActivity(intent);

                                }
                            } else {

                                Toast.makeText(ManDangNhap.this, "Tài khoản hoặc mật khẩu không chính xác, Xin vui lòng thử lại ", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } catch (Exception e) {e.printStackTrace();}
                }

    });
    }



    private void AnhXa(){
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
    }
    public void thongbaothoat(){
        androidx.appcompat.app.AlertDialog.Builder
                alerDialog = new AlertDialog.Builder(this);
        alerDialog.setTitle("Thông báo !!");
        alerDialog.setMessage("Bạn có muốn thoát ?");
        alerDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        alerDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerDialog.show();

    }
    }
