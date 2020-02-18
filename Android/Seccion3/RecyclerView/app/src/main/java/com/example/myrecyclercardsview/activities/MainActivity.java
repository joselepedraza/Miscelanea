package com.example.myrecyclercardsview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import android.view.MenuItem;


import com.example.myrecyclercardsview.R;
import com.example.myrecyclercardsview.adapters.MyAdapter;
import com.example.myrecyclercardsview.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    //Puede ser declarado como "RecyclerView.Adapter" o como nuestra clase adaptador "MyAdapter"
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0; //para añadir elementos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies(); //Rellenamos la lista de nombres

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);



        //Implementamos nuestro OnItemClickListener propio, sobreescribiendo el método que nosotros definimos en el adaptador,
        //y recibiendo los parametros que necesitamos
        mAdapter =  new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() { //creamos una instancia de un objeto que implementa la interfaz onItemClickListener que esta en la clase MyAdapter (la mía)
            @Override                                                                                      // y sobreescribimos el metodo como en cualquier interfaz
            public void onItemClick(Movie movie, int position) {    //podria llegarle cualquier objeto de nuestro modelo, Movie en este caso
                deleteMovie(position);
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
                this.addMovie(0);//añadiremos el elemento en el top
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //para rellenar la lista
    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Ben Hur", R.drawable.benhur));
            add(new Movie("DeadPool", R.drawable.deadpool));
            add(new Movie("Guardians of the galaxy", R.drawable.guardians));
            add(new Movie("Warcraft", R.drawable.warcraft));
        }};
    }

    //Metodo para añadir al pulsar la opcion del menu
    private void addMovie(int position){
        movies.add(position, new Movie("New image " + (++counter), R.drawable.newmovie));
        //refrescamos el adaptador
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);// para actualizar si hay demasiados elementos en pantalla
        // (si añades elementos en el top, la visualizacion de la vista se mantiene en bottom)
    }

    //Metodo para borrar elemento
    private void deleteMovie(int position){
        movies.remove(position);
        //refrescamos el adaptador
        mAdapter.notifyItemRemoved(position);
    }


}
