package com.example.app_doctruyen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_doctruyen.database.databasedoctruyen;

public class DoiMatKhauActivity extends AppCompatActivity {

    private EditText edtTaikhoandoi,edtMatkhaudoi,edtMatkhaumoi;
    private Button btnDoiMk,btnTroVe;
    public static databasedoctruyen db;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        Anhxa();
        context=this;
        db=new databasedoctruyen(this);
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DoiMatKhauActivity.this,ManDangNhap.class);
                startActivity(intent);
            }
        });
        btnDoiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoandoi=edtTaikhoandoi.getText().toString();
                String tenmatkhaudoi=edtMatkhaudoi.getText().toString();
                String tenmatkhaumoi=edtMatkhaumoi.getText().toString();
                Boolean check = db.checkusernamepassword(tentaikhoandoi,tenmatkhaudoi);

                if(check==true) {
                    Boolean update = db.datlai(tentaikhoandoi,tenmatkhaumoi);
                    if (update == true) {
                        Toast.makeText(DoiMatKhauActivity.this, " Đã đổi thành công", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(DoiMatKhauActivity.this, " sai thông tin", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void Anhxa() {
        edtMatkhaumoi=findViewById(R.id.edt_nhapmkmoi);
        edtTaikhoandoi=findViewById(R.id.edt_tkdoitk);
        edtMatkhaudoi=findViewById(R.id.edt_mkdoimk);
        btnDoiMk=findViewById(R.id.btn_doimk);
        btnTroVe=findViewById(R.id.dkdangnhap);
    }
    public void thongbaothoat(){
        androidx.appcompat.app.AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
        alerDialog.setTitle("Thông báo !!");
        alerDialog.setIcon(R.mipmap.ic_launcher);
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