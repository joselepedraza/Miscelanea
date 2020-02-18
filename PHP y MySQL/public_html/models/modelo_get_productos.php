<?php

    function getProducto($DB, $ID_Producto)
    {
        $item = NULL;

        $sql = "SELECT Descripcion_corta, Descripcion_larga, Precio, Imagen FROM PRODUCTO WHERE ID_P = $ID_Producto";

        if ($resultado = mysqli_query($DB,$sql))
        {
            $item = $resultado->fetch_array(MYSQLI_ASSOC);
        }

        return $item;
    }