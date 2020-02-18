<!DOCTYPE html>
<html>
<div id="Header">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/running.css">
        <title>RUNNING | AS</title>
        <a href="index.php?accio=getCategories"><img id="Logo" src="css/img/Aerodynamic_Sports_Logo.png" width="300" height="100"></a>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <h3>RUNNING</h3>
        <h4><br /></h4>
    </head>
</div>

<div id="Main">
    <body>
    <div id="greybackground">
     

        <div class="contenido"></div>

        <script type="text/javascript">
            $(document).ready(function(){
                $('.contenido').load('controllers/control_get_producto.php', function(){console.log('COMPLETADO');})


                $('img#</?php echo $Productos['nombre']; ?>').click(function(){
                    $('img#</?php echo $Productos['nombre']; ?>').load('controllers/control_get_producto.php');
                });

            });

        </script>

    </div>

    </body>
</div>
</html>