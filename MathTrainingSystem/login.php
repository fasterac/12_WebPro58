<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
        echo '<form action="controllerLogin.php" method="POST">'
        . '<h1> ระบบฝึกทักษะทางคณิตศาสตร์: Math Training System </h1> <br>'
        . 'Username: <input type="text" name="username" value="" size="20" /> <br>'
        . 'Passwword: <input type="password" name="password" value="" size="20"/> <br>'
        . '<input type="submit" value="Login" name="subbutton" /> <input type="submit" value="Register" name="subbutton"/>'
        . '</form>';
        ?>
    </body>
</html>
