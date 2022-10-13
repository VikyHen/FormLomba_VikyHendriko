package com.if3a.formlombaprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ConfirmActivity extends AppCompatActivity
{
    DatePickerDialog datePickerDialog;
    TextView tvNama, tvJk, tvNoWa, tvAlamat, tvTanggal;
    Button btnTanggal, btnKonfirmasi;

    String nama, jk, noWa, alamat, choosenDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        tvNama = findViewById(R.id.tv_nama);
        tvJk = findViewById(R.id.tv_jk);
        tvNoWa = findViewById(R.id.tv_no_whatsapp);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvTanggal = findViewById(R.id.tv_tanggal);

        btnTanggal = findViewById(R.id.btn_tanggal);
        btnKonfirmasi = findViewById(R.id.btn_konfirmasi);

        //ambil intent yang dikirim dari MainActivity
        Intent terima = getIntent();
        nama = terima.getStringExtra("varNama");
        noWa = terima.getStringExtra("varNoWa");
        alamat = terima.getStringExtra("varAlamat");
        jk = terima.getStringExtra("varJenisKelamin");

        tvNama.setText(nama);
        tvJk.setText(jk);
        tvAlamat.setText(alamat);
        tvNoWa.setText(noWa);

        btnTanggal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar newCalender = Calendar.getInstance();

                datePickerDialog = new DatePickerDialog(ConfirmActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        String tahun = Integer.toString(year);
                        String bulan = Integer.toString(month + 1);
                        String hari = Integer.toString(dayOfMonth);
                        choosenDate = hari + " / " + bulan + " / " + tahun;
                        tvTanggal.setText(choosenDate);
                    }
                }, newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH), newCalender.get(Calendar.DAY_OF_MONTH));

                //Tammpilkan datePickerDialog
                datePickerDialog.show();
            }
        });

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ConfirmActivity.this);
                dialog.setTitle("Perhatian!");
                dialog.setMessage("Apakah data anda sudah benar?");

                //button positif
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ConfirmActivity.this, "Terima Kasih, Pendaftaraan Anda Berhasil", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                //button negatif
                dialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                //tampilkan dialog
                dialog.show();
            }
        });
    }
}