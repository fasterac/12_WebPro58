<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
        echo '<form action="controllerRegister.php" method="POST">'
            . '<h2> Register Page </h2>'
                . 'Username: <input type="text" name="username" size="20" /> <input type="submit" value="Check" name="subbutton"/> <br>'
                . 'Password: <input type="text" name="password" size="20" /> <br>'
                . 'First name: <input type="text" name="name" size="20" /> <br>'
                . 'Last name: <input type="text" name="lastname" size="20" /> <br>'
                . 'E-mail: <input type="text" name="email" size="20" /> <br>'
                . '<input type="checkbox" name="accept" /> I accept something <br>
            <input type="submit" value="Register" name="subbutton" />
            </form>';
        ?>
    </body>
</html>
