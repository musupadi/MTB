package com.destinyapp.mtb.Activity.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.destinyapp.mtb.R;

public class DetailProductActivity extends AppCompatActivity {
    TextView deskripsi,nama,harga,order;
    ImageView gambar;
    EditText Nama,Email,KodeNomor,NoTelepon,Alamat,Kota,Provinsi,KodePos,Order,Jumlah;
    Button proses;
    String mailMTB="info@mutiaratigaberlian.co.id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        nama=(TextView)findViewById(R.id.tvNamaProduk);
        harga=(TextView)findViewById(R.id.tvHarga);
        deskripsi=(TextView)findViewById(R.id.tvDeskripsi);
        order=(TextView)findViewById(R.id.tvOrder);
        gambar=(ImageView)findViewById(R.id.ivGambar);
        Nama=(EditText)findViewById(R.id.etNama);
        Email=(EditText)findViewById(R.id.etEmail);
        KodeNomor=(EditText)findViewById(R.id.etKodeNomor);
        NoTelepon=(EditText)findViewById(R.id.etTelepon);
        Alamat=(EditText)findViewById(R.id.etAlamat);
        Kota=(EditText)findViewById(R.id.etKota);;
        Provinsi=(EditText)findViewById(R.id.etProvinsi);
        KodePos=(EditText)findViewById(R.id.etKodePos);
        Order=(EditText)findViewById(R.id.etOrder);
        Jumlah=(EditText)findViewById(R.id.etJumlah);
        proses=(Button)findViewById(R.id.btnProses);
        Intent data = getIntent();
        final String GAMBAR = data.getStringExtra("GAMBAR");
        final String NAMA = data.getStringExtra("NAMA");
        final String HARGA = data.getStringExtra("HARGA");
        final String DESKRIPSI = data.getStringExtra("DESKRIPSI");

        nama.setText(NAMA);
        harga.setText(HARGA);
        deskripsi.setText(DESKRIPSI);
        Order.setText(NAMA);
        order.setText(this.getString(R.string.order_first)+" "+NAMA+" "+this.getString(R.string.order_back));
        String BASE_URL = this.getString(R.string.BASE_URL);
        String URL = BASE_URL+"filefoto/"+GAMBAR;
        Glide.with(DetailProductActivity.this)
                .load(URL)
                .into(gambar);
        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logic();
            }
        });
    }
//    private void Information(){
//        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
//        Call<ResponseModel> Information = api.Information();
//        Information.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                mailMTB = response.body().getEmail();
//                String recipentList = mailMTB;
//                String[] recipent = recipentList.split(",");
//                Toast.makeText(DetailProductActivity.this, mailMTB, Toast.LENGTH_SHORT).show();
//                String nama = Nama.getText().toString();
//                String email = Email.getText().toString();
//                String kodetelephone = KodeNomor.getText().toString();
//                String telephone = NoTelepon.getText().toString();
//                String alamat = Alamat.getText().toString();
//                String kota = Kota.getText().toString();
//                String provinsi = Provinsi.getText().toString();
//                String kodepos = KodePos.getText().toString();
//                String orders = Order.getText().toString();
//                String jumlah = Jumlah.getText().toString();
//                String OrderMessageBuilder = "Nama : "+nama+"\nEmail : "+email+"\nNo Telepon : "+kodetelephone+telephone+"\nAlamat : "+alamat+"\nKota : "+kota+"\nKode Pos : "+kodepos+"\nProvinsi : "+provinsi+"\nNama Barang : "+orders+"\nJumlah barang : "+jumlah;
//
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_EMAIL,recipent);
//                intent.putExtra(Intent.EXTRA_SUBJECT,"Pemesanan Untuk : "+orders);
//                intent.putExtra(Intent.EXTRA_TEXT,OrderMessageBuilder);
//
//                intent.setType("message/rfc822");
//                startActivity(Intent.createChooser(intent,"Pilih Email Client"));
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(DetailProductActivity.this, "Check Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    private void logic(){
        String recipentList = mailMTB;
        String[] recipent = recipentList.split(",");
        Toast.makeText(DetailProductActivity.this, mailMTB, Toast.LENGTH_SHORT).show();
        String nama = Nama.getText().toString();
        String email = Email.getText().toString();
        String kodetelephone = KodeNomor.getText().toString();
        String telephone = NoTelepon.getText().toString();
        String alamat = Alamat.getText().toString();
        String kota = Kota.getText().toString();
        String provinsi = Provinsi.getText().toString();
        String kodepos = KodePos.getText().toString();
        String orders = Order.getText().toString();
        String jumlah = Jumlah.getText().toString();
        String OrderMessageBuilder = "Nama : "+nama+"\nEmail : "+email+"\nNo Telepon : "+kodetelephone+telephone+"\nAlamat : "+alamat+"\nKota : "+kota+"\nKode Pos : "+kodepos+"\nProvinsi : "+provinsi+"\nNama Barang : "+orders+"\nJumlah barang : "+jumlah;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipent);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Pemesanan Untuk : "+orders);
        intent.putExtra(Intent.EXTRA_TEXT,OrderMessageBuilder);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Pilih Email Client"));
    }
}