<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Untitled Document</title>
</head>

<body>
<!--incluimos filtro para aquellos usuarios que se hayan logeado correctamente-->
<?php

	session_start(); //reanudamos la sesion creada anteriormente (rescatamos lo que hay en la variable superglobal)
	
	if(!isset($_SESSION["usuario"])){	//si no se almacenó algo en la variable superglobal usuario
			header("Location:login.php"); //redirigimos a que inicie sesion
	}

?>

<h1>Bienvenidos Usuarios</h1>

<?php
	echo "Hola: " . $_SESSION["usuario"] . "<br><br>"
?>

<p><a href="cierre.php">CERRAR SESION</a></p>

<p>Esto es informacion solo para usuarios registrados</p>

<table width="383" height="166" border="1">
  <tr>
    <td colspan="3" align="center">Zona de usuarios</td>
    
  </tr>
  <tr>
    <td><a href="usuarios_registrados2.php">Pagina 1</a></td>
    <td><a href="usuarios_registrados3.php">Pagina 2</a></td>
    <td><a href="usuarios_registrados4.php">Pagina 3</a></td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>