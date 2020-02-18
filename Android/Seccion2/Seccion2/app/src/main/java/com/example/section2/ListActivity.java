package com.example.section2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> names; //elementos del listView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciamos listView de la UI
        listView = (ListView) findViewById(R.id.listView);
        //datos a mostrar
        names = new ArrayList<String>();
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Ruben");
        names.add("Santiago");
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Santiago");
        names.add("Santiago");
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Ruben");
        names.add("Santiago");
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Santiago");
        names.add("Santiago");


        //necesitamos un adaptador (forma visual en que mostraremos estos datos)
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);//(contexto, creamos layout para mostrar, datos a mostrar)

        //Enlazamos nuestro adaptador con nuestro ListView
        //listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this, "Clicked: "+names.get(position), Toast.LENGTH_LONG).show();
            }
        });

        //Enlazamos con nuestro adaptador personalizado
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names);
        listView.setAdapter(myAdapter);

    }
}
