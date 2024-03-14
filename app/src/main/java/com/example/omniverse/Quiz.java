package com.example.omniverse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quiz extends AppCompatActivity {
    DataManager dataManager;
    MaterialButton m;
    EditText editText;
    TextView qu;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        LottieAnimationView animationView = findViewById(R.id.lottieAnimationView);
        animationView.setSpeed(1.5f);
        context=this;
        animationView.playAnimation();
        dataManager = new DataManager();
        m = findViewById(R.id.buttonSubmit);
        editText = findViewById(R.id.editTextAnswer);
        qu = findViewById(R.id.textViewQuestion);
    }

    static int counter = 0;

    private Map<String, Object> fillMap() {
        Map<String, Object> loveQuestions = new HashMap<>();

        loveQuestions.put("1", "What is your idea of a perfect date?");
        loveQuestions.put("2", "What does love mean to you?");
        loveQuestions.put("3", "Describe your dream romantic getaway.");
        loveQuestions.put("4", "What is the most romantic thing someone has done for you?");
        loveQuestions.put("5", "What qualities do you value most in a romantic partner?");
        loveQuestions.put("6", "If you could have dinner with any famous couple, dead or alive, who would it be?");
        loveQuestions.put("7", "What is your favorite love song?");
        loveQuestions.put("8", "Do you believe in love at first sight?");
        loveQuestions.put("9", "What is the most important aspect of a successful relationship?");
        loveQuestions.put("10", "If you could travel back in time, what romantic era would you visit?");
        loveQuestions.put("11", "What is your favorite romantic movie?");
        loveQuestions.put("12", "How do you express love and affection?");
        loveQuestions.put("13", "What is your love language?");
        loveQuestions.put("14", "What is your favorite love quote?");
        loveQuestions.put("15", "If you could write a love letter to your younger self, what would you say?");
        loveQuestions.put("16", "What is your idea of a romantic home-cooked meal?");
        loveQuestions.put("17", "What is the most thoughtful gift you've ever received?");
        loveQuestions.put("18", "What is your favorite memory with your significant other?");
        loveQuestions.put("19", "If you could be any fictional couple, who would you be?");
        loveQuestions.put("20", "What romantic gesture melts your heart every time?");
        loveQuestions.put("21", "How do you celebrate special occasions with your loved ones?");
        loveQuestions.put("22", "What is your go-to love song for karaoke?");
        loveQuestions.put("23", "Describe your ideal engagement proposal.");
        loveQuestions.put("24", "What is your favorite romantic dessert?");
        loveQuestions.put("25", "If you could have a romantic dinner with any historical figure, who would it be?");
        if(!editText.getText().toString().isEmpty())
        {
            qu.setText((String)loveQuestions.get(String.valueOf(counter)));
            loveQuestions.put("answer"+counter,editText.getText().toString());

        }
        else {
            Toast.makeText(Quiz.this, "FILL ANSWER", Toast.LENGTH_SHORT).show();
        }
        return  loveQuestions;
    }


    public  void  doqz()
    {
        ObjectBoundary boundary =new ObjectBoundary();
        boundary.setType("QUIZ");
        boundary.setAlias("QUIZ NUM"+String.valueOf(Math.random()*88)+(Math.random()*88));
        boundary.setObjectDetails(fillMap());
        if(counter>=25 ) {
            dataManager.api.createObject(boundary).enqueue(new Callback<ObjectBoundary>() {
                @Override
                public void onResponse(Call<ObjectBoundary> call, Response<ObjectBoundary> response) {
                    if (response.isSuccessful()) {
                        startActivity(new Intent(context, SearchByCretiria.class));
                    }
                }

                @Override
                public void onFailure(Call<ObjectBoundary> call, Throwable t) {

                }
            });
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                doqz();//create new Quiz
            }
        });
    }
}