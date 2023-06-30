package com.example.app_doctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.app_doctruyen.adapter.adapterTruyen;
import com.example.app_doctruyen.adapter.adapterchuyenmuc;
import com.example.app_doctruyen.adapter.adapterthongtin;
import com.example.app_doctruyen.database.databasedoctruyen;
import com.example.app_doctruyen.model.TaiKhoan;
import com.example.app_doctruyen.model.Truyen;
import com.example.app_doctruyen.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView,listViewNew,listViewThongTin;
    DrawerLayout drawerLayout;
    String email;
    String tentaikhoan;
    String matkhau;

    ArrayList<Truyen> TruyenArraylist;
    adapterTruyen adapterTruyen;

    ArrayList<chuyenmuc> chuyenmucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;

    databasedoctruyen databasedoctruyen;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedoctruyen = new databasedoctruyen(this);

        // nhan du lieu o man dang nhap gui
        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phanq",0);
        int idd = intentpq.getIntExtra("idd",0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");
        matkhau =intentpq.getStringExtra("matkhau");



        AnhXa();
        ActionBar();
        ActionViewFlipper();

        // bat su kien click item
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ManNoiDung.class);

                String tent = TruyenArraylist.get(position).getTenTruyen();
                String noidungt = TruyenArraylist.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);

            }
        });
        // bat clickitem cho listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //dang bai
                if(position==0){
                    if(i==2){
                        Intent intent = new Intent(MainActivity.this,ManAdmin.class);
                        // gui id tai khoan qua man admin
                        intent.putExtra("id",idd);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_SHORT).show();

                        Log.e("Đăng Bài : ","Bạn không có quyền");

                    }
                }
                //neu vi tri an vao la thong tin thi chuyen qua man hinh thong tin
                else if(position == 1){
                    Intent intent = new Intent(MainActivity.this,ManThongTin.class);
                    startActivity(intent);



                }
                else if(position == 2){
                    finish();//dang xuat
                }




            }
        });

    }

    private void ActionBar() {
        // ham ho tro toolbar
        setSupportActionBar(toolbar);
        // set nut cho actiocbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // tao icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });



    }

    //phuong thuc cho chay quang cao voi ViewFlipper
    private void ActionViewFlipper(){
        //mang chua tam anh cho quang cao
        ArrayList<String> mangquangcao = new ArrayList<>();

        mangquangcao.add("data:image/jpeg;base64,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");
        mangquangcao.add("https://www.tiepthithegioi.vn/wp-content/uploads/2018/10/20-mau-ve-tranh-minh-hoa-truyen-co-tich-dep-nhat-8.jpg");
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1mDEEBnrB4vD_0f4AbDxce-Hd61pN7ZF6W2aySo50jQCvySAJ44QOlzzoGBodsb71fQw&usqp=CAU");
        mangquangcao.add("https://toigingiuvedep.vn/wp-content/uploads/2022/11/ve-tranh-the-gioi-co-tich.jpg");
        mangquangcao.add("https://anh.eva.vn/upload/4-2017/images/2017-12-20/maxresdefault-1513743970-217-width640height480.jpg");


        //thuc hien vong lap for gan anh vao imageview, roi tu image view len app
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            //su dung ham thu vien picasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            //phuong thuc chinh tam hinh vua quang cao
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //them anh tu image vào view flipper
            viewFlipper.addView(imageView);
        }
        //thiet lap tu dong chay cho viewflipper chay trong 4s
        viewFlipper.setFlipInterval(4000);
        //run auto
        viewFlipper.setAutoStart(true);
        //gọi animation cho vao va ra
//        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
//        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        ActionBar();

        //goi animation vao flipper
//        viewFlipper.setInAnimation(animation_slide_in);
//        viewFlipper.setInAnimation(animation_slide_out);


    }
    private void AnhXa(){
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew = findViewById(R.id.listviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigatationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArraylist = new ArrayList<>();

        Cursor cursor1 = databasedoctruyen.getData1();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArraylist);
            listViewNew.setAdapter(adapterTruyen);

        }
        cursor1.moveToFirst();
        cursor1.close();

        //thong tin
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan());

        adapterthongtin = new adapterthongtin(this,R.layout.nagivation_thongtin,taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterthongtin);
        //chuyen muc

        chuyenmucArrayList = new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng Bài", R.drawable.post));
        chuyenmucArrayList.add(new chuyenmuc("Thông Tin ", R.drawable.infor));
        chuyenmucArrayList.add(new chuyenmuc("Đăng Xuất ",R.drawable.out1));
        adapterchuyenmuc = new adapterchuyenmuc(this,R.layout.chuyenmuc,chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);





    }
    // nap mot menu tim kiem vao actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // neu click vao icon tim kiem se chuyen sang man hinh tim kiem
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,ManTimKiem.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}