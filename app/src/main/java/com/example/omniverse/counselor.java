package com.example.omniverse;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.omniverse.Ids.InvokedBy;
import com.example.omniverse.Ids.ObjectId;
import com.example.omniverse.Ids.TargetObject;
import com.example.omniverse.Ids.UserId;
import com.example.omniverse.interfaces.MiniAppCommandBoundary;
import com.google.android.material.button.MaterialButton;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class counselor extends AppCompatActivity {
    private TextView[] arr;
    DataManager dataManager;
    private EditText einfo;
    MaterialButton mb;
    String type;
    static  int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_counselor);
        arr = new TextView[8];
        findViews();
        dataManager = new DataManager();
        type = getIntent().getStringExtra("type");

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (type.equalsIgnoreCase("counselor")) {
            invoke("counselor-GiveTips");
        } else if (type.equalsIgnoreCase("dreamer")) {

        }
    }

    private void findViews() {
        arr[0] = findViewById(R.id.text_title);
        arr[1] = findViewById(R.id.text_tips_title);
        arr[2] = findViewById(R.id.text_tip_1);
        arr[3] = findViewById(R.id.text_tip_2);
        arr[4] = findViewById(R.id.text_services_title);
        arr[5] = findViewById(R.id.text_service_1);
        arr[6] = findViewById(R.id.text_service_2);
        arr[7] = findViewById(R.id.text_additional_info);
        einfo = findViewById(R.id.einfo);
        mb = findViewById(R.id.binfo);
    }

    String superapp = "2024a.otiel.malik";

    private void invoke(String comm) {
        MiniAppCommandBoundary m = new MiniAppCommandBoundary();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> tips = new HashMap<>();
        mb(tips);
        map.put("tips", tips);

        m.setCommandAttributes(map);
        m.setCommand(comm);
        m.setInvocationTimestamp(new Date());
        m.setInvokedBy(new InvokedBy(new UserId(superapp, "miniapp@gmail.com")));
        m.setTargetObject(new TargetObject(new ObjectId(superapp, "bd0c34f0-0e74-415c-8a86-b3c0a3e7a1ba")));
        dataManager.api.invoke("CounT", m).enqueue(new Callback<List<MiniAppCommandBoundary>>() {
            @Override
            public void onResponse(Call<List<MiniAppCommandBoundary>> call, Response<List<MiniAppCommandBoundary>> response) {

            }

            @Override
            public void onFailure(Call<List<MiniAppCommandBoundary>> call, Throwable t) {

            }
        });
    }

    private void mb(Map<String,Object>map)
    {
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                map.put("tip"+counter,einfo.getText().toString());
                arr[(int) ((Math.random()*(4))+2)].setText(einfo.getText());
            }
        });
    }
}

