<!DOCTYPE html>
<html>

<div id="Header">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/index.css">
        <title>AERODYNAMIC SPORTS</title>
        <a href="../views/vista_login_true.php"><img id="Logo" src="../css/img/Aerodynamic_Sports_Logo.png" width="300" height="100"></a>

    </head>
</div>

<div id="Nav">
    <a id="CerrarSesion" href="../index.php"><p id="CerrarSesion">CERRAR SESIÓN</p></a>
    <a id="Carrito" href="../views/vista_carrito.php"><p id="Carrito">CARRITO</p></a>
</div>

<div id="Main">
    <body>
    <?php
    require("../controllers/control_categorias.php");
    ?>
    </body>
</div>

<div id="Footer">

</div>

</html>