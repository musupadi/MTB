package com.destinyapp.mtb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.mtb.Activity.ui.contact.AlbumActivity;
import com.destinyapp.mtb.Model.DataModel;
import com.destinyapp.mtb.R;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    public AlbumAdapter (Context ctx,List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_album,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        holderData.Tittle.setText(dm.getTittle());
        String BASE_URL = ctx.getString(R.string.BASE_URL);
        String URL = BASE_URL+"photogallery/"+dm.getImage();
        holderData.Gambar.setImageResource(R.drawable.logo);
        Glide.with(ctx)
                .load(URL)
                .into(holderData.Gambar);
        holderData.Lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, AlbumActivity.class);
                goInput.putExtra("ID",dm.getId_gallery());
                goInput.putExtra("TITTLE",dm.getTittle());
                ctx.startActivities(new Intent[]{goInput});
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Tittle;
        ImageView Gambar;
        Button Lihat;
        public HolderData(View v){
            super(v);
            Tittle = (TextView)v.findViewById(R.id.tvTittle);
            Gambar = (ImageView)v.findViewById(R.id.ivImage);
            Lihat = (Button)v.findViewById(R.id.btnDetail);
        }
    }
}