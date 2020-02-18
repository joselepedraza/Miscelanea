package com.example.myrecyclercardsview.adapters;

//agregamos al buid.gradle ( implementation 'com.android.support:recyclerview-v7:+')
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myrecyclercardsview.R;
import com.example.myrecyclercardsview.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {//extiende de la class myAdapter en el recyclerView que le pasamos el ViewHolder

    private List<Movie> movies; //lista de nombres
    private int layout; //layout con el que queremos inflar la vista
    private OnItemClickListener itemClickListener; //se añade al crear la interface


    //Constructor
    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener){
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = listener;
    }
    //Sobreescribimos los tres metodos para nuestro adaptador
    //Creamos, inflamos y devolvemos el ViewHolder (al que le pasamos la vista al constructor del ViewHOlder)

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);   //Inflamos la vista con el contexto del padre
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Cogemos nuestro modelo y volcamos los datos en el textView (nos creamos metodo dentro del ViewHolder: bind)
    @Override   //se llama solo 1 vez cuando se llama cuando se crea el recyclerView, despues no se vuelve a renderizar por cada uno de los objetos
    public void onBindViewHolder(ViewHolder holder, int position) {//Hará el volcado de datos
        holder.bind(movies.get(position), itemClickListener);    //el holder llama a su metodo bind y le pasa el obj actual y el itemClickListener (en el bind tiene definido su comportamiento)
    }

    //numero de item que tenemos
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{ //Cada vez que se itere la lista de elementos le llega un view
                                                                    //le pasamos directamente el view sin el convertView (sin el viewHolder en el setTag
                                                                    //Ahora le pasamos al viewHolder directamente el view y extraemos la referencia
        //Elementos UI a rellenar
        public TextView textViewName;
        public ImageView imageViewPoster;

        //constructor que hereda de la clase ViewHolder que esta en RecyclerView
        public ViewHolder (View itemView){
            //Recibe la View completa. La pasa al constructor padre y enlazamos referencias UI
            //con las propiedades ViewHolder declaradas justo arriba
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);

        }
        //Aparte del volcado de datos, definimos como trabaja el itemClickListener (nuestra interfaz)
        //le pasamos el modelo, el evento Listener
        public void bind(final Movie movie, final OnItemClickListener listener){
            //Procesamos los datos a renderizar
            textViewName.setText(movie.getName());

            Picasso.get().load(movie.getPoster()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(movie.getPoster());

            //Definimos que por cada elemento de nuestro recyclerview, tenemos un clicklistener
            //que se comporta de la siguiente forma..
            itemView.setOnClickListener(new View.OnClickListener() {    //se renderizará cada vez que se haga un click en un elemento
                @Override
                public void onClick(View view){
                    listener.onItemClick(movie, getAdapterPosition());   //debemos rescatar la posicion cuando le clickamos (
                }
            });
        }
    }

    //Declaramos nuestra interfaz con el/los metodo/s a implementar
    public interface OnItemClickListener{
        void onItemClick(Movie movie, int position);//mandaremos directamente el obj y la posicion que queremos trabajar
    }
}
