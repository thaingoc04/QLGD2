package com.example.qlgd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Edit extends AppCompatActivity {
    private EditText edtDate;
    private EditText edtName;
    private EditText edtContent;
    private EditText edtCost;
    private Button btnCancel;
    private Button btnThem;
    private RadioGroup rdoGroup;
    public int id;
    public int chooseRadio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtDate = findViewById(R.id.edtDate);
        edtName = findViewById(R.id.edtName);
        edtContent = findViewById(R.id.edtContent);
        edtCost = findViewById(R.id.edtCost);
        rdoGroup = findViewById(R.id.radioGroup);
        btnThem = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            GiaoDich x = new GiaoDich();
            x = (GiaoDich) bundle.getSerializable("editItem");
            edtDate.setText(x.getDate());
            int check = x.getType();
            if(check == 1){
                int radioButtonId = R.id.rdnCome; // ID of the radio button you want to check
                rdoGroup.check(radioButtonId);
                
            }
            else {
                int radioButtonId = R.id.rdnGo; // ID of the radio button you want to check
                rdoGroup.check(radioButtonId);

            }
            rdoGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.rdnGo)
                        chooseRadio = 1;
                    else
                        chooseRadio = 0;
                }
            });
            edtName.setText(x.getName());
            edtContent.setText(x.getContent());
            edtCost.setText(String.valueOf(x.getCost()));

        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                GiaoDich x = new GiaoDich(edtContent.getText().toString(), edtDate.getText().toString(), chooseRadio,
                        edtName.getText().toString(), Integer.parseInt(edtCost.getText().toString()));
                bundle.putSerializable("editResult", x);
                intent.putExtras(bundle);
                setResult(300, intent);
                finish();
            }
        });
    }
}