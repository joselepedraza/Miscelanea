package com.example.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;

import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0; //para añadir elementos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = this.getAllNames(); //Rellenamos la lista de nombres

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Para cambiar la forma de verlo en lista, cuadricula o cualquier formato
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);//nos permite diferentes formatos de vistas en nuestro recyclerview lo renderizará de diferente tamaño
                            //en este caso no podemos usar: setHasFixedSize(true)

        //Declaramos nuestro adaptador
        mAdapter =  new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() { //creamos una instancia de un objeto que implementa la interfaz onItemClickListener que esta en la clase MyAdapter (la mía)
            @Override                                                                                      // y sobreescribimos el metodo como en cualquier interfaza
            public void onItemClick(String name, int position) {    //podria llegarle cualquier objeto de nuestro modelo, strin en este caso
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
                deleteName(position); //Borraremos el elemento cuando hagamos un click
            }
        });
        //Si sabemos que el layout de nuestro item en nuestro recyclerView no se va a hacer mas grande
        mRecyclerView.setHasFixedSize(true);    //mejora
        //Implementamos una animacion
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//demomento la pordefecto

        //Le adjuntamos nuestro adaptador y el layout
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    //Creamos el menu para añadir
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //manejador de eventos dentro del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                this.addName(0);//añadiremos el elemento en el top
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //para rellenar la lista
    private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("Alejandro");
            add("Jose");
            add("Barrera");
            add("Ruben");
            add("Antonio");
            add("Ruben");
        }};
    }

    //Metodo para añadir al pulsar la opcion del menu
    private void addName(int position){
        names.add(position, "New name " + (++counter));
        //refrescamos el adaptador
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);// para actualizar si hay demasiados elementos en pantalla
        // (si añades elementos en el top, la visualizacion de la vista se mantiene en bottom)
    }

    //Metodo para borrar elemento
    private void deleteName(int position){
        names.remove(position);
        //refrescamos el adaptador
        mAdapter.notifyItemRemoved(position);
    }
}
