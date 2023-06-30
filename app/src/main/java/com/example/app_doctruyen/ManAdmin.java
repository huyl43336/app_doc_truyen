package com.example.app_doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_doctruyen.adapter.adapterTruyen;
import com.example.app_doctruyen.database.databasedoctruyen;
import com.example.app_doctruyen.model.Truyen;

import java.util.ArrayList;

public class ManAdmin extends AppCompatActivity {
    ListView listView;
    Button buttonThem;
    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;
    databasedoctruyen databasedoctruyen;
    //
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_man_admin);
//
//        listView = findViewById(R.id.listviewAdmin);
//        buttonThem = findViewById(R.id.buttonThemtruyen);
//
//        initList();
//
//        buttonThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // lay id tai khoan da biet tai khoan admin nao da vao chinh sua
//                Intent intent1 = getIntent();
//                int id = intent1.getIntExtra("Id",0);
//                //tiep tuc gui id qua man hinh them truyeb
//                Intent intent = new Intent(ManAdmin.this,ManDangBai.class);
//                intent.putExtra("Id",id);
//                startActivity(intent);
//
//
//            }
//        });
    //click item long se xoa item
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                DialogDelete(position);
////                int idtruyen = TruyenArrayList.get(position).getID();
////                // xoa du lieu
////                databasedoctruyen.Delete(idtruyen);
////                // cap nhat lai activiti
////                Intent intent = new Intent(ManAdmin.this,ManAdmin.class);
////                finish();
////                startActivity(intent);
////
////                Toast.makeText(ManAdmin.this, "Xoa truyen thanh cong", Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });
//    }
//    // phuong thuc dialog hien thi cua so xoa
//    private void DialogDelete(int position){
//        Dialog dialog = new Dialog(this);
//        // nap layout vao dialog
//
//        dialog.setContentView(R.layout.dialogdelete);
//        //tat click ra ngoai la dong ,chi click ra no moi dong
//        dialog.setCanceledOnTouchOutside(false);
//        // anh xa
//        Button btnYes = dialog.findViewById(R.id.buttonYes);
//        Button btnNo = dialog.findViewById(R.id.buttonNo);
//
//        btnYes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int idtruyen = TruyenArrayList.get(position).getID();
//                // xoa du lieu
//                databasedoctruyen.Delete(idtruyen);
//                // cap nhat lai activiti
//                Intent intent = new Intent(ManAdmin.this,ManAdmin.class);
//
//                startActivity(intent);
//                finish();
//                Toast.makeText(ManAdmin.this, "Xoa truyen thanh cong", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//        btnNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //thuc hien doc dialog
//                dialog.cancel();
//
//
//            }
//        });
//        // run dialog
//        dialog.show();
//
//
//    }
//    // gan du lieu cho list view
//    private void initList() {
//        TruyenArrayList = new ArrayList<>();
//        databasedoctruyen = new databasedoctruyen(this);
//
//        Cursor cursor1 = databasedoctruyen.getData2();
//        while (cursor1.moveToNext()){
//            int id = cursor1.getInt(0);
//            String tentruyen = cursor1.getString(1);
//            String noidung = cursor1.getString(2);
//            String anh = cursor1.getString(3);
//            int id_tk = cursor1.getInt(4);
//
//            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
//
//            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);
//
//            listView.setAdapter(adapterTruyen);
//
//        }
//        cursor1.moveToFirst();
//        cursor1.close();
//    }
    Button btnQLtruyen, btnQLnguoidung, btnTroveTC,btndangnhap,btnxoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_admin);
        Anhxa();

//        btnQLtruyen.setOnClickListener(v -> {
//            Intent intent1 = getIntent();
//            int id = intent1.getIntExtra("Id", 0);
//            //tiep tuc gui id qua man hinh them truyeb
//            Intent intent = new Intent(ManAdmin.this, ManDangBai.class);
//            intent.putExtra("Id", id);
//            startActivity(intent);
//
//        });
        btnQLtruyen.setOnClickListener(v->{
            Intent intent1= getIntent();
            int id=intent1.getIntExtra("Id",0);

            Intent intent= new Intent(ManAdmin.this,ManDangBai.class);
            intent.putExtra("Id",id);
            startActivity(intent);
        });
        btnQLnguoidung.setOnClickListener(v-> {
            Intent intent=new Intent(ManAdmin.this,QuanlyTaikhoanActivity.class);
            startActivity(intent);
        });
        btnTroveTC.setOnClickListener(v -> {
            Intent intent=new Intent(ManAdmin.this,MainActivity.class);
            startActivity(intent);

        });
        btndangnhap.setOnClickListener(v ->{
            Intent intent= new Intent(ManAdmin.this,ManDangNhap.class);
            startActivity(intent);
        });
        btnxoa.setOnClickListener(view -> {
            Intent intent = new Intent(ManAdmin.this,Manxoatruyen.class);
            startActivity(intent);
        });

    }

    private void Anhxa() {
        btnQLnguoidung=findViewById(R.id.btn_quantringuoidung);
        btnQLtruyen=findViewById(R.id.btn_themtruyen);
        btnTroveTC=findViewById(R.id.btn_trovetrangchu);
        btndangnhap=findViewById(R.id.dkdangnhap);
        btnxoa=findViewById(R.id.btn_xoatruyen);
    }
}