package com.example.app_doctruyen;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_doctruyen.adapter.adapterTruyen;
import com.example.app_doctruyen.database.databasedoctruyen;
import com.example.app_doctruyen.model.Truyen;

import java.util.ArrayList;


public class Manxoatruyen extends AppCompatActivity {

    ListView listView;
    Button buttonXoa;

    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adaptertruyen;
    databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manxoatruyen);
        listView = findViewById(R.id.listviewxoa);


        initList();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDelete(position);
                return false;
            }


        });




    }

    private void DialogDelete(int position){

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdelete);
        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtruyen = TruyenArrayList.get(position).getID();
                //Xóa trong SQL
                databasedoctruyen.Delete(idtruyen);
                //Cập nhật lại listview
                Intent intent = new Intent(Manxoatruyen.this,Manxoatruyen.class);
                finish();
                startActivity(intent);
                Toast.makeText(Manxoatruyen.this,"Xóa truyện thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void initList(){
        TruyenArrayList = new ArrayList<>();
        databasedoctruyen = new databasedoctruyen(this);

        Cursor cursor1 = databasedoctruyen.getData2();

        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);
            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adaptertruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);
            listView.setAdapter(adaptertruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}