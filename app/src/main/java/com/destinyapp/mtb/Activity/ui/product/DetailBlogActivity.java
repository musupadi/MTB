package com.destinyapp.mtb.Activity.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.destinyapp.mtb.R;

public class DetailBlogActivity extends AppCompatActivity {
    TextView Post,Author,Tittle,Deskripsi;
    ImageView Gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_blog);

        Post=(TextView)findViewById(R.id.tvDatePost);
        Author=(TextView)findViewById(R.id.tvAuthor);
        Tittle=(TextView)findViewById(R.id.tvTittle);
        Deskripsi=(TextView)findViewById(R.id.tvDeskripsi);
        Gambar=(ImageView)findViewById(R.id.ivImage);
        Intent data = getIntent();
        final String GAMBAR = data.getStringExtra("GAMBAR");
        final String POST = data.getStringExtra("POST");
        final String AUTHOR = data.getStringExtra("AUTHOR");
        final String TITTLE = data.getStringExtra("TITTLE");
        final String DESKRIPSI = data.getStringExtra("DESKRIPSI");
        Post.setText("Post: "+POST);
        Author.setText("By: "+AUTHOR);
        Tittle.setText(TITTLE);
        Deskripsi.setText(DESKRIPSI);
        String BASE_URL = this.getString(R.string.BASE_URL);
        String URL = BASE_URL+"filefoto/"+GAMBAR;
        Glide.with(this)
                .load(URL)
                .into(Gambar);
    }
}