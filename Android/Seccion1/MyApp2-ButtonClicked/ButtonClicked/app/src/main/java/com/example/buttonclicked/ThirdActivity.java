package com.example.buttonclicked;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
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
    private final int PICTURE_FROM_CAMERA = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        //Activamos flecha para volver atrás(actualizamos el manifest)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //para activar flecha para ir atras

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imgBtnPhone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imgBtnWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imgBtnCamera = (ImageButton) findViewById(R.id.imageButtonCamera);

        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//el view que llega sera del imgBtnPhone casteado
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {//si no es nulo y no esta vacio, comprobamos
                    //comprobar versión actual de android que esta corriendo la app
                    //M==api23 donde esta el salto generacional de funcionamiento de permisos en las app
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //COMPROBAR SI HA ACEPTADO, NO HA ACEPTADO O NUNCA SE LE HA PREGUNTADO
                        if(CheckPermission(Manifest.permission.CALL_PHONE)){
                            //HA ACEPTADO
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
                            if (ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;//hay que decirle ThirdActivity.this para indicar el contexto en el que estamos!!!!
                            }
                            startActivity(i);
                        }
                        else{
                            //0 NO HA ACEPTADO O ES LA PRIMERA VEZ QUE SE LE PREGUNTA
                            if(!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){//SI NO SE LE HA PREGUNTADO AUN
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            }
                            else{//SI HA DENEGADO
                                Toast.makeText(ThirdActivity.this,"Please, enable the request permission", Toast.LENGTH_LONG).show();
                                //sacamos la ventana para que los acepte (PORQUE EL USUARIO NO SABE DONDE ESTÁ EL SITIO PARA ACTIVARLO
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);//vamos a la ventana de los ajustes de app(que app?)
                                i.addCategory(Intent.CATEGORY_DEFAULT);//categorizamos nuestro intent en la categoria por deecto
                                i.setData(Uri.parse("package:" + getPackageName()));//PASAMOS LE URI DE NUESTRA APP (conseguimos el nombre del paquete con el metodo get)


                                /*AÑADIMOS BANDERAS(para llevar a una nueva tarea y volver a la anterior)   --FLAGS--

                                ej1- cuando en la aplicacion te lleva al navegador y cuando le damos atrás vuelve directamente en donde estabamos en la app que ha llamado al otro recurso
                                ej2- sistema login en app: hacemos login, vemos nuestro usuario, volvemos atras y cierra la app (no debemos volver a hacer login)
                                ej3- sistema login en app: hacemos login, vemos nuestro usuario, cerramos sesion, le damos atrás y volveriamos a ver nuestro ususario (fallo de seguridad, deberia volver a la pantalla login)
                                */
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//para volver a nuestra aplicacion cuando demos atrás despues de aceptar los permisos
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                                startActivity(i);
                            }
                        }

                        //(actualizado)requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE); //deberemos de sobrescribir el metodo onRequestPermissionResult
                    } else {   //si es de las versiones inferiores
                        OlderVersions(phoneNumber);
                    }
                } else {//si el numero de telefono es nulo o está vacio
                    Toast.makeText(ThirdActivity.this, "Insert a phone number", Toast.LENGTH_SHORT).show();
                }
            }

            //nos creamos dentro del contexto (porque no podemos utilizarlo fuera) ya que esto solo será para este caso de la llamada de telefon
            private void OlderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));/*-IMPLICITO-
                    le decimos la accion y le pasamos un URI tipo de referencia para que sepa como comportarse (en este caso, para llamar con el telefono
                    debemos tener en cuenta los permisos, a partir del API 23 version 6 y posterior, la aplicacion preguntará por los permisos cuando el usuario
                    requiera del uso de esa funcionalidad con esos permisos, antiguamente se aceptaban todos los permisos requeridos al descargar la aplicacion
                    Deberemos implementar las dos diferentes formas que hay para el las dos formas, la antigua y la nueva*/
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {//si tenemos el permiso, lanzaremos el activity (siempre esta aceptado, pero el actioncall necesita que se compruebe por seguridad)
                    if (ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();
                }
            }

        });

        //BOTÓN PARA LA DIRECCIÓN WEB (Y OTROS)
        imgBtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextWeb.getText().toString();
                String email = "joselee@gmail.com";
                if(url != null && !url.isEmpty()){
                    Intent intentWeb = new Intent();
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://"+url));
                }

                //Contactos
                Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));//para mostrar contactos de google
                //Email rápido (para abrir la app del correo electronico que tengamos configurado)
                Intent intentEmailTo = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"+email));
                //Email completo
                Intent intentMail = new Intent(Intent.ACTION_SEND, Uri.parse(email));
                //intentMail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail"); //sin contexto
                intentMail.setType("plain/text");
                intentMail.putExtra(Intent.EXTRA_SUBJECT, "Mail's title");
                intentMail.putExtra(Intent.EXTRA_TEXT, "Hi there, i love my app, but..................");
                intentMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"killo@gmail.com", "acho@gmail.com"});
                startActivity(Intent.createChooser(intentMail, "Elige cliente de correo"));

                //Telefono2, sin permisos requeridos (SE MOSTRARÁ MARCADO EN LA APP DE TEL)
                Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6565445454"));    //PARA AHORRARSE EL TEMA DE LOS PERMISOS O FAKECLICKS

                //startActivity(intentMailTo);
            }


        });

        //BOTÓN PARA LA CÁMARA
        imgBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abrir cámara
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intentCamera, PICTURE_FROM_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case PICTURE_FROM_CAMERA:
                if(resultCode == Activity.RESULT_OK){
                    String result = data.toUri(0);
                    Toast.makeText(this, "Result:"+result, Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this, "There was an error with the picture, try again!", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
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
    private boolean CheckPermission(String permission){//comprueba que el usuario realmente haya aceptado el permiso (en las versiones antiguas acepta en la instalacion)
        int result = this.checkCallingOrSelfPermission(permission); //comprobara internamente si tenemos el permiso en el manifest!! (debemos agregarlo al manifest)
        return result == PackageManager.PERMISSION_GRANTED; //es un valor entero GRANTED==0 DENIED==1
    }
}
