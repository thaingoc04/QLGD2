package com.example.qlgd2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    TextView txtDu;
    SearchView searchView;
    ListView lstView;
    Button btnAdd;
    ArrayList<GiaoDich> arrayList;
    Adapter adapter;
    int Id;
    SQLite mysql = new SQLite(this, "GiaoDich4", null, 4);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDu = findViewById(R.id.txtDu);
        searchView = findViewById(R.id.edtSearch);
        lstView = findViewById(R.id.lstContact);
        btnAdd = findViewById(R.id.btnAdd);

//        mysql.addContact(new GiaoDich("Tiền thưởng", "30/01/2023", 1, "Đào Minh Anh", 500000));
//        mysql.addContact(new GiaoDich("Mua Sách", "26/01/2023",0,"NXB Nhã Nam", 200000));
//        mysql.addContact(new GiaoDich("Ăn tối", "24/01/2023", 0, "Cơm Quyên", 25000));




        arrayList = mysql.getAllContact();
        Collections.sort(arrayList, new Comparator<GiaoDich>() {
            @Override
            public int compare(GiaoDich o1, GiaoDich o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        adapter = new Adapter(this, arrayList);
        lstView.setAdapter(adapter);
        int soDu = 0;
        for(GiaoDich thuChi: arrayList){
            if(thuChi.getType()==1)
                soDu+= thuChi.getCost();
            else
                soDu-= thuChi.getCost();
        }
        txtDu.setText("Số Dư: "+String.valueOf(soDu));

        lstView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                Id = i;
                registerForContextMenu(view);
                return false;
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuDelete:{
                break;
            }

            case R.id.mnuEdit:{
                Intent intent = new Intent(MainActivity.this, Edit.class);
                Bundle bundle = new Bundle();

                GiaoDich x = new GiaoDich(arrayList.get(Id).getContent(), arrayList.get(Id).getDate(),
                        arrayList.get(Id).getType(), arrayList.get(Id).getName(), arrayList.get(Id).getCost());
                bundle.putSerializable("editItem", x);
                intent.putExtras(bundle);
                startActivityForResult(intent, 200);
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if(requestCode == 200 && resultCode == 300){
            Bundle bundle = data.getExtras();
            GiaoDich item = (GiaoDich) bundle.getSerializable("editResult");
            int idToUpdate = arrayList.get(Id).getId();
            item.setId(idToUpdate);
            mysql.upgradeContact(idToUpdate, item);
            arrayList = mysql.getAllContact();
            adapter = new Adapter(MainActivity.this, arrayList);
            lstView.setAdapter(adapter);
        }
    }
}