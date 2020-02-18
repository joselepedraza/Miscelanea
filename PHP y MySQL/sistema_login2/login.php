<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>

<style>
	h1,h2{text-align:center;}
	table{
		width:25%;
		background-color: #FFC;
		border: 2px dotted #F00;
		margin: auto;	
	}
	
	.izq{text-align:right;}
	.der{text-align:left;}
	td{
		text-align:center;
		padding:10px;
	}

</style>


</head>

<body>

<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">	<!--para recargar la misma pagina (para el array asociativo de la variable super global)PETICION AL SERVIDOR DE LA CARGA DE LA PROPIA PAGINA, POR LO QUE VOLVER A EJECUTAR EL CODIGO DE ARRIBA A ABAJO-->

<!--ESTA PAGINA TB SE DEBERA DE ENCARGAR DE COMPROBAR QUE EL USUARIO SE ENCUENTRA EN LA BASE DE DATOS-->

<?php

if(isset($_POST["enviar"])){

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
		/*echo "<h2>Adelante!!</h2>";
		header("Location:usuarios_registrados1.php");*/
		
	}else{/*2opc: bucle inf ó rewrite*/
	
		/*header("location:login.php");	redirige para que vuelva a introducir*/
		echo "ERROR. Usuario o contraseña incorrectos";
		
	}
	
	
}catch(Exception $e){
	
	die("Error: " . $e->getMessage());
}

}

?>


<?php	//para que aparezca el formulario solo si no se ha iniciado sesion (formulario.html)

	if(!isset($_SESSION["usuario"])){
		include("formulario.html");
	}else{
		echo "USUARIO: " . $_SESSION["usuario"];	//solo aparecerá el nombre de usuario que ha iniciado sesion	
	}

?>


<h2>CONTENIDO DE LA WEB</h2>
<table width="800" border="0">
	<tr>
    	<td><img src="img/img1.jpg" width="330" height="166"></td>
        <td><img src="img/img2.jpg" width="330" height="166"></td>
    
    </tr>
    <tr>
    	<td><img src="img/img3.jpg" width="330" height="166"></td>
        <td><img src="img/img4.jpg" width="330" height="166"></td>
    
    </tr>

</table>

</body>
</html>