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
import com.destinyapp.mtb.Activity.ui.product.DetailBlogActivity;
import com.destinyapp.mtb.Model.DataModel;
import com.destinyapp.mtb.R;

import java.util.List;

public class FullBlogAdapter extends RecyclerView.Adapter<FullBlogAdapter.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    public FullBlogAdapter (Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_full_blog,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        holderData.Tittle.setText(dm.getTitle());
        holderData.Author.setText(dm.getAuthor());
        holderData.Date.setText(dm.getDate());
        if (dm.getDescription().length()>=75){
            holderData.Deskripsi.setText(dm.getDescription().substring(0,75)+"...");
        }else{
            holderData.Deskripsi.setText(dm.getDescription());
        }
        String BASE_URL = ctx.getString(R.string.BASE_URL);
        String URL = BASE_URL+"filefoto/"+dm.getImage();
        Glide.with(ctx)
                .load(URL)
                .into(holderData.Gambar);
        holderData.Selengkapnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, DetailBlogActivity.class);
                goInput.putExtra("GAMBAR",dm.getImage());
                goInput.putExtra("POST",dm.getDate());
                goInput.putExtra("AUTHOR",dm.getAuthor());
                goInput.putExtra("TITTLE",dm.getTitle());
                goInput.putExtra("DESKRIPSI",dm.getDescription());
                ctx.startActivities(new Intent[]{goInput});
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Author,Date,Tittle,Deskripsi;
        ImageView Gambar;
        Button Selengkapnya;
        public HolderData(View v){
            super(v);
            Tittle = (TextView)v.findViewById(R.id.tvTittle);
            Date = (TextView)v.findViewById(R.id.tvDatePost);
            Author = (TextView)v.findViewById(R.id.tvAuthor);
            Deskripsi = (TextView)v.findViewById(R.id.tvDeskripsi);
            Gambar = (ImageView)v.findViewById(R.id.ivGambar);
            Selengkapnya = (Button)v.findViewById(R.id.btnSelengkapnya);
        }
    }
}
