
<?php	//para destruir (cerrar) sesion abierta

	session_start(); //debemos reanudarla primero (para indicar cual es la sesion que queremos cerrar, a pesar de que solo haya una abierta)
	
	session_destroy();	//cerramos sesion
	
	header("Location:/Enreos PHP/sistema_login3/Contact.php"); //redirigimos al inicio de seision

?>
