package com.anila.telu.project3_vsga_jmp_a_anila;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "login";

    EditText editUserName, editPassword, editEmail, editNama, editAsalSekolah, editAlamat;
    TextView tvUserName, tvPassword, tvEmail, tvNama, tvAsal, tvAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("Halaman Depan");

        editUserName = findViewById(R.id.inputUserName);
        editPassword = findViewById(R.id.inputPassword);
        editEmail = findViewById(R.id.inputEmail);
        editNama = findViewById(R.id.inputNama);
        editAsalSekolah = findViewById(R.id.inputAsal);
        editAlamat = findViewById(R.id.inputAlamat);

        tvUserName = findViewById(R.id.tv_username);
        tvPassword = findViewById(R.id.tv_password);
        tvEmail = findViewById(R.id.tv_email);
        tvNama = findViewById(R.id.tv_nama);
        tvAsal = findViewById(R.id.tv_sekolah);
        tvAlamat = findViewById(R.id.tv_alamat);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setVisibility(View.GONE);

        editUserName.setEnabled(false);
        editPassword.setVisibility(View.GONE);
        tvPassword.setVisibility(View.GONE);
        editEmail.setEnabled(false);
        editNama.setEnabled(false);
        editAsalSekolah.setEnabled(false);
        editAlamat.setEnabled(false);

        bacaFileLogin();
    }

    void bacaFileLogin(){
        File file = new File(getFilesDir(), FILENAME);

        if(file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }

            String data = text.toString();
            String[] dataUser = data.split(";");

            bacaDataUser(dataUser[0]);
        }
    }

    void bacaDataUser(String namaFile){
        File file = new File(getFilesDir(), namaFile);

        if(file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }

            String data = text.toString();
            String[] dataUser = data.split(";");

            editUserName.setText(dataUser[0]);
            editEmail.setText(dataUser[2]);
            editNama.setText(dataUser[3]);
            editAsalSekolah.setText(dataUser[4]);
            editAlamat.setText(dataUser[5]);
        }else{
            Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                tampilkanDialogKonfirmasiLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()){
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout(){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin akan logout?")
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hapusFile();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}