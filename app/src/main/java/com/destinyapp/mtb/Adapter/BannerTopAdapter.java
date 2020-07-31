package com.destinyapp.mtb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.mtb.Model.DataModel;
import com.destinyapp.mtb.R;

import java.util.List;

public class BannerTopAdapter extends RecyclerView.Adapter<BannerTopAdapter.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    public BannerTopAdapter (Context ctx,List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_home,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        holderData.Judul.setText(dm.getJudul());
        holderData.Deskripsi.setText(dm.getDescription());
        String BASE_URL = ctx.getString(R.string.BASE_URL);
        String URL = BASE_URL+"filefoto/"+dm.getImage();
        holderData.Gambar.setImageResource(R.drawable.logo);
        Glide.with(ctx)
                .load(URL)
                .into(holderData.Gambar);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Judul,Deskripsi;
        ImageView Gambar;
        RelativeLayout ListBanner;
        public HolderData(View v){
            super(v);
            Judul = (TextView)v.findViewById(R.id.tvJudul);
            Deskripsi = (TextView)v.findViewById(R.id.tvDeskripsi);
            Gambar = (ImageView)v.findViewById(R.id.ivGambar);
            ListBanner = (RelativeLayout)v.findViewById(R.id.ListBanner);
        }
    }
}


