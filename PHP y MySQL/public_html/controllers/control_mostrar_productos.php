<?php

require_once(__DIR__.'/../models/mysql_conexion.php');
require_once(__DIR__.'/../models/modelo_secciones.php');

$ID_Categoria = $_GET['ID_Cat'];

$DB = conecta();

$Productos = muestra($DB, $ID_Categoria);

if ($ID_Categoria == 1)
{
    include(__DIR__.'/../views/vista_running.php');
}

if ($ID_Categoria == 2)
{
    include(__DIR__.'/../views/vista_escalada.php');
}

if ($ID_Categoria == 3)
{
    include(__DIR__.'/../views/vista_natacion.php');
}
