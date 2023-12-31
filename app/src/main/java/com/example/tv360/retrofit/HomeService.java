package com.example.tv360.retrofit;

import com.example.tv360.model.DataObject;
import com.example.tv360.model.DataObjectLoadMore;
import com.example.tv360.model.DataObjectUrlVideo;
import com.example.tv360.model.HomeModel;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface HomeService {

    @GET("public/v1/composite/get-home")
    Call<DataObject> getHomeBox();

    @GET("public/v1/composite/get-home-live")
    Call<DataObject> getTVBox();

    @GET("public/v1/composite/get-link")
    Call<DataObjectUrlVideo> getlinka(
                              @Query("id") String id,
                              @Query("type") String type);

    @GET("public/v1/vod/get-list-item-collection")
    Call<DataObjectLoadMore> getCollectionDetail(
            @Query("id") String id,
            @Query("limit") int limit,
            @Query("offset") int offset);

    @GET("public/v1/live/get-detail-category")
    Call<DataObject> getCategoryLive(
            @Query("id") String id,
            @Query("limit") int limit,
            @Query("offset") int offset);
}
