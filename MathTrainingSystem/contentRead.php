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
        include_once './modelUser.php';
        include_once './modelCourse.php';
        include_once './connector.php';
        session_start();
        
        
        $user = new modelUser();
        $user = $_SESSION['sesuser'];
        $course = new modelCourse();
        $course = $_SESSION['sescourse'];
        $c = new modelContent();
        
        echo '<form action="controllerContentRead.php" method="POST">';
        echo 'Welcome '.$user->getName().' '. $user->getSurname().'<input type="submit" value="Logout" name="subbut" /><br>'
                . '<input type="submit" value="Back to Home" name="subbut" /><br>';
        ?>
    </body>
</html>
