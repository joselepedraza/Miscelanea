package com.example.myrecyclerview;

//agregamos al buid.gradle ( implementation 'com.android.support:recyclerview-v7:+')

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {//extiende de la clase myAdapter en el recyclerView que le pasamos el ViewHolder

    private List<String> names; //lista de nombres
    private int layout; //layout con el que queremos inflar la vista
    private OnItemClickListener itemClickListener; //se añade al crear la interface


    //Constructor
    public MyAdapter(List<String> names, int layout, OnItemClickListener listener){
        this.names = names;
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
        holder.bind(names.get(position), itemClickListener);    //el holder llama a su metodo bind y le pasa el obj actual y el itemClickListener (en el bind tiene definido su comportamiento)
    }

    //numero de item que tenemos
    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{ //Cada vez que se itere la lista de elementos le llega un view
                                                                    //le pasamos directamente el view sin el convertView (sin el viewHolder en el setTag
                                                                    //Ahora le pasamos al viewHolder directamente el view y extraemos la referencia
        public TextView textViewName; //lo que tenemos en el recycler_view_item

        //constructor que hereda de la clase ViewHolder que esta en RecyclerView
        public ViewHolder (View itemView){//
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        }
        //Aparte del volcado de datos, definimos como trabaja el itemClickListener (nuestra interfaz)
        //le pasamos el modelo, el evento Listener
        public void bind(final String name, final OnItemClickListener listener){
            //le volcamos la info de nuestro modelo
            this.textViewName.setText(name);
            //Definimos el click listener para cada una de sus vistas
            //istancia para la vista entera, cada vista dentro de una lista es un elemento!
            itemView.setOnClickListener(new View.OnClickListener() {    //se renderizará cada vez que se haga un click en un elemento
                @Override
                public void onClick(View view){
                    listener.onItemClick(name, getAdapterPosition());   //debemos rescatar la posicion cuando le clickamos (
                }
            });
        }
    }

    //Creamos interface despues del viewHolder y tenemos que implementar nuestro propio método OnClickListener
    public interface OnItemClickListener{
        void onItemClick(String name, int position);//mandaremos directamente el obj y la posicion que queremos trabajar
    }
}
