<?php

    function hacerLogin($correo, $password, $DB)
    {
        $tabla = "USUARIO";

        $correo = htmlentities($correo, ENT_QUOTES | ENT_HTML5, 'UTF-8');

        $sql = "SELECT Password FROM $tabla WHERE Mail='$correo'";
        $resultado = mysqli_query($DB,$sql);

        $passHash = $resultado->fetch_array(MYSQLI_ASSOC);

        if (password_verify($password, $passHash['Password']))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    function tipoUsuario($correo, $DB)
    {
        $tabla = "USUARIO";

        $correo = htmlentities($correo, ENT_QUOTES | ENT_HTML5, 'UTF-8');

        $sql = "SELECT Admin FROM $tabla WHERE Mail='$correo'";
        $resultado = mysqli_query($DB,$sql);

        $Admin = $resultado->fetch_array(MYSQLI_ASSOC);

        if ($Admin['Admin'] == TRUE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    function getID($correo, $DB)
    {
        $tabla = "USUARIO";

        $sql = "SELECT ID_U FROM $tabla WHERE Mail='$correo'";
        $resultado = mysqli_query($DB,$sql);

        $ID = $resultado->fetch_array(MYSQLI_ASSOC);

        return $ID['ID_U'];
    }