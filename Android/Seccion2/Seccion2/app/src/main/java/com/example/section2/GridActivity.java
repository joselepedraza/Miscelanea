package com.example.section2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {


    private List<String> names; //elementos del listView
    private GridView gridView;

    private MyAdapter myAdapter;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        //instanciamos listView de la UI
        gridView = (GridView) findViewById(R.id.gridView);
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


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridActivity.this, "Clicked: "+names.get(position), Toast.LENGTH_LONG).show();//cambiamos el contexto
            }
        });

        //Enlazamos con nuestro adaptador personalizado
        myAdapter = new MyAdapter(this, R.layout.grid_item, names);
        gridView.setAdapter(myAdapter);

        //necesitamos decirle que registre un contextMenu
        registerForContextMenu(gridView);   //en este caso para los atributos del gridView
    }
    //inflamos el layout del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu){//inflamos el actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }
    //manejamos los eventos click en el menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item){ //para manejar los diferentes botones
        switch (item.getItemId()){
            case R.id.add_item: //el el caso del boton de añadir
                this.names.add("Added nº"+(++counter)); //añadimos nuevo nombre
                //avisamos al adaptador de que hemos incrementado en 1 el valor del array y que se tiene que actualizar
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //inflamos el layout del context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;    //para acceder al array actual
        menu.setHeaderTitle(this.names.get(info.position));//mostramos el nombre de la celda a borrar junto a la opcion de borrar

        inflater.inflate(R.menu.context_menu, menu);
    }

    //manejamos eventos click en el context menu
    @Override
    public boolean onContextItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();//para rescatar info acerca del item clickeado en nuestra lista

        switch (item.getItemId()){
            //para borrar el item en el que se ha hecho click
            case R.id.delete_item:  //lo borramos
                this.names.remove(info.position);
                this.myAdapter.notifyDataSetChanged();//y notificamos cambios al adaptador
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
