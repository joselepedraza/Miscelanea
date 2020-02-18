<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>

<?php	//para destruir (cerrar) sesion abierta

	session_start(); //debemos reanudarla primero (para indicar cual es la sesion que queremos cerrar, a pesar de que solo haya una abierta)
	
	session_destroy();	//cerramos sesion
	
	header("Location:login.php"); //redirigimos al inicio de seision

?>

</body>
</html>