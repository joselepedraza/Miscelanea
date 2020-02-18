<?php

function muestraCategoria($DB)
{
    $categorias = NULL;

    $sql = "SELECT imagen, nombre FROM CATEGORIA";

    if ($resultado = mysqli_query($DB,$sql)) 
    {
        $categorias = $resultado->fetch_all(MYSQLI_ASSOC);
    }

    return $categorias;
    
}