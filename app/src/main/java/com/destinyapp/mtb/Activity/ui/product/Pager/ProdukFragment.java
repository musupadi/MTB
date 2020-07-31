package com.destinyapp.mtb.Activity.ui.product.Pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.mtb.API.ApiRequest;
import com.destinyapp.mtb.API.RetroServer;
import com.destinyapp.mtb.Adapter.ProductAdapter;
import com.destinyapp.mtb.Model.DataModel;
import com.destinyapp.mtb.Model.ResponseModel;
import com.destinyapp.mtb.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdukFragment extends Fragment{
    RecyclerView rvCategory;
    TextView Kategori;
//    Spinner produk;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    LinearLayout succes;
    RelativeLayout failed;
    ProgressBar pdProduct;
    public ProdukFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produk, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCategory = (RecyclerView)view.findViewById(R.id.recycler);
//        produk = (Spinner)view.findViewById(R.id.spinnerKategori);
        Kategori = (TextView)view.findViewById(R.id.tvKategori);
        succes=(LinearLayout)view.findViewById(R.id.indexSucces);
        failed=(RelativeLayout)view.findViewById(R.id.indexGagal);
        pdProduct=(ProgressBar)view.findViewById(R.id.pdProduct);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.kategori_produk,R.layout.support_simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        produk.setAdapter(adapter);
//        produk.setOnItemSelectedListener(this);
        rvCategory.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        logic();


    }


//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        pdProduct.setVisibility(View.VISIBLE);
//        final String text = adapterView.getItemAtPosition(i).toString();
//        Kategori.setText(text);
//        if (text.equals("All Product")){
//            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
//            Call<ResponseModel> AllProduct = api.AllProduct();
//            AllProduct.enqueue(new Callback<ResponseModel>() {
//                @Override
//                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                    succes.setVisibility(View.VISIBLE);
//                    failed.setVisibility(View.GONE);
//                    pdProduct.setVisibility(View.GONE);
//                    mItems=response.body().getResult();
//                    mAdapter = new ProductAdapter(getActivity(),mItems);
//                    rvCategory.setAdapter(mAdapter);
//                    mAdapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onFailure(Call<ResponseModel> call, Throwable t) {
//                    succes.setVisibility(View.GONE);
//                    failed.setVisibility(View.VISIBLE);
//                    pdProduct.setVisibility(View.GONE);
//                }
//            });
//        }else{
//            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
//            Call<ResponseModel> AllProduct = api.KategoriProduct(text);
//            AllProduct.enqueue(new Callback<ResponseModel>() {
//                @Override
//                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                    succes.setVisibility(View.VISIBLE);
//                    failed.setVisibility(View.GONE);
//                    pdProduct.setVisibility(View.GONE);
//                    mItems=response.body().getResult();
//                    if (response.body().getResult().isEmpty()){
//                        Toast.makeText(getActivity(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
//                        mAdapter = new ProductAdapter(getActivity(),mItems);
//                        rvCategory.setAdapter(mAdapter);
//                        mAdapter.notifyDataSetChanged();
//                    }else{
//                        mAdapter = new ProductAdapter(getActivity(),mItems);
//                        rvCategory.setAdapter(mAdapter);
//                        mAdapter.notifyDataSetChanged();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseModel> call, Throwable t) {
//                    succes.setVisibility(View.GONE);
//                    failed.setVisibility(View.VISIBLE);
//                    pdProduct.setVisibility(View.GONE);
//                }
//            });
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
    private void logic(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> AllProduct = api.AllProduct();
        AllProduct.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                succes.setVisibility(View.VISIBLE);
                failed.setVisibility(View.GONE);
                pdProduct.setVisibility(View.GONE);
                mItems=response.body().getData();
                mAdapter = new ProductAdapter(getActivity(),mItems);
                rvCategory.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                succes.setVisibility(View.GONE);
                failed.setVisibility(View.VISIBLE);
                pdProduct.setVisibility(View.GONE);
            }
        });
    }
}
