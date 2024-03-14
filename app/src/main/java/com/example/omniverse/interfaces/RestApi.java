package com.example.omniverse.interfaces;

import com.example.omniverse.NewUserBoundary;
import com.example.omniverse.ObjectBoundary;
import com.example.omniverse.ObjectEntity;
import com.example.omniverse.UserEntity;
import com.google.android.material.internal.ViewOverlayImpl;

import java.util.List;

import reactor.core.publisher.Flux;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {
    //object
    @GET("superapp/objects/{userSuperapp}/{userEmail}")
    Call<List<ObjectEntity>> getObjects( String superapp, String email);
    @POST("superapp/objects")
    Call<ObjectBoundary> createObject(@Body ObjectBoundary objectEntity);

    @PUT("superapp/objects/{superapp}/{id}")
    Call<Void> updateObject(String objectSuperapp, String id, ObjectBoundary update, String userSuperapp,
                                 String userEmail);
    @GET("superapp/objects/{superapp}/{id}")
    Call<ObjectEntity> getObject( @Path("superapp") String superapp,@Path("id") String id
    ,@Query("userSuperapp")String userSuperapp,@Query("userEmail")String useEmail);


  //user

    @POST("superapp/users")
    Call<UserEntity> create( @Body NewUserBoundary userBoundary);

    @GET("superapp/users/login/{superapp}/{email}")
    Call<UserEntity> login( @Path("superapp") String superapp,@Path("email") String email);

    //command

    @POST("superapp/miniapp/{miniAppName}")
    Call<List<MiniAppCommandBoundary>> invoke(@Path("miniAppName") String miniAppName, @Body MiniAppCommandBoundary miniAppCommandBoundary);
}

