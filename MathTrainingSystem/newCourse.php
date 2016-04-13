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
        session_start();
        $userid = $_SESSION['userid'];
        echo '<form action="controllerHomeIns.php" method="POST">'.
            '<h2>'. $userid . 'Create New Course </h2>'.
            '<input type="submit" value="Logout" name="subbut" />
            Course Name:<input type="text" name="coursename" /><br>
            Description: <textarea name="description" rows="2" cols="60"></textarea><br>
            <input type="submit" value="Create course" name="subbut" />
        </form>';
        $_SESSION['userid'] = $userid;
        ?>
    </body>
</html>
