package com.example.serviceapp.core;

import com.example.serviceapp.home.ui.home.pojo.BannerResponse;
import com.example.serviceapp.home.ui.home.pojo.CategoryResponse;
import com.example.serviceapp.home.ui.home.pojo.CommonResponse;
import com.example.serviceapp.home.ui.home.pojo.ItemResponse;
import com.example.serviceapp.home.ui.home.pojo.NewsResponse;
import com.example.serviceapp.home.ui.home.pojo.SubCategoryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("get_categories")
    Call<CategoryResponse> getcategories();

    @GET("get_subcategories")
    Call<SubCategoryResponse> getSubCategories(@Query("category_id")String category_id);

    @GET("get_news")
    Call<NewsResponse> getNews();

    @GET("get_datas")
    Call<ItemResponse> getItems(@Query("sub_category_id")String sub_category_id);

    @FormUrlEncoded
    @POST("add_data")
    Call<CommonResponse> addItems(@Field("name")String name,@Field("phone")String phone,@Field("place")String place,@Field("sub_category_id")String sub_category_id,@Field("image")String image,@Field("pincode")String pincode);

    @FormUrlEncoded
    @POST("add_advertisement")
    Call<CommonResponse> addAdvertisement(@Field("name")String name,@Field("phone_number")String phone,@Field("message")String message);

    @FormUrlEncoded
    @POST("send_feedback")
    Call<CommonResponse> sendfeedback(@Field("name")String name,@Field("phone_number")String phone,@Field("feedback")String feedback);

    @GET("get_sliderimages")
    Call<BannerResponse> getBanners();


}
