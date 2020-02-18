<?php

	require_once("../models/mysql_conexion.php");
    require_once("../models/modelo_registro.php");

	$name = $_POST['name'];
	$apellidos = $_POST['apellidos'];
	$direccion = $_POST['direccion'];
	$telefono = $_POST['telefono'];
	$poblacion = $_POST['poblacion'];
	$codigo = $_POST['codigo'];
	$correo = $_POST['correo'];
	$usuario = $_POST['usuario'];
	$password = $_POST['password'];
	$genero = $_POST['genero'];

	$DB = conecta();

    $Validacion = validarDatos($name, $apellidos, $direccion, $telefono, $poblacion, $codigo, $correo, $usuario, $password, $genero, $DB);

    if ($Validacion == TRUE)
    {
        $Existe = compruebaUsuario($correo, $DB);

        if ($Existe == FALSE)
        {
            $Creado = creaUsuario($name, $apellidos, $direccion, $telefono, $poblacion, $codigo, $correo, $usuario, $password, $genero, $DB);

            if($Creado == TRUE)
            {
                //arreglar la redirección a vista_index para que se vea el echo.

                echo "<div id=exitoReg>USUARIO REGISTRADO CON ÉXITO</div>";
                //include(__DIR__.'/../vista_index.php');
                header('Location: ../index.php?accio=getCategories');
            }
            else
            {
                echo "<div id=errorReg>ERROR: USUARIO NO REGISTRADO. VUELVE A INTENTARLO DE NUEVO</div>";
                include(__DIR__.'/../views/vista_registro.php');
            }
        }
        else
        {
            echo "<div id=errorReg>ERROR: ESTE USUARIO YA EXISTE</div>";
            include(__DIR__.'/../views/vista_registro.php');
        }
    }
    else
    {
        echo "<div id=errorReg>ERROR: DATOS NO VÁLIDOS</div>";
        include(__DIR__.'/../views/vista_registro.php');
    }





