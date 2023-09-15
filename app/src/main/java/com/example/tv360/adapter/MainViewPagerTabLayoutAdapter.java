package com.example.tv360.adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tv360.R;
import com.example.tv360.model.FilmModel;
import com.example.tv360.model.HomeModel;
import com.example.tv360.presenter.HomePresenter;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class MainViewPagerTabLayoutAdapter extends RecyclerView.Adapter<MainViewPagerTabLayoutAdapter.MainViewPagerTabLayoutViewHolder> {

    private Context context;

    private List<HomeModel> listhomemodels;


    public MainViewPagerTabLayoutAdapter(Context context, List<HomeModel> listhomemodels) {
        this.context = context;
        this.listhomemodels = listhomemodels;
    }

    public void setData(List<HomeModel> data) {
        this.listhomemodels = data;
        notifyDataSetChanged();
    }


    public  class MainViewPagerTabLayoutViewHolder extends  RecyclerView.ViewHolder{

        TextView gifImageView;

        RecyclerView recyclerView;

        public MainViewPagerTabLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            gifImageView = itemView.findViewById(R.id.tv_imageView);
            recyclerView = itemView.findViewById(R.id.inner_recyleview_tablaout);
        }
    }


    @NonNull
    @Override
    public MainViewPagerTabLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.tablayout_viewholder,parent,false);

        return new MainViewPagerTabLayoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewPagerTabLayoutViewHolder holder, int position) {
        HomeModel homeModel = listhomemodels.get(position);
        if(listhomemodels == null)
        {
//            holder.gifImageView.setText("TAB NULL " + position);
            return;
        }
        holder.gifImageView.setText(homeModel.getName());
        RvFilmImageAdapter modelAdapter = new RvFilmImageAdapter();
        modelAdapter.setData(context, homeModel.getContent(),homeModel.getDisplay());
        holder.recyclerView.setAdapter(modelAdapter);

    }



    @Override
    public int getItemCount() {
        return listhomemodels.size();
    }
}