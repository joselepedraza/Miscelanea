<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Pereira Aragüete Abogados</title>

<link rel="stylesheet" href="style_inicio_about_service_warning_contact_library.css"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link  rel="icon" href="img/hoja.png" type="image/png">
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
		echo "USER: " . $_SESSION["usuario"];	//solo aparecerá el nombre de usuario que ha iniciado sesion	
	}

?>
	<header>
    	<figure id="MainImg">
        	<img src="img/logo.png" alt="logo" width="300" height="125"/>
        </figure>
    	<h1>Contact Us</h1>
    
    </header>
    
    <nav>
    	<ul id="bar">
        	<a class="lbar" href="Home.html"><li class="linksBar">HOME</li></a>
            <a class="lbar" href="About.html"><li class="linksBar"> ABOUT US </li></a>
            <a class="lbar" href="Services.html"><li class="linksBar"> OUR SERVICES </li></a>
            <a class="lbar" href="Library.html"><li class="linksBar"> NEWSPAPER LIBRARY </li></a>
            <a class="lbar" href="Contact.php"><li class="linksBar"> CONTACT US </li></a>
            
        </ul>
    </nav>
    
  	<section id="sectServices">
    	<div class="contactText">
        	<article>
            <!--Envianos un mensaje-->
            	<h2>Make your inquiry</h2>
                <!--Si tienes alguna pregunta acerca de nuestros productos o servicios, por favor rellena el siguiente formulario y en breve nos pondremos en contacto contigo. También nos puedes llamar en horarios de oficina.-->
                <p>If you have any questions about our products or services, please fill in the following form and we will contact you as soon as possible. You can also call us during office hours.</p>
            	<img id="contactImg" src="img/background-pereira-aragüete-abogados.jpg" alt="logo" width="600" height="350"/>
            </article>
        
        </div>
        
        
		<?php	//para que aparezca el formulario solo si no se ha iniciado sesion (formulario.html)

            if(!isset($_SESSION["usuario"])){
                echo "<br><br><br><br>PLEASE LOGIN FOR FILL YOUR REQUEST";	//solo aparecerá el nombre de usuario que ha iniciado sesion	
            }else{
                echo "<br><br><br>USER: " . $_SESSION["usuario"];
                include("formulario1.html");
            }

        ?>
        
        
	</section>
    
	<footer>
        <figure>
        	<img src="img/footer-araguete.png" alt="logofooter" width="200" height="70">
            
        </figure>
        
    	<address>
        Address: 
        Avd/ Villanueva 3, 1º A 06005 Badajoz, Spain
        </address>
        
        <address>
        Contact us: +34 924223821
        </address>
        
        <div>
        <a href="Warning.html">LEGAL WARNING</a>
        </div>
       <!-- 
        <figure>
        	<img src="img/footer-araguete.png" alt="logofooter" width="200" height="70">
        </figure>
        -->
    </footer>

</body>
</html>
