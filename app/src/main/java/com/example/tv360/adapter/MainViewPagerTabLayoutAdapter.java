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
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewPagerTabLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.tablayout_viewholder,parent,false);
        return new MainViewPagerTabLayoutViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MainViewPagerTabLayoutViewHolder holder, int position) {
        if( listhomemodels.get(position).getContent() == null)
        {
            return;
        }
        HomeModel homeModel = listhomemodels.get(position);
        holder.gifImageView.setText(homeModel.getName());
        PlayingVideoTVAdapter modelAdapter = new PlayingVideoTVAdapter();
        modelAdapter.setData(context, homeModel.getContent(),homeModel.getDisplay());
        holder.recyclerView.setAdapter(modelAdapter);

    }

    @Override
    public int getItemCount() {

        return listhomemodels.size();
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
}
