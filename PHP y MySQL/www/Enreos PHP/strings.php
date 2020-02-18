<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>

<style>

	.resaltar{
		color: #F00;
		font-weight: bold;
	}

</style>

</head>

<body>

<?php
	echo "<p class='resaltar'> Esto es un ejemplo de string</p>";	//cuidado con comillas simples y dobles cuando son padres e hijas
	$nombre="Juan";
	echo "Hola $nombre";
	
	//comparar strings strcmp (incluye minuscula y mayuscula) strcasecmp (le da igual) 0-> si los valores coinciden; 1-> no coinciden
	
	$variable1="Casa";
	$variable2="CASA";
	$resultado=strcmp($variable1, $variable2);
	
	//echo "<br>El resultado es " . $resultado;
	
	if($resultado){
		echo "<br>No son iguales";	
	}else{
		echo "<br>Coinciden";
	}
	
	

?>

</body>
</html>