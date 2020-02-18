package com.example.buttonclicked;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Elementos IU
    //private EditText editTextName;
    private View btnNext;
    private final String SMS = "Hello from the other side"; //elemento a pasar al otro activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciar los elementos de la UI con sus referencias
        //editTextName = (EditText) findViewById((R.id.editTextName));    //buscamos por id la referencia en la clase R(donde estan guardadas todas), hacemos casting al view y guardamos en variable

        btnNext = (Button) findViewById(R.id.buttonToSecondActivity);//como recibimos unn view casteamos

        //Evento click del boton para pasar al siguiente activity
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //evento asociado al boton btnNext
                //String name = editTextName.getText().toString();
                //if (name != null && !name.isEmpty()){   //si el nombre introducido no es nulo y se ha introducido un nombre

                    //para comunicarse con el segundo activity y mandarle el string
                    Intent intent = new Intent (MainActivity.this, SecondActivity.class);//objeto para comunicarse entre activity(desde,hasta)-EXPLICITO-
                    intent.putExtra("sms", SMS); //pasamos por id y contenido para comprobar en el segundo activity con el bundle devuelto por intent
                    startActivity(intent);//lo lanzamos
                /*}
                else{
                    Toast.makeText(MainActivity.this, "The name is mandatory, please type it", Toast.LENGTH_SHORT).show();
                }*/
            }
        });


    }


}
