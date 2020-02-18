<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>

<?php
	/*Funciones (tb podemos ponerlas en otro documento)
	
	include("proporciona_datos.php"); //si hubiese un error porque no existe el archivo, se ejecuta igualmente pero mostrando errores(+flexible)
	require("proporciona_datos.php");	//dejaria de ejecutar el programa en esa instruccion
	*/
	function dameDatos(){
			echo "Este es el mensaje del interior de la funcion <br>";
	}
	

?>

<?php

	echo "Este es el primer mesanje <br>";
	
	
	dameDatos();
	
	echo "Este es el segundo mensaje <br>";
	
	

?>

</body>
</html>