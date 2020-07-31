package com.destinyapp.mtb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.mtb.Model.TestModel;
import com.destinyapp.mtb.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<TestModel> dataList;
    private Context context;

    public CustomAdapter(Context context, List<TestModel> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View v;

        TextView Nama,Kode;
        LinearLayout list;
        ImageView Gambar;

        CustomViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            Nama = (TextView)v.findViewById(R.id.tvNama);
            Kode = (TextView)v.findViewById(R.id.tvSubNama);
            list = (LinearLayout) v.findViewById(R.id.ListProduct);
            Gambar = (ImageView)v.findViewById(R.id.ivGambar);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_product, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.Nama.setText(dataList.get(position).getNamaproduk());
        holder.Kode.setText(dataList.get(position).getKode());
        String BASE_URL = context.getString(R.string.BASE_URL);
        String URL = BASE_URL+"filefoto/"+dataList.get(position).getFoto();
        Glide.with(context)
                .load(URL)
                .into(holder.Gambar);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
