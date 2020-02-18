<body>
    <div id="greybackground">
        <?php
        foreach ($Productos as $producto):
            ?>

            <a href="<?php include __DIR__.'/../index.php?accio=getProducte' ?>index.php?accio=getProducte&ID_Prod=<?php echo $producto['ID_P']; ?>&ID_Cat=3"><img id="<?php echo $producto['nombre']; ?>" src="Pictures/Natacion/<?php echo $producto['Imagen']; ?>" height="220" width="220"></a>
            <p><?php echo $producto['Descripcion_corta']; ?></p>
            <p><?php echo $producto['Precio']; ?> Euros</p>
            <?php
        endforeach;
        ?>
    </div>
</body>
