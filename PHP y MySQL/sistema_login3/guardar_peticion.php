<?php

	session_start();

    try{
        $base=new PDO("mysql:host=localhost; dbname=pruebas" , "root", "");	/*datos de conexion a la base de datos en phpmyadmin*/
        /*establecemos propiedaddes de la conexion:*/
        $base->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);/*el objeto base llama a la funcion atribute*/

        //preparamos la sentencia para insertar un contacto en la bd
        $sql="insert into contact_form (USERID, IDSTATE, REQUEST, DATE) VALUES (:user_id, :state_id, :request, NOW())";

        $user_id = $_SESSION['userid'];// el id del usuario que guardamos en session cuando hicimos login
        $sate_id = 1; //este id corresponde con el estado que metimos en la base de datos manualmente (waiting for review)
        $request = htmlentities(addslashes($_POST['message'])); //mensaje que escribe el usuario enviado por POST
		

        //bindear valores
        $resultado=$base->prepare($sql);
        $resultado->bindValue(":user_id", $user_id);
        $resultado->bindValue(":state_id", $sate_id);
        $resultado->bindValue(":request", $request);

        /*EJEUTAMOS NUESTRA INSTRUCCION SQL*/
        $resultado->execute();

        $_SESSION['mensaje_insertado'] = "<br><br>THE QUERY HAS BEEN SENT. PLEASE CLOSE SESSION.<br>";

        header("Location:/Enreos PHP/sistema_login3/Contact.php");

    }catch(Exception $e){

        die("Error: " . $e->getMessage());
    }
