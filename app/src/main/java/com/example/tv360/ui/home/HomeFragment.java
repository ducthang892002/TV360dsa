package com.example.tv360.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tv360.adapter.RvAdapter;
import com.example.tv360.databinding.FragmentHomeBinding;
import com.example.tv360.model.DataObject;
import com.example.tv360.model.FilmModel;
import com.example.tv360.model.HomeModel;
import com.example.tv360.model.ListFilmModel;
import com.example.tv360.presenter.HomePresenter;
import com.example.tv360.retrofit.ApiService;
import com.example.tv360.retrofit.HomeService;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    RvAdapter rvAdapter;
    List<HomeModel> listitem = new ArrayList<HomeModel>();
    HomeService apiInterface;
    private  static  final  String SHARED_PREF_NAME = "mypref";

    private  static  final  String KEY_ACCESSTOKEN ="accessToken";
    private  static  final  String KEY_REFRESHTOKEN = "refreshToken";



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.viewhome.setLayoutManager(new LinearLayoutManager(requireContext()));
        GetData();

        return root;
    }

    private void GetData(){
         HomePresenter homePresenter= new HomePresenter();
        apiInterface = ApiService.getClient().create(HomeService.class);
        Call<DataObject> data = apiInterface.getHomeBox();

        data.enqueue(new Callback<DataObject>() {
            @Override
            public void onResponse(Call<DataObject> call, Response<DataObject> response) {

                DataObject dataObject = response.body();

                listitem = new HomePresenter().getdata(dataObject.getData());

                rvAdapter=new RvAdapter(getContext(), listitem);
                binding.viewhome.setAdapter(rvAdapter);
            }

            @Override
            public void onFailure(Call<DataObject> call, Throwable throwable) {
                call.cancel();
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}