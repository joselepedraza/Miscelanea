<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <title>AERODYNAMIC SPORTS</title>
        <a href="index.php?accio=getCategories"><img id="Logo" src="css/img/Aerodynamic_Sports_Logo.png" width="300" height="100"></a>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    </head>
<body>
<div id="Nav">
    <a id="Reg" href="views/vista_registro.php"><p id="Reg">REGISTRO</p></a>
    <a id="Log" href="views/vista_login.php"><p id="Log">LOGIN</p></a>
</div>

<div id="background">
    <div id="test">
        <?php
        foreach ($Categorias as $categoria):

                switch ($categoria['imagen']){

                    case'Running.jpg':
                    echo '<a id="running" href="index.php?accio=getProductos&ID_Cat=1">';
                    break;

                    case'Escalada.jpg':
                    echo '<a id="escalada" href="index.php?accio=getProductos&ID_Cat=2">';
                    break;

                    case 'Natacion.jpg':
                    echo '<a id="natacion" href="#">';
                    break;
                }
                ?>
           
            <img id="<?php echo $categoria['nombre']; ?>" src="Pictures/<?php echo $categoria['imagen']; ?>" height="420" width="420">
            </a>
           
        <?php
        endforeach;
        ?>  
    </div>
<div class="prueba"> </div>


<script>
$(document).ready(function(){
    <?php
    foreach ($Categorias as $categoria):
    ?>

   $("#natacion").click(function (e){ // click sobre imagen de la categoria a cargar
       $.post ('index.php?accio=getProductos&ID_Cat=3', function(responde){ // carga de pagina recibida
           $("#test").html(responde);
           $("#background").css("background-image", "url(css/img/natacion_background.jpg)");
       });
   });

    <?php
    endforeach;
    ?>  

});
</script>
</body>
</div>

<div id="Footer">

</div>

</html>