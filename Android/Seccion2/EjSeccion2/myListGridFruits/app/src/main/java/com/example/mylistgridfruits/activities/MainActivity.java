package com.example.mylistgridfruits.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mylistgridfruits.R;
import com.example.mylistgridfruits.adapters.FruitAdapter;
import com.example.mylistgridfruits.models.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {    //extender para el onItemClickListener

    // ListView, Gridview y Adapters
    private ListView listView;
    private GridView gridView;
    private FruitAdapter adapterListView;   //adaptador para cada uno
    private FruitAdapter adapterGridView;

    // Lista de frutas, modelo Fuit
    private List<Fruit> fruits;

    // Items en el option menu(cuadricula o lista)
    private MenuItem itemListView;
    private MenuItem itemGridView;

    // Variables
    private int counter = 0;//para contar los items añadidos
    private final int SWITCH_TO_LIST_VIEW = 0;//para el intercambio de lista <-> cuadricula
    private final int SWITCH_TO_GRID_VIEW = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mostramos el icono de la app
        this.enforceIconBar();

        this.fruits = getAllFruits();   //rellenamos la lista de frutas

        this.listView = (ListView) findViewById(R.id.listView); //rescatamos el listView y el gridView(sin visibilidad en el xml para mostrar primero el list)  del layout
        this.gridView = (GridView) findViewById(R.id.gridView);

        //Adjuntamos el mismo metodo click para ambos, tanto para el Grid como el List(implements en nuestra clase)
        this.listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        this.gridView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        //creamos los adaptadores
        this.adapterListView = new FruitAdapter(this, R.layout.list_view_item_fruit, fruits);//pasandoles layout diferentes y las misma coleccion y contexto
        this.adapterGridView = new FruitAdapter(this, R.layout.grid_view_item_fruit, fruits);

        //asignamos a cada uno su adaptador
        this.listView.setAdapter(adapterListView);
        this.gridView.setAdapter(adapterGridView);

        //registrar el context menu para ambos (ya que el context menu lo queremos en ambas vistas)
        registerForContextMenu(this.listView);
        registerForContextMenu(this.gridView);
    }


    //método para visualizar el icono de app
    private void enforceIconBar() {
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //sobreescribimos los metodos para nuestrooo objeto
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
        this.clickFruit(fruits.get(position));  //llamamos a clickFruit y le llegará la fruta
    }
    private void clickFruit(Fruit fruit) {
        // Diferenciamos entre las frutas conocidas y desconocidas (las recien añadidas son "desconocidas")
        if(fruit.getOrigin().equals("Unknown"))
            Toast.makeText(this, "Sorry, we don't have many info about " + fruit.getName(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "The best fruit from " + fruit.getOrigin() + " is " + fruit.getName(), Toast.LENGTH_SHORT).show();
    }


    //4 metodos sobreescritos

    //para crear el menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflamos el option menu con nuestro layout
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); //le inflamos con el layout de los 3 items option_menu.xml


        // Después de inflar, recogemos las referencias a los botones que nos interesan(IMPORTANTE--> siempre despues de inflar la vista)
        this.itemListView = menu.findItem(R.id.list_view);  //findItem devuelve un menu item (se crea al principio antes de la interaccion del usuario "onCreateOptionsMenu)
        this.itemGridView = menu.findItem(R.id.grid_view);
        return true;
    }

    //manejador de elementos del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Eventos para los clicks en los botones del options menu
        switch (item.getItemId()) {
            case R.id.add_fruit:
                this.addFruit(new Fruit("Added nº" + (++counter), R.mipmap.ic_new_fruit, "Unknown"));
                return true;
            case R.id.list_view:
                this.switchListGridView(this.SWITCH_TO_LIST_VIEW);  //para cambiar a lista
                return true;
            case R.id.grid_view:
                this.switchListGridView(this.SWITCH_TO_GRID_VIEW);  //para cambiar a cuadricula
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // Método para cambiar entre Grid/List view
    private void switchListGridView(int option) {
        if (option == SWITCH_TO_LIST_VIEW) {
            // Si queremos cambiar a list view, y el list view está en modo invisible!!(comprobar que no está ya visible-->en nuestro caso da igual porque lo tenemos oculto si fuese asi)
            if (this.listView.getVisibility() == View.INVISIBLE) {
                // ... escondemos el grid view, y enseñamos su botón en el menú de opciones
                this.gridView.setVisibility(View.INVISIBLE);
                this.itemGridView.setVisible(true);
                // no olvidamos enseñar el list view, y esconder su botón en el menú de opciones
                this.listView.setVisibility(View.VISIBLE);
                this.itemListView.setVisible(false);
            }
        } else if (option == SWITCH_TO_GRID_VIEW) {
            // Si queremos cambiar a grid view, y el grid view está en modo invisible!!
            if (this.gridView.getVisibility() == View.INVISIBLE) {
                // ... escondemos el list view, y enseñamos su botón en el menú de opciones
                this.listView.setVisibility(View.INVISIBLE);
                this.itemListView.setVisible(true);
                // no olvidamos enseñar el grid view, y esconder su botón en el menú de opciones
                this.gridView.setVisibility(View.VISIBLE);
                this.itemGridView.setVisible(false);
            }
        }
    }

    //
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Inflamos el context menu con nuestro layout
        MenuInflater inflater = getMenuInflater();
        // Antes de inflar, le añadimos el header dependiendo del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getName());  //da el nombre del objeto para meterlo en la cabecera
        // Inflamos
        inflater.inflate(R.menu.context_menu_fruits, menu);
    }

    //
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Obtener info en el context menu del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete_fruit:
                this.deleteFruit(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    // CRUD actions - Get, Add, Delete  (Create,Read,Update,Delete)
    //crea, rellena y devuelve lista de frutas con sus atributos y referencias a su icono (recordar quitar el shape)
    private List<Fruit> getAllFruits() {
        List<Fruit> list = new ArrayList<Fruit>() {{
            add(new Fruit("Banana", R.mipmap.ic_banana, "Gran Canaria"));
            add(new Fruit("Strawberry", R.mipmap.ic_strawberry, "Huelva"));
            add(new Fruit("Orange", R.mipmap.ic_orange, "Sevilla"));
            add(new Fruit("Apple", R.mipmap.ic_apple, "Madrid"));
            add(new Fruit("Cherry", R.mipmap.ic_cherry, "Galicia"));
            add(new Fruit("Pear", R.mipmap.ic_pear, "Zaragoza"));
            add(new Fruit("Raspberry", R.mipmap.ic_raspberry, "Barcelona"));
        }};
        return list;
    }

    private void addFruit(Fruit fruit) {
        this.fruits.add(fruit);//lo añadimos y
        // Avisamos del cambio en ambos adapters
        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
    }

    private void deleteFruit(int position) {
        this.fruits.remove(position);//borramos el elemento de la posicion
        // Avisamos del cambio en ambos adapters
        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
    }

}
