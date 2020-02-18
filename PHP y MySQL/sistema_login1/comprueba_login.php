<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>

<?php

try{
	$base=new PDO("mysql:host=localhost; dbname=pruebas" , "root", "");	/*datos de conexion a la base de datos en phpmyadmin*/
	/*establecemos propiedaddes de la conexion:*/
	$base->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);/*el objeto base llama a la funcion atribute*/
	$sql="SELECT * FROM USUARIOS_PASS WHERE USUARIOS= :login AND PASSWORD= :password"; /*uso los dos marcadores para la sentecia sql (instruccion sql encargada de mirar en la base de datos si el usuario esta registrado)*/
	/*creamos consulta preparada con marcadores:*/
	$resultado=$base->prepare($sql);	/*creamos variable resultado=conexion que llama a la funcion prepare, encargada de preparar la instruccion sql*/
	/*rescatamos login y password introducido en el formulario de la pagina:*/
	$login=htmlentities(addslashes($_POST["login"]));	/*convertimos cualquier simbolo en html"_-... (addslashes omitirá aquellos caracteres de inyeccion sql)*/
	$password=htmlentities(addslashes($_POST["password"]));/*para que rescate lo que el usuario introdujo en el cuadro de texto password del formulario*/
	
	/*IDENTIFICAMOS CADA MARCADOR CON SU CUADRO DE TEXTO(bindValue)*/
	$resultado->bindValue(":login", $login);
	$resultado->bindValue(":password", $password);
	/*EJEUTAMOS NUESTRA INSTRUCCION SQL*/
	$resultado->execute();
	
	
	/*PARA SABER SI EL USUARIO ESTÁ DENTRO O NO DE LA BASE DE DATOS (rowCount-> devuelve el numero de registros que devuelve una consulta, si no existe = 0 filas, si existe = 1 row)*/
	$numero_registro=$resultado->rowCount();
	if($numero_registro != 0){/*si existe*/
		session_start(); //para crear sesion para el usuario que se acaba de logear (variable superglobal $_SESSION, podremos usarlo en cualquier lugar de nuestro sitio)
		$_SESSION["usuario"]=$_POST["login"];	//rescatamos de la pagina del formulario (cuadro de texto login del formulario)
		//echo "<h2>Adelante!!</h2>";
		header("Location:usuarios_registrados1.php");
		
	}else{/*2opc: bucle inf ó rewrite*/
	
		header("location:login.php");	/*redirige para que vuelva a introducir*/
		
	}
	
	
}catch(Exception $e){
	
	die("Error: " . $e->getMessage());
}



?>

</body>
</html>