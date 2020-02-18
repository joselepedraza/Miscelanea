<?php

    require_once("../models/mysql_conexion.php");
    require_once("../models/modelo_login.php");

    $correo = $_POST['correo'];
    $password = $_POST['password'];


    $DB = conecta();
    $Log = hacerLogin($correo, $password, $DB);

    if($Log == TRUE)
    {
        $Admin = tipoUsuario($correo, $DB);

        if ($Admin == TRUE)
        {
            session_start();
            $_SESSION["ID_USER"] = getID($correo, $DB);
            include(__DIR__.'/../views/vista_admin.php');
        }
        else
        {
            //header('Location: ../views/vista_login_true.php');
            session_start();
            $_SESSION["ID_USER"] = getID($correo, $DB);
            include(__DIR__.'/../index.php?accio=getCategories');

        }

    }
    else
    {
        echo "<div id=errorReg>ERROR: MAIL Y/O CONTRASEÑA INCORRECTOS. INTÉNTALO DE NUEVO</div>";
        include(__DIR__.'/../views/vista_login.php');
    }








