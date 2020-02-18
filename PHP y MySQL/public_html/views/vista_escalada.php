<!DOCTYPE html>
<html>
<div id="Header">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/escalada.css">
        <title>ESCALADA | AS</title>
        <a href="index.php?accio=getCategories"><img id="Logo" src="css/img/Aerodynamic_Sports_Logo.png" width="300" height="100"></a>
        <h3>ESCALADA</h3>
        <h4><br /></h4>
    </head>
</div>

<div id="Main">
    <body>
    <div id="greybackground">

        <?php
        foreach ($Productos as $producto):
            ?>

            <a href="<?php include __DIR__.'/../index.php?accio=getProducte' ?>index.php?accio=getProducte&ID_Prod=<?php echo $producto['ID_P']; ?>&ID_Cat=2"><img id="<?php echo $producto['nombre']; ?>" src="Pictures/Escalada/<?php echo $producto['Imagen']; ?>" height="220" width="220"></a>
            <p><?php echo $producto['Descripcion_corta']; ?></p>
            <p><?php echo $producto['Precio']; ?> Euros</p>

            <?php
        endforeach;
        ?>
    </div>

    </body>
</div>
</html>