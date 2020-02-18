<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Untitled Document</title>
</head>

<body>
<!--incluimos filtro para aquellos usuarios que se hayan logeado correctamente-->
<?php	//para comprobar si la sesion ha sido abierta previamente

	session_start(); //reanudamos la sesion creada anteriormente (rescatamos lo que hay en la variable superglobal)
	
	if(!isset($_SESSION["usuario"])){	//si no se almacenó algo en la variable superglobal usuario
			header("Location:login.php"); //redirigimos a que inicie sesion
	}

?>

<h1>Bienvenidos Usuarios</h1>

<?php
	echo "USUARIO: " . $_SESSION["usuario"] . "<br><br>"
?>

<p><a href="cierre.php">CERRAR SESION</a></p>

<p>Esto es informacion solo para usuarios registrados</p>
<p>&nbsp;</p>
<p><a href="usuarios_registrados1.php">VOLVER</a></p>
</body>
</html>