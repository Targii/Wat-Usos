package com.example.watusos.watusos;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Obsługa ListView
        ListView schoolListView = (ListView) findViewById(R.id.listView);
        ArrayList<String> schoolStringArray = new ArrayList<>();
        schoolStringArray.add("WAT");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_rows, schoolStringArray);
        schoolListView.setAdapter(adapter);


        schoolListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent n = new Intent(getApplicationContext(), LoginScreen.class);
                n.putExtra("position", position);
                startActivity(n);
            }
        });



    }


}
