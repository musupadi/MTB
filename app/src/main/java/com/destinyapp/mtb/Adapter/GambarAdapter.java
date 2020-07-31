package com.destinyapp.mtb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.mtb.Model.DataModel;
import com.destinyapp.mtb.R;

import java.util.List;

public class GambarAdapter extends RecyclerView.Adapter<GambarAdapter.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    public GambarAdapter (Context ctx,List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_gambar,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        String BASE_URL = ctx.getString(R.string.BASE_URL);
        String URL = BASE_URL+"photogallery/"+dm.getBerkas();
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
        ImageView Gambar;
        public HolderData(View v){
            super(v);
            Gambar = (ImageView)v.findViewById(R.id.ivImage);
        }
    }
}
