<?php

    $accio = $_GET['accio'];

    switch ($accio)
    {
	/*
        case 'getCategories':
            include __DIR__.'/controllers/control_categorias.php';
            break;
	*/

        case 'getProductos':
            include __DIR__.'/controllers/control_mostrar_productos.php';
            break;

        case 'getProducte':
            include __DIR__.'/controllers/control_get_producto.php';
            break;
            
        case 'getCategories':
            default:
                //include __DIR__.'/vista_index.php';
            include __DIR__.'/controllers/control_categorias.php';
                break;
    }
