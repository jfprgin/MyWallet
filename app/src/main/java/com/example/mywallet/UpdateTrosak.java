package com.example.mywallet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.mywallet.ui.gallery.GalleryFragment;

public class UpdateTrosak extends AppCompatActivity {

    EditText et_vrijednost, et_biljeska;
    Spinner spinner;
    Button btn_azuriraj, btn_izbrisi;

    String id;
    String kategorija;
    int vrijednost;
    String biljeska;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trosak);

        et_vrijednost=findViewById(R.id.et_vrijednost3);
        et_biljeska=findViewById(R.id.et_biljeska3);
        spinner=findViewById(R.id.spinner4);
        btn_azuriraj=findViewById(R.id.btn_azuriraj3);
        btn_izbrisi=findViewById(R.id.btn_izbrisi3);
        getAndSetIntentData();
        btn_azuriraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper db = new DataBaseHelper(UpdateTrosak.this);
                kategorija = spinner.getSelectedItem().toString();
                vrijednost = Integer.parseInt(et_vrijednost.getText().toString());
                biljeska = et_biljeska.getText().toString();
                db.updateDataTrosak(id, kategorija, vrijednost, biljeska);
                Intent intent = new Intent(UpdateTrosak.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_izbrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("kategorija") && getIntent().hasExtra("vrijednost") && getIntent().hasExtra("biljeska")) {
            String vrijednost1;
            id=getIntent().getStringExtra("id");
            kategorija=getIntent().getStringExtra("kategorija");
            vrijednost1= getIntent().getStringExtra("vrijednost");
            vrijednost=Integer.parseInt(vrijednost1);
            biljeska=getIntent().getStringExtra("biljeska");

            //Treba postaviti i spinner
            et_vrijednost.setText(String.valueOf(vrijednost));
            et_biljeska.setText(biljeska);
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Jeste li sigurni?");
        builder.setNegativeButton("Da", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBaseHelper db = new DataBaseHelper(UpdateTrosak.this);
                db.deleteTrosak(id);
                Intent intent = new Intent(UpdateTrosak.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setPositiveButton("Ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}