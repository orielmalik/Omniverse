package com.example.omniverse.interfaces;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omniverse.Meeting;
import com.example.omniverse.ObjectEntity;
import com.example.omniverse.R;

import java.util.List;

public class ObjectEntityAdapter extends ArrayAdapter<ObjectEntity> {

    private Context context;
    private List<ObjectEntity> objectEntityList;

    public ObjectEntityAdapter(Context context, List<ObjectEntity> objectEntityList) {
        super(context, R.layout.list_item_layout, objectEntityList);
        this.context = context;
        this.objectEntityList = objectEntityList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_layout, parent, false);
        }

        ObjectEntity objectEntity = objectEntityList.get(position);

        TextView textViewName = view.findViewById(R.id.textViewName);
        // Set the text for other TextViews based on ObjectEntity properties

        String genderString = objectEntity.getGender() ? "Male" : "Female";
        textViewName.setText( objectEntity.getAlias() + "::"+ genderString);
        // Set other properties accordingly
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event
                //Toast.makeText(context, "Clicked on item with Name: " + objectEntity.getAlias(), Toast.LENGTH_SHORT).show();

                // Start a new activity
                Intent intent = new Intent(context, Meeting.class);
                // You can pass data to the new activity if needed
                intent.putExtra("extraKey", objectEntity.getAlias()+":"+objectEntity.getActive()+
                        "-"+objectEntity.getGender()+"%"+
                        objectEntity.getObjectId());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
