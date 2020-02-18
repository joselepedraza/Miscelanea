<!DOCTYPE html>
<html>
<div id="Header">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/login.css">
        <title>LOGIN | AS</title>
        <a href="../index.php?accio=getCategories"><img id="Logo" src="../css/img/Aerodynamic_Sports_Logo.png" width="300" height="100"></a>
        <h3>LOGIN</h3>
        <h4><br /></h4>
    </head>
</div>

<div id="Main">
    <body>
        <form method="post" action="../controllers/control_login.php" autocomplete="on">
                <input placeholder="E-Mail" type="email" name="correo" pattern="^[a-zA-Z0-9.!#$%&�*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" required /><br /><br />
                <input placeholder="Contrase�a" type="password" name="password" pattern="[a-zA-Z0-9������acce����ei����ln����������uu��zz��c��������ACCEE��������ILN����������UU��ZZ��ǌ�C��?� ]{5,20}" required /><br /><br />

                <input type="submit" value="Enviar formulario" />
                <input type="reset" value="Borrar formulario">
        </form>
    </body>
</div>
</html>