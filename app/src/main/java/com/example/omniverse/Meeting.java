package com.example.omniverse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.omniverse.Ids.CreatedBy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Meeting extends AppCompatActivity {
    private ImageView profile;
    private TextView name,status,message;
    private EditText text;
    private  String gender;
    private  String id;
    private Context context;
    private TextView[]mes;
    DataManager dataManager;

    private FloatingActionButton sent,gift,attr,tips,qz,cb;//gift-makeGifts,qz-quiz practice,cb-counselor tips
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meeting);
        mes=new TextView[5];
        dataManager=new DataManager();
        sent=findViewById(R.id.btn_sentmes);
        findViews();
        Intent intent= getIntent();
        String deatils=intent.getStringExtra("extraKey");
        name.setText(deatils.substring(0,deatils.indexOf(':')));
        switchByGender
                (deatils.substring(deatils.indexOf('-'),deatils.indexOf('%')),
                        deatils.substring(deatils.indexOf(':'),
                                deatils.indexOf('-')));
        id=deatils.substring(deatils.indexOf('%'));
context=this;
    }
    private  void  findViews()
    {
        this.name=findViewById(R.id.textViewName);
        this.status=findViewById(R.id.textViewStatus);
        this.profile=findViewById(R.id.imageViewProfile);
        mes[0]=findViewById(R.id.textViewMessage1);
        mes[1]=findViewById(R.id.textViewMessage2);
        mes[2]=findViewById(R.id.textViewMessage3);
        mes[3]=findViewById(R.id.textViewMessage4);
        mes[4]=findViewById(R.id.textViewMessage5);
        text=findViewById(R.id.editTextMessage);
        gift=findViewById(R.id.fab3);
       attr =findViewById(R.id.fab4);
       qz =findViewById(R.id.fab2);
       cb =findViewById(R.id.fab1);

    }

    private  void  UpdateHuman(ObjectBoundary objectBoundary)  {
        dataManager.api.updateObject(id.substring(0,id.indexOf(':')),id.substring(id.indexOf(':')),objectBoundary,
                "2024a.otiel.malik"  ,"superapp@gmail.com").enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                {
                    fillText();
                }

            }



            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Meeting.this, "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void fillText() {
        for (int i = 0; i <5 ; i++) {
            String str=mes[i].getText().toString();
            if(str.length()<140)
            {
                mes[i].setText(str+text.getText().toString());
                return;
            }
            else {
                mes[i].setText("");

            }
        }
    }

    private  void createMeeting()
    {
        ObjectBoundary meeting=new ObjectBoundary();
        meeting.setType("meeting");
        meeting.setCreatedBy(new CreatedBy("2024a.otiel.malik","super@gmail.com"));
        Map<String,Object>map=new HashMap<>();
        ArrayList<String>lst=new ArrayList<>();
        lst.add(text.getText().toString());
        map.put("messages",id+"content"+text.getText());
        meeting.setObjectDetails(map);
        dataManager.api.createObject(meeting).enqueue(new Callback<ObjectBoundary>() {
            @Override
            public void onResponse(Call<ObjectBoundary> call, Response<ObjectBoundary> response) {
                if(response.isSuccessful())
                    UpdateHuman(response.body());

            }

            @Override
            public void onFailure(Call<ObjectBoundary> call, Throwable t) {
                Toast.makeText(Meeting.this, "Cant update"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private  void  switchByGender(String gender,String status)
    {
        if(gender.equalsIgnoreCase("true"))
        {
            profile.setImageResource(R.drawable.ic_man);
        }
        else
        {
            profile.setImageResource(R.drawable.ic_woman);

        }
        if(status.equalsIgnoreCase("true"))
        {
            this.status.setText("online");
        }
        else {
            this.status.setText("offline");

        }
    }
    static  int counter=0;
    @Override
    protected void onResume() {
        super.onResume();
       sentMes();
cb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent j=new Intent(context,counselor.class);
        j.putExtra("type","counsleor");
        startActivity(j);
    }
});

    }



    private void  sentMes()
    {
       sent.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               createMeeting();
               Toast.makeText(Meeting.this, "SENT", Toast.LENGTH_SHORT).show();
           }
       });
       quizAttack();
    }









    // Helper method to check if the touch event is on the drawableEnd (icon)
    private boolean isDrawableClicked(EditText editText, MotionEvent event) {
        if (editText.getCompoundDrawablesRelative()[2] == null) {
            // No drawableEnd set
            return false;
        }

        int drawableWidth = editText.getCompoundDrawablesRelative()[2].getBounds().width();
        int editTextWidth = editText.getWidth();

        int paddingEnd = editText.getPaddingEnd();
        int startX = editTextWidth - paddingEnd - drawableWidth;
        int endX = editTextWidth - paddingEnd;

        float touchX = event.getX();

        return touchX >= startX && touchX <= endX;
    }

    private  void quizAttack()
    {
       qz.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     startActivity(new Intent(context,Quiz.class));
                                 }
                             }
       );
    }
}