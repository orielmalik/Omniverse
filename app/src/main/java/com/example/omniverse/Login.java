package com.example.omniverse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.omniverse.interfaces.CallBackIntent;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText superapp,email;
    private MaterialButton accept;
    private Context context;
    DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        context=this;
        superapp=findViewById(R.id.editTextUsernameLogin);
        email=findViewById(R.id.editTextPasswordLogin);
        dataManager=new DataManager();
        accept=findViewById(R.id.buttonLogin);
    }




    private void login() {
dataManager.api.login(superapp.getText().toString(),email.getText().toString())
        .enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if(response.isSuccessful())
                {
                    startActivity(new Intent(context,Menu.class));
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }


        });

    }
}