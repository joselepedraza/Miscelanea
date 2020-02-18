<?php

    require_once(__DIR__.'/../models/mysql_conexion.php');
    require_once(__DIR__.'/../models/modelo_get_productos.php');

    $DB = conecta();

    $ID_Producto = $_GET['ID_Prod'];
    $ID_Categoria = $_GET['ID_Cat'];

    $Producto = getProducto($DB, $ID_Producto);

if ($ID_Categoria == 1)
{
    include(__DIR__.'/../views/vista_producto_running.php');
}

if ($ID_Categoria == 2)
{
    include(__DIR__.'/../views/vista_producto_escalada.php');
}

if ($ID_Categoria == 3)
{
    include(__DIR__.'/../views/vista_producto_natacion.php');
}