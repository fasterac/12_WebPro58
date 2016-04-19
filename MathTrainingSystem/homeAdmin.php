<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
        echo '<h1>Admin main page</h1>
            <form action="controllerHomeAdmin.php" method="POST">
                <input type="submit" value="Delete account" name="subbut" /> <br>
                <input type="submit" value="Grant Access" name="subbut" /> <br>
                <input type="submit" value="Logout" name="subbut" />
            </form>';
        ?>
    </body>
</html>
