<!DOCTYPE html>
<html>
<div id="Header">
	<head>
		<meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/registro.css">
        <title>REGISTRO | AS</title>
        <a href="../index.php?accio=getCategories"><img id="Logo" src="../css/img/Aerodynamic_Sports_Logo.png" width="300" height="100"></a>
		<h3>REGISTRO</h3>
        <h4><br /></h4>
	</head>
</div>

<div id="Registro" >
	<body>
        <form method="post" action="../controllers/control_registro.php" autocomplete="on">
            <input placeholder="Nombre" type="text" name="name" pattern="[a-zA-Z������????����??����??����������??��??��?��������?????��������???����������??��??��ǌ�?��?� ]{2,30}" required /><br /><br />
            <input placeholder="Apellidos" type="text" name="apellidos" pattern="[a-zA-Z������????����??����??����������??��??��?��������?????��������???����������??��??��ǌ�?��?� ]{2,30}" required /><br /><br />
            <input placeholder="Direccion" type="text" name="direccion" pattern="[a-zA-Z0-9������????����??����??����������??��??��?��������?????��������???����������??��??��ǌ�?��?� ]{2,30}" required /><br /><br />
            <input placeholder="Telefono" type="text" name="telefono" pattern="[0-9]{9}" /><br /><br />
            <input placeholder="Poblacion" type="text" name="poblacion" pattern="[a-zA-Z������????����??����??����������??��??��?��������?????��������???����������??��??��ǌ�?��?� ]{2,30}" required /><br /><br />
            <input placeholder="Codigo Postal" type="text" pattern="[0-9]{5}" name="codigo" required /><br /><br />
            <input placeholder="E-Mail" type="email" name="correo" pattern="^[a-zA-Z0-9.!#$%&�*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" required /><br /><br />
            <input placeholder="Nombre de Usuario" type="text" name="usuario" pattern="[a-zA-Z0-9������????����??����??����������??��??��?��������?????��������???����������??��??��ǌ�?��?� ]{5,20}" required /><br /><br />
            <input placeholder="Contrase�a" type="password" name="password" pattern="[a-zA-Z0-9������????����??����??����������??��??��?��������?????��������???����������??��??��ǌ�?��?� ]{5,20}" required /><br /><br />
            <label>Genero</label><br /><br />
            <p id="p1">Mujer</p> <input type="radio" id="r1" name="genero" value="M" />
            <p id="p2">Hombre</p> <input type="radio" id="r2" name="genero" value="H" /><br /><br />
            <input type="submit" value="Enviar formulario" />
            <input type="reset" value="Borrar formulario">
        </form>
	</body>
</div>

</html>