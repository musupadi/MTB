package com.destinyapp.mtb.API;

import com.destinyapp.mtb.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("DataAlbum")
    Call<ResponseModel> DataAlbum(@Field("id_gallery") String id_gallery);

    @FormUrlEncoded
    @POST("KategoriProduct.php")
    Call<ResponseModel> KategoriProduct(@Field("kategori") String kategori);

    @FormUrlEncoded
    @POST("Login")
    Call<ResponseModel> Login(@Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("Register")
    Call<ResponseModel> Register(@Field("username") String username,
                                 @Field("password") String password,
                                 @Field("nama") String nama);
    //GET METHOD
    //ALL
    @GET("AllProduct")
    Call<ResponseModel> AllProduct();

    @GET("Information.php")
    Call<ResponseModel> Information();

    //Limits
    @GET("BannerTop")
    Call<ResponseModel> BannerTop();

    @GET("Album")
    Call<ResponseModel> Album();

    @GET("Blog")
    Call<ResponseModel> Blog();
}
