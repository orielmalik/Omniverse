package com.example.omniverse;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.omniverse.Ids.CreatedBy;
import com.example.omniverse.Ids.UserId;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Register extends AppCompatActivity {
private Spinner year,month,day,ex;
private ToggleButton gender,type;
private MaterialButton materialButton;
    DataManager dataManager=new DataManager();

private EditText username,password,email,spec,ph;
    private Context context;
    private String superapp="2024a.otiel.malik";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        initViews();
        context=this;
    }


private  void initViews()
{
year=findViewById(R.id.spinnerYear);
month=findViewById(R.id.spinnerMonth);
day=findViewById(R.id.spinnerDay);
username=findViewById(R.id.editTextUsername);
email=findViewById(R.id.editTextEmail);
password=findViewById(R.id.editTextPassword);
gender=findViewById(R.id.toggleButtonGender);
type=findViewById(R.id.toggleButtonPosition);
    ex=findViewById(R.id.spinnerExperienceYears);

getSpinners(year,month,day);
materialButton=findViewById(R.id.buttonRegister);
spec=findViewById(R.id.editTextSpecialization);
ph=findViewById(R.id.editTextPhoneNumber);
}



private void fillObject() {

    ObjectBoundary entity = new ObjectBoundary();
    entity.setType(!type.isChecked() ? "Dreamer" : "Counselor");
    entity.setAlias(username.getText().toString());
    entity.setCreatedBy(new CreatedBy(superapp, email.getText().toString()));
    Map<String, Object> map = new HashMap<>();
    map.put("location", "NORTH");
    map.put("gender", String.valueOf(!gender.isChecked()));
    map.put("viewscount", (long) (877 + Math.random() * 99999));
    map.put("avatar", "avatar");
    map.put("password", password.getText().toString());
    map.put("username", username.getText().toString());
    if(entity.getType().equals("Counselor")) {
        map.put("specialization", spec.getText().toString());
        map.put("phoneNumber", ph.getText().toString());
        if(ex.getSelectedItem()==null)
        {
            map.put("experience", 0);

        }
        else {
            map.put("experience", 5);
        }
    }

    //if (year.getSelectedItem() != null && month.getSelectedItem() != null && day.getSelectedItem() != null) {
    //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    map.put("birthdate", 21000 /*calculateDaysAlive(year, month, day)*/);
    // }
    // }
    entity.setObjectDetails(map);

    dataManager.api.createObject(entity).enqueue(new Callback<ObjectBoundary>() {
        @Override
        public void onResponse(Call<ObjectBoundary> call, Response<ObjectBoundary> response) {
            if(response.isSuccessful())
            {
                startActivity(new Intent(context, Menu.class));
            }
            else
                 Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<ObjectBoundary> call, Throwable t) {
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d(null, "tackle: "+t.getMessage());


        }
    });

}

    private  void getSpinners(Spinner spinnerYear, Spinner spinnerMonth, Spinner spinnerDay) {
// Create adapters for each spinner with integer arrays
        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, getYears());
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(yearAdapter);

        ArrayAdapter<Integer> dayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, getDays());
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(dayAdapter);

        ArrayAdapter<Integer> monthAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, getMonths());
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(monthAdapter);
        String[] yearsArray = new String[51];
        for (int i = 0; i <= 50; i++) {
            yearsArray[i] = String.valueOf(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, yearsArray
        );

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        ex.setAdapter(adapter);

    }
    // Helper methods to generate number arrays (modify as needed)
    // Function to get a list of years
    private List<Integer> getYears() {
        List<Integer> years = new ArrayList<>();

        // Adjust the range of years based on your requirements
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int startYear = currentYear - 100; // You can adjust the number of years in the past

        for (int year = startYear; year <= currentYear; year++) {
            years.add(year);
        }

        return years;
    }


    private Integer[] getMonths() {
        return new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    }
 private Integer[] getDays() {
        Integer[]arr=new Integer[31];
     for (int i = 1; i <31 ; i++) {
         arr[i]=i;
     }
        return arr;
    }

    @Override
    protected void onResume() {
        super.onResume();
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillObject();

            }
        });
    }
}