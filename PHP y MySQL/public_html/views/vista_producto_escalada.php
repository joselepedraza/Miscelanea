<!DOCTYPE html>
<html>
<div id="Header">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/escalada.css">
        <title>ESCALADA | AS</title>
        <a href="index.php?accio=getCategories"><img id="Logo" src="css/img/Aerodynamic_Sports_Logo.png" width="300" height="100"></a>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <h3>ESCALADA</h3>
        <h4><br /></h4>
    </head>
</div>

<div id="Main">
    <body>
    <div id="greybackground">

        <img src="Pictures/Escalada/<?php echo $Producto['Imagen']; ?>" height="220" width="220">
        <p><?php echo $Producto['Descripcion_corta']; ?></p>
        <p><?php echo $Producto['Descripcion_larga']; ?></p>
        <p><?php echo $Producto['Precio']; ?> Euros</p>

        <div id="cantidadProducto">Cantidad: <input id="cantidadProducto" type="number"  min="1" max="10" /></div>
        <a href="<?php include(__DIR__.'/../controllers/control_carrito.php'); ?>">Añadir al Carrito</a>

    </div>

    </body>
</div>
</html>