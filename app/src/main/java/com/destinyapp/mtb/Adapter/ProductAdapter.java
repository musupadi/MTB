package com.destinyapp.mtb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.mtb.Activity.ui.product.DetailProductActivity;
import com.destinyapp.mtb.Model.DataModel;
import com.destinyapp.mtb.R;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    public ProductAdapter (Context ctx,List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_product,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        holderData.Nama.setText(dm.getNamaproduk());
        holderData.Kode.setText(dm.getKode());
        String BASE_URL = ctx.getString(R.string.BASE_URL);
        String URL = BASE_URL+"filefoto/"+dm.getFoto();
        holderData.Gambar.setImageResource(R.drawable.logo);
        Glide.with(ctx)
                .load(URL)
                .into(holderData.Gambar);
        holderData.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, DetailProductActivity.class);
                goInput.putExtra("GAMBAR",dm.getFoto());
                goInput.putExtra("NAMA",dm.getNamaproduk());
                goInput.putExtra("HARGA",dm.getHarga());
                goInput.putExtra("DESKRIPSI",dm.getDeskripsi());
                ctx.startActivities(new Intent[]{goInput});
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Nama,Kode;
        LinearLayout list;
        ImageView Gambar;
        Button detail;
        public HolderData(View v){
            super(v);
                Nama = (TextView)v.findViewById(R.id.tvNama);
                Kode = (TextView)v.findViewById(R.id.tvSubNama);
                list = (LinearLayout) v.findViewById(R.id.ListProduct);
                Gambar = (ImageView)v.findViewById(R.id.ivGambar);
                detail = (Button)v.findViewById(R.id.btnDetail);
        }
    }

}
