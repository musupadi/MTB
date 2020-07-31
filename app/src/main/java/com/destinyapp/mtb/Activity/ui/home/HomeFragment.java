package com.destinyapp.mtb.Activity.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.mtb.API.ApiRequest;
import com.destinyapp.mtb.API.RetroServer;
import com.destinyapp.mtb.Activity.MapsActivity;
import com.destinyapp.mtb.Adapter.AlbumAdapter;
import com.destinyapp.mtb.Adapter.BannerTopAdapter;
import com.destinyapp.mtb.Adapter.BlogAdapter;
import com.destinyapp.mtb.Adapter.ProductListAdapter;
import com.destinyapp.mtb.Model.DataModel;
import com.destinyapp.mtb.Model.ResponseModel;
import com.destinyapp.mtb.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ProgressBar pdProduct,pdBannerTop,pdAlbum,pdBlog;
    RecyclerView rvProduct;
    RecyclerView rvBannerTop;
    RecyclerView rvAlbum;
    RecyclerView rvBlog;
    ImageView facebook,googlemaps,whatsapp;
    TextView alamat,email,phone;
    String WhatsaApp="+6281384215205";
    LinearLayout succes;
    RelativeLayout failed;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBannerTop = (RecyclerView)view.findViewById(R.id.recyclerHome);
        rvProduct = (RecyclerView)view.findViewById(R.id.recyclerProduct);
        rvAlbum = (RecyclerView)view.findViewById(R.id.recyclerAlbum);
        rvBlog = (RecyclerView)view.findViewById(R.id.recyclerBlog);
        googlemaps = (ImageView)view.findViewById(R.id.btnGoogleMaps);
        whatsapp = (ImageView)view.findViewById(R.id.btnWhatsapp);
        facebook = (ImageView)view.findViewById(R.id.btnFacebook);
        alamat = (TextView)view.findViewById(R.id.tvAlamat);
        email = (TextView)view.findViewById(R.id.tvEmail);
        phone = (TextView)view.findViewById(R.id.tvPhone);
        pdAlbum = (ProgressBar)view.findViewById(R.id.pdAlbum);
        pdBannerTop = (ProgressBar)view.findViewById(R.id.pdHome);
        pdProduct = (ProgressBar)view.findViewById(R.id.pdProduct);
        pdBlog = (ProgressBar)view.findViewById(R.id.pdBlog);
        succes=(LinearLayout)view.findViewById(R.id.indexSucces);
        failed=(RelativeLayout)view.findViewById(R.id.indexGagal);
        //Get Information
//        Information();
        //BannerTop
        pdBannerTop.setVisibility(View.VISIBLE);
        BannerTop();
        //PRODUCT
        pdProduct.setVisibility(View.VISIBLE);
        Product();
        //ALBUM
        pdAlbum.setVisibility(View.VISIBLE);
        Album();
        //BLOG
        pdBlog.setVisibility(View.VISIBLE);
        Blog();
        googlemaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String daftarWA="https://api.whatsapp.com/send?phone=6281384215205&text=Nama%20%3A%20%0AAlamat%20%3A%20%0AJenis%0APekerjaan%20%3A%20";
                String url = "https://api.whatsapp.com/send?phone="+WhatsaApp;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private void Information(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Information = api.Information();
        Information.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                succes.setVisibility(View.VISIBLE);
                failed.setVisibility(View.GONE);
                alamat.setText(response.body().getData().get(0).getAddress());
                email.setText(response.body().getData().get(0).getEmail());
                phone.setText(response.body().getData().get(0).getTelephone());
                WhatsaApp = response.body().getData().get(0).getMobile().substring(0);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                succes.setVisibility(View.GONE);
                failed.setVisibility(View.VISIBLE);
            }
        });
    }
    private void BannerTop(){
        mManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rvBannerTop.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> BannerTop = api.BannerTop();
        BannerTop.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pdBannerTop.setVisibility(View.GONE);
                succes.setVisibility(View.VISIBLE);
                failed.setVisibility(View.GONE);
                mItems=response.body().getData();
                mAdapter = new BannerTopAdapter(getActivity(),mItems);
                rvBannerTop.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pdBannerTop.setVisibility(View.GONE);
                succes.setVisibility(View.GONE);
                failed.setVisibility(View.VISIBLE);
            }
        });
    }
    private void Product(){
        mManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rvProduct.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> AllProduct = api.AllProduct();
        AllProduct.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pdProduct.setVisibility(View.GONE);
                succes.setVisibility(View.VISIBLE);
                failed.setVisibility(View.GONE);
                mItems=response.body().getData();
                mAdapter = new ProductListAdapter(getActivity(),mItems);
                rvProduct.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pdProduct.setVisibility(View.GONE);
                succes.setVisibility(View.GONE);
                failed.setVisibility(View.VISIBLE);
            }
        });
    }
    private void Album(){
        mManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rvAlbum.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> AllProduct = api.Album();
        AllProduct.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pdAlbum.setVisibility(View.GONE);
                succes.setVisibility(View.VISIBLE);
                failed.setVisibility(View.GONE);
                mItems=response.body().getData();
                mAdapter = new AlbumAdapter(getActivity(),mItems);
                rvAlbum.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pdAlbum.setVisibility(View.GONE);
                succes.setVisibility(View.GONE);
                failed.setVisibility(View.VISIBLE);
            }
        });
    }
    private void Blog(){
        mManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rvBlog.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> AllProduct = api.Blog();
        AllProduct.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pdBlog.setVisibility(View.GONE);
                succes.setVisibility(View.VISIBLE);
                failed.setVisibility(View.GONE);
                mItems=response.body().getData();
                mAdapter = new BlogAdapter(getActivity(),mItems);
                rvBlog.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pdBlog.setVisibility(View.GONE);
                succes.setVisibility(View.GONE);
                failed.setVisibility(View.VISIBLE);
            }
        });
    }
}