package com.example.section2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

//nos creamos aqui nuestro propio adaptador para el layout creado list_item (sobreescribiendo los metodos de la clase abstracta)
public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout; //int porque le pasamos la referencia del layout
    private List<String> names;

    public MyAdapter(Context context, int layout, List<String> names){//creamos constructor de la clase con lo necesario
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override   //(num de elementos a dibujar) le dice al adaptador cuantas veces hay que iterar sobre una coleccion dada (el n que devuelve son los numeros de items dibujados en el listview)
    public int getCount() {
        return this.names.size(); //devolvemos el numero de elementos que tenemos
    }

    @Override//obtener item de la coleccion
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override//obtiene el id del item de la coleccion
    public long getItemId(int id) {
        return id;
    }

    @Override //encargado de dibujar el item del listview
    public View getView(int position, View convertView, ViewGroup viewGroup) { //(pos de la vista, view-item que vamos a dibujar, grupo de vistas)
        /*
        1º)Cogemos el view que nos llega por defecto
        2º)Lo inflamos
        3º)Lo personalizamos (rellenarlo con nuestros datos)
        4º)Lo devolvemos
        */

        //OPC2: refinada.View Holder Pattern
        ViewHolder holder;

        if(convertView == null){//si viene nulo
            //Inflamos la vista que nos ha llegado con nuestro layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context); //sacamos del contexto el layout inflater
            convertView = layoutInflater.inflate(this.layout/*R.layout.list_item*/, null); //inflamos el layout (devuelve el view)

            holder = new ViewHolder();//nueva instancia
            //Referenciamos el elemento a modificar y lo rellenamos
            holder.nameTextView = (TextView) convertView.findViewById(R.id.textView);//añadimos a la propiedad de nuestra instancia del holder
            convertView.setTag(holder);//nos permite meterle el objeto (id) ó clave-valor

        }
        else{//si no es nulo, ha pasado previamente por el if por lo que el view tendrá el holder, que lo pasamos por el tag
            holder = (ViewHolder) convertView.getTag();
        }

        //Nos traemos el valor actual dependiente de la posicion
        String currentName = names.get(position);

        //Referenciamos el elemento a modificar y lo rellenamos
        holder.nameTextView.setText(currentName);

        //Devolvemos la vista inflada y moficicada con nuestros datos
        return convertView;


/*OPC1
        //creamos una vista que vamos a inflar
        View v = convertView;//Copiamos la vista

        //Inflamos la vista que nos ha llegado con nuestro layout personalizado
        LayoutInflater layoutInflater = LayoutInflater.from(this.context); //sacamos del contexto el layout inflater
        v = layoutInflater.inflate(R.layout.list_item, null); //inflamos el layout (devuelve el view)

        //Nos traemos el valor actual dependiente de la posicion
        String currentName = names.get(position);
        //currentName = (String) getItem(position); //asi se usaría el metodo de nuestra propia clase en vez de la linea anterior pero requiere casteo

        //Referenciamos el elemento a modificar y lo rellenamos
        TextView textView = (TextView) v.findViewById(R.id.textView);//debemos rellenar el textView que tenemos en el layout (LLAMADA POCO EFICIENTE tiene una carga muy grande de busqueda)
        //implementamos el patron View Holder Pattern para si ya ha entrado antes y ha encontrado la referencia no la tenga que volver a buscar
        textView.setText(currentName);

        //Devolvemos la vista inflada y moficicada con nuestros datos
        return v;
*/
    }
    //refinamiento eficiencia creando objeto holder con las propiedades de la UI
    //EL ADAPTADOR NO DEPENDE DE SI VA A SER UTILIZADO EN UN ListView o un GridView, asegurar referencia al layout
    static class ViewHolder {//tantas propiedades como elementos de la UI que queramos modificar
        private TextView nameTextView;
    }
}
