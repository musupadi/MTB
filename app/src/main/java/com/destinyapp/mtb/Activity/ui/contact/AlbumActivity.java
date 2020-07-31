package com.destinyapp.mtb.Activity.ui.contact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.mtb.API.ApiRequest;
import com.destinyapp.mtb.API.RetroServer;
import com.destinyapp.mtb.Adapter.GambarAdapter;
import com.destinyapp.mtb.Model.DataModel;
import com.destinyapp.mtb.Model.ResponseModel;
import com.destinyapp.mtb.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {
    RecyclerView rvGambar;
    TextView Tittle;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    LinearLayout succes;
    RelativeLayout failed;
    ProgressBar pdAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        Tittle=(TextView)findViewById(R.id.tvTittle);
        rvGambar=(RecyclerView)findViewById(R.id.recyclerImage);
        succes=(LinearLayout)findViewById(R.id.indexSucces);
        failed=(RelativeLayout)findViewById(R.id.indexGagal);
        pdAlbum=(ProgressBar)findViewById(R.id.pdAlbum);
        Intent data = getIntent();
        final String ID = data.getStringExtra("ID");
        final String TITTLE = data.getStringExtra("TITTLE");
        Tittle.setText(TITTLE);
        rvGambar.setLayoutManager(new GridLayoutManager(this, 2));

        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> DataAlbum = api.DataAlbum(ID);
        DataAlbum.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                succes.setVisibility(View.VISIBLE);
                failed.setVisibility(View.GONE);
                pdAlbum.setVisibility(View.GONE);
                mItems=response.body().getData();
                mAdapter = new GambarAdapter(AlbumActivity.this,mItems);
                rvGambar.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                succes.setVisibility(View.GONE);
                failed.setVisibility(View.VISIBLE);
                pdAlbum.setVisibility(View.GONE);
            }
        });
    }
}