package com.example.mardiana.network;

import com.example.mardiana.config.Constants;
import com.example.mardiana.model.LihatQuisioner;
import com.example.mardiana.model.Quisioner;
import com.example.mardiana.model.GetQuisioner;
import com.example.mardiana.model.UserModel;
import com.example.mardiana.model.HasilQuisioner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import java.util.List;

public interface APIService {
  @FormUrlEncoded
  @POST("api/login")
  Call<UserModel.UserDataModel> postLogin(@Field("username") String username,
                                            @Field("password") String password);

  @GET("api/getGejala")
  Call<GetQuisioner> getQuisioner();

  @FormUrlEncoded
  @POST("api/register")
  Call<UserModel.UserDataModel> postRegister(@Field("username") String username,
                                             @Field("password") String password,
                                             @Field("email") String email);
  @FormUrlEncoded
  @POST("api/gantipassword")
  Call<UserModel.UserDataModel> postGantiPassword(@Field("username") String username,
                                                  @Field("passwordbaru") String passwordbaru);

  @FormUrlEncoded
  @POST("api/updateprofil")
  Call<UserModel.UserDataModel> postUpdateProfil(@Field("id") String id,
                                               @Field("username") String username,
                                               @Field("password") String password,
                                               @Field("gantipassword") String gantipassword,
                                               @Field("email") String email);
  @POST("api/getNaive")
  Call<HasilQuisioner> postGejala(@Body HasilQuisioner hasil);

  @POST("api/getprofile")
  Call<UserModel.UserDataModel> postId(@Body UserModel iduser);

  @POST("api/gethasil")
  Call<LihatQuisioner> postLihatHasil(@Body LihatQuisioner iduser);


    class Factory{
        public static APIService create(){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            // set log level
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            builder.addInterceptor(httpLoggingInterceptor).build();
            OkHttpClient client = builder.build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit.create(APIService.class);
        }
    }
}
