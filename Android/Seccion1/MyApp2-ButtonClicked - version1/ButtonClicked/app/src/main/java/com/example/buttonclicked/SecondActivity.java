package com.example.buttonclicked;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    //Paso por intent explicito:
    //1ºrescatar el text view
    //2ºrescatar el valor que se manda cuando se hace la transicion desde el primer activity hasta el segundo (en un bundle)
    //3ºy meterlo dentro del text view

    private TextView textView;  //recogemos el text view

    private Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        textView = (TextView) findViewById(R.id.textViewMain);//nos traemos la vista (el text view) - casting
        btnNext = (Button) findViewById(R.id.buttonGoSharing);

        Bundle bundle = getIntent().getExtras();//tomar los datos del intent - cuando se manda un intent con datos se rescatan en forma de bundle (imaginar como caja)
        //y comprobamos que no esté vacío!
        if(bundle != null && bundle.getString("sms") != null){//accedemos
            String sms = bundle.getString("sms"); //rescatamos el valor del string a traves de su clave
            Toast.makeText(SecondActivity.this, sms, Toast.LENGTH_LONG).show();//mostramos el mensaje de sms
            textView.setText(sms);//lo ponemos en la etiqueta
        }
        else{//si es nulo el bundle o el string
            Toast.makeText(SecondActivity.this, "Empty SMS", Toast.LENGTH_LONG).show();//mensaje vacío
        }

        //le añadimos funcionalidad al boton para pasar al tercer activity
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);//-EXPLICITO-
                startActivity(intent);
            }
        });
    }
}
