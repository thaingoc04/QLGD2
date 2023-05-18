package com.example.qlgd2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class Adapter extends BaseAdapter implements Serializable {
    Activity activity;
    ArrayList<GiaoDich> data;
    ArrayList<GiaoDich> oldData;
    LayoutInflater inflater;

    public Adapter(Activity activity, ArrayList<GiaoDich> data){
        this.activity = activity;
        this.data = data;
        this.oldData = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.item, null);
            ConstraintLayout layout = v.findViewById(R.id.idLayout);
            TextView txtType = v.findViewById(R.id.txtType);
            if(data.get(i).getType() == 1){
                txtType.setText("Tien den tu: "+ data.get(i).getName());
                layout.setBackgroundColor(Color.RED);
            }
            if(data.get(i).getType() == 0){
                txtType.setText("Tien di tu: "+ data.get(i).getName());
                layout.setBackgroundColor(Color.BLUE);
            }

            TextView txtDate = v.findViewById(R.id.txtDate);
            txtDate.setText(data.get(i).getDate());
            TextView txtContent = v.findViewById(R.id.txtContent);
            txtContent.setText(data.get(i).getContent());
            TextView txtCost = v.findViewById(R.id.txtCost);
            txtCost.setText(String.valueOf(data.get(i).getCost()));
        }
        return v;
    }
}
