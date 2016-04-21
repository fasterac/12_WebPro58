<!DOCTYPE html>
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
        
        echo '<h2>Editor mode</h2>';
        echo '<form action="controllerCourseIns.php" method="POST">';
        echo 'Welcome '.$user->getName().' '. $user->getSurname().'<input type="submit" value="Logout" name="subbut" /><br>';
        echo '<input type="submit" value="Back to Home" name="subbut" /><br>';
        
        echo '<input type="submit" value="Create Content" name="subbut" /><br>'
        . '<input type="submit" value="Create Exam" name="subbut" /><br>'
                . '</form>';
        ?>
    </body>
</html>
