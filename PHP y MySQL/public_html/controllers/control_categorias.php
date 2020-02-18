<?php


        require_once(__DIR__.'/../models/mysql_conexion.php');
        require_once(__DIR__.'/../models/modelo_categorias.php');

        $DB = conecta();
        $Categorias = muestraCategoria($DB);

        include(__DIR__.'/../vista_index.php');

?>
