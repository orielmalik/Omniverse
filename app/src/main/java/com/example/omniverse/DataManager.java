package com.example.omniverse;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.omniverse.Ids.InvokedBy;
import com.example.omniverse.Ids.ObjectId;
import com.example.omniverse.Ids.TargetObject;
import com.example.omniverse.Ids.UserId;
import com.example.omniverse.interfaces.CallBackIntent;
import com.example.omniverse.interfaces.MiniAppCommandBoundary;
import com.example.omniverse.interfaces.RestApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    Retrofit retrofit;
    RestApi api;
    private UserEntity create;

    public DataManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http//:localhost:8085")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(RestApi.class);
    }

    public void Register(String Avatar, String Email, Role role, String username, String type, String alias) {
        ObjectEntity o;
        NewUserBoundary n = new NewUserBoundary();
        n.setEmail(Email);
        n.setAvatar(Avatar);
        n.setRole(role);
        n.setUsername(username);

        api.create(n).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.isSuccessful()) {
                    Log.d(null, "onResponse: " + true);
                    setCreate((UserEntity) response.body());
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.d("onFailure: ", "gaya" + t.getMessage());
            }
        });


    }

    public UserEntity getCreate() {
        return create;
    }

    public void setCreate(UserEntity create) {
        this.create = create;
    }
}




