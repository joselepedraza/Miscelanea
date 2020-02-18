package com.example.buttonclicked;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnWeb;
    private ImageButton imgBtnCamera;

    private final int PHONE_CALL_CODE = 100;   //codigo para comprobar permisos con el requestPermision en las nuevas versiones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imgBtnPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imgBtnWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imgBtnCamera = (ImageButton) findViewById(R.id.imageButtonCamera);

        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//el view que llega sera del imgBtnPhone casteado
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null) {//si se ha escrito el numero
                    //comprobar versión actual de android que esta corriendo la app
                        //M==api23 donde esta el salto generacional de funcionamiento de permisos en las app
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE); //deberemos de sobrescribir el metodo onRequestPermissionResult
                    }
                        //NewerVersions();

                    else{   //si es de las versiones inferiores
                        OlderVersions(phoneNumber);
                    }
                }
            }


            //nos creamos dentro del contexto (porque no podemos utilizarlo fuera) ya que esto solo será para este caso de la llamada de telefon
            private void OlderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));/*-IMPLICITO-
                    le decimos la accion y le pasamos un URI tipo de referencia para que sepa como comportarse (en este caso, para llamar con el telefono
                    debemos tener en cuenta los permisos, a partir del API 23 version 6 y posterior, la aplicacion preguntará por los permisos cuando el usuario
                    requiera del uso de esa funcionalidad con esos permisos, antiguamente se aceptaban todos los permisos requeridos al descargar la aplicacion
                    Deberemos implementar las dos diferentes formas que hay para el las dos formas, la antigua y la nueva*/
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {//si tenemos el permiso, lanzaremos el activity
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    //newersVersions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            //Estamos en el caso del telefono (como podemos tener mas peticiones, hacemos un switch)
            switch (requestCode) {
                case PHONE_CALL_CODE:
                    String permission = permissions[0]; //ya que solo tenemos uno guardado en la primera posicion
                    int result = grantResults[0];

                    if (permission.equals(Manifest.permission.CALL_PHONE)) {//si el permiso es el permiso del telefono
                        //comprobamos si ha sido aceptado o denegada la peticion de permiso
                        if (result == PackageManager.PERMISSION_GRANTED) {
                            //concedió su permiso
                            String phoneNumber = editTextPhone.getText().toString();//cogemos el telefono del editText
                            Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)); //creamos el intent para cambiar al activity para llamar

                            //alt+enter porque si no se agregan los permisos asi justo antes del startActivity no funcionan, es necesario poner esto justo una linea encima
                            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            startActivity(intentCall);
                        }
                        else{//no concedió su permiso
                            Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
    }


    //creamos los metodos para de comprobacion de permisos fuera del onCreate
    private boolean CheckPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission); //comprobara internamente si tenemos el permiso en el manifest!! (debemos agregarlo al manifest)
        return result == PackageManager.PERMISSION_GRANTED; //es un valor entero GRANTED==0 DENIED==1
    }
}
