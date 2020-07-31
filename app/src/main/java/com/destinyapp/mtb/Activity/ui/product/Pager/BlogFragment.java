package com.destinyapp.mtb.Activity.ui.product.Pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.mtb.API.ApiRequest;
import com.destinyapp.mtb.API.RetroServer;
import com.destinyapp.mtb.Adapter.FullBlogAdapter;
import com.destinyapp.mtb.Model.DataModel;
import com.destinyapp.mtb.Model.ResponseModel;
import com.destinyapp.mtb.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlogFragment extends Fragment {
    RecyclerView rvBlog;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    LinearLayout succes;
    RelativeLayout failed;
    ProgressBar pdBlog;
    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBlog=(RecyclerView)view.findViewById(R.id.recyclerFullBlog);
        succes=(LinearLayout)view.findViewById(R.id.indexSucces);
        failed=(RelativeLayout)view.findViewById(R.id.indexGagal);
        pdBlog=(ProgressBar)view.findViewById(R.id.pdBlog);
        mManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvBlog.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> FullBlog = api.Blog();
        FullBlog.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                succes.setVisibility(View.VISIBLE);
                failed.setVisibility(View.GONE);
                pdBlog.setVisibility(View.GONE);
                mItems=response.body().getData();
                mAdapter = new FullBlogAdapter(getActivity(),mItems);
                rvBlog.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                succes.setVisibility(View.GONE);
                failed.setVisibility(View.VISIBLE);
                pdBlog.setVisibility(View.GONE);
            }
        });
    }
}