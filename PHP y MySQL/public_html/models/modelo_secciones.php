<?php

function muestra($DB, $ID_Cat)
{

    $items = NULL;

    $sql = "SELECT Descripcion_corta, Descripcion_larga, Precio, Imagen, ID_P FROM PRODUCTO WHERE ID_Categoria = $ID_Cat AND Activo = 1";

    if ($resultado = mysqli_query($DB,$sql))
    {
        $items = $resultado->fetch_all(MYSQLI_ASSOC);
    }

    return $items;

}

