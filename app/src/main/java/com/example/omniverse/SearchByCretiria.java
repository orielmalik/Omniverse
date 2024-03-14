package com.example.omniverse;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.omniverse.Ids.CommandId;
import com.example.omniverse.Ids.InvokedBy;
import com.example.omniverse.Ids.ObjectId;
import com.example.omniverse.Ids.TargetObject;
import com.example.omniverse.Ids.UserId;
import com.example.omniverse.interfaces.MiniAppCommandBoundary;
import com.example.omniverse.interfaces.ObjectEntityAdapter;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reactor.core.publisher.Flux;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchByCretiria extends AppCompatActivity implements SearchView.OnQueryTextListener  {
    private SearchView searchView;
    private Context context;
    private ListView listViewResults;
    String superapp="2024a.otiel.malik";
    private List<ObjectEntity>lst;
    private List<String> items; // This can be replaced with your actual data type (e.g., custom object)
    private ArrayAdapter<String> adapter;  // You might need to modify this adapter for custom data types
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_by_cretiria);



        searchView = findViewById(R.id.searchView);
        listViewResults = findViewById(R.id.listViewResults);

        // Initialize data (replace with your data source)

        context=this;
        dataManager=new DataManager();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
         if(!s.isEmpty()||s!=null) {
             search(s);
             Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
         }
        return false;
    }


    public void  search(String s) {
        MiniAppCommandBoundary m=new MiniAppCommandBoundary();
        //  m.setCommandId(new CommandId(superapp, "omn", "dsad23"));

        m.setCommand("search-ByUserNameLike");//i have more conditions at server side but no time
        // m.setInvocationTimestamp(new Date());
        m.setInvokedBy(new InvokedBy(new UserId(superapp,"miniapp@gmail.com")));
        m.setTargetObject(new TargetObject(new ObjectId(superapp,"bd0c34f0-0e74-415c-8a86-b3c0a3e7a1ba")));
        Map<String,Object>map=new HashMap<>();
        map.put("UserName",s);
        m.setCommandAttributes(map);
        String field="results";
        dataManager.api.invoke("searchBy",m).enqueue(new Callback<List<MiniAppCommandBoundary>>() {
            @Override
            public void onResponse(Call<List<MiniAppCommandBoundary>> call, Response<List<MiniAppCommandBoundary>> response) {
                if(response.isSuccessful())
                {
                    Log.d(null, "invokeRes: "+response.body().get(0).getCommandAttributes().get("results").toString());
                    ArrayList<MiniAppCommandBoundary>mlst=(ArrayList<MiniAppCommandBoundary>)response.body();
                    lst =new ArrayList<>();
                    try{

                       fillList( mlst.get(0).getCommandAttributes(),lst);

                    }catch (NullPointerException nullPointerException)
                    {
                        Toast.makeText(context, nullPointerException.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                    ObjectEntityAdapter objectEntityAdapter=new ObjectEntityAdapter(context,lst);
                    listViewResults.setAdapter(objectEntityAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<MiniAppCommandBoundary>> call, Throwable t) {
                Log.d(null, "invokeFail: "+t.getMessage());

            }
        });
    }



    public  void fillList(Map<String,Object>resultMap,  List<ObjectEntity> objectEntityList)
    {
        List<?> rawList = (List<?>) resultMap.get("results");

        if (rawList != null) {
            for (Object item : rawList) {
                if (item instanceof LinkedTreeMap) {
                    Gson gson = new Gson();
                    ObjectEntity objectEntity = gson.fromJson(gson.toJson(item), ObjectEntity.class);
                    Toast.makeText(context, objectEntity.getAlias(), Toast.LENGTH_SHORT).show();
                    objectEntityList.add(objectEntity);
                }
            }
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        searchView.setOnQueryTextListener(this);

    }
}