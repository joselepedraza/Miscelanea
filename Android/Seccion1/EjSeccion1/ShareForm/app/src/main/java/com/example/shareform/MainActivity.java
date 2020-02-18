package com.example.shareform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //Elementos de la UI
    private Button btnNext;
    private EditText editTextName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Forzar el logo en todas las versiones de android
        getSupportActionBar().setLogo(R.mipmap.ic_mylauncher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //Instanciamos los elementos de la UI con sus referencias
        editTextName = (EditText) findViewById(R.id.editTextName);
        btnNext = (Button) findViewById(R.id.buttonToSecondActivity);

        //Evento click del botón para pasar al segundo activity
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                if (name != null && !name.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "The name is mandatory, please type it", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
