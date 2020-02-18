<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>

<?php
	/*Variables estaticas*/
	
	function incrementaVariable(){
		static $contador=0; //solo se ejecuta la primera vez que llama a la funcion y el valor de la variable se conserva al finalizar la llamada
		$contador++;
		echo $contador . "<br>";
	}
	
	incrementaVariable();
	incrementaVariable();


?>

</body>
</html>