package com.chat.project;

import static com.chat.project.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class search extends AppCompatActivity {
Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_search);
        Spinner spinner2;
        spinner2 = findViewById(id.spinner1);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();
                // Handle the selected item
                Toast.makeText(search.this, ""+selectedItem,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected
            }
        });
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("select country");
        arrayList.add("India");
        arrayList.add("America");
        arrayList.add("China");
        arrayList.add("Iraq");
        arrayList.add("Japan");arrayList.add("Germany");arrayList.add("England");arrayList.add("France");
        arrayList.add("Pakistan");
        arrayList.add("Canada");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner2.setAdapter(adapter);

        submit=findViewById(id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=spinner2.getSelectedItem().toString();
                Intent i=new Intent(search.this,second.class);
                i.putExtra("editTextValue",text);
            startActivity(i);
            }
        });


    }
}