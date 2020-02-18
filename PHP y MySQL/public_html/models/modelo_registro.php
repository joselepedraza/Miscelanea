<?php

	function creaUsuario($name, $apellidos, $direccion, $telefono, $poblacion, $codigo, $correo, $usuario, $password, $genero, $DB)
	{
	    $tabla = "USUARIO";
	    $Estado = 1;
	    $Admin = 0;

        $name = htmlentities($name, ENT_QUOTES | ENT_HTML5, 'UTF-8');
        $apellidos = htmlentities($apellidos, ENT_QUOTES | ENT_HTML5, 'UTF-8');
        $direccion = htmlentities($direccion, ENT_QUOTES | ENT_HTML5, 'UTF-8');
        $telefono = htmlentities($telefono, ENT_QUOTES | ENT_HTML5, 'UTF-8');
        $poblacion = htmlentities($poblacion, ENT_QUOTES | ENT_HTML5, 'UTF-8');
        $codigo = htmlentities($codigo, ENT_QUOTES | ENT_HTML5, 'UTF-8');
        $correo = htmlentities($correo, ENT_QUOTES | ENT_HTML5, 'UTF-8');
        $usuario = htmlentities($usuario, ENT_QUOTES | ENT_HTML5, 'UTF-8');
        $password = htmlentities($password, ENT_QUOTES | ENT_HTML5, 'UTF-8');
        $genero = htmlentities($genero, ENT_QUOTES | ENT_HTML5, 'UTF-8');

	    $Hash = password_hash($password, PASSWORD_DEFAULT);

        $sql = $DB->prepare("INSERT INTO $tabla (Nombre, Apellidos, Direccion, Telefono, Poblacion, CP, Mail, Usuario, password, Genero, Estado, Admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        $sql->bind_param('ssssssssssii', $name, $apellidos, $direccion, $telefono, $poblacion, $codigo, $correo, $usuario, $Hash, $genero, $Estado, $Admin);

        if ($sql->execute() == TRUE)
        {
            return true;
        }
        else
        {
            return false;
        }

	}

	function compruebaUsuario($correo, $DB)
    {
        $tabla = "USUARIO";

        $sql = "SELECT ID_U FROM $tabla WHERE Mail='$correo'";
        $resultado = mysqli_query($DB,$sql);
        $i = mysqli_num_rows($resultado);

        if($i== 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    function validarDatos($name, $apellidos, $direccion, $telefono, $poblacion, $codigo, $correo, $usuario, $password, $genero, $DB)
    {
        $valido = true;

        $valido = filter_var($correo, FILTER_VALIDATE_EMAIL);

        if ($valido == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }