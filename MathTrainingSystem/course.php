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
        
        
        echo '<form action="controllerCourse.php" method="POST">';
        echo 'Welcome '.$user->getName().' '. $user->getSurname().'<input type="submit" value="Logout" name="subbut" /><br>'
                . '<input type="submit" value="Back to Home" name="subbut" /><br>';
        echo '<h3>Course '. $course->getCourse_name() .'</h3>';
        echo '<h4>'. $course->getCourse_description().'</h4><br>';
        echo '------------<Content>-------------';
        //echo '<input type="submit" value="Read Content" name="subbut" /><br>';
        $connect = new connector();
        $result = $connect->executeQuery('SELECT content_id, content_name FROM content WHERE course_id =\''. $course->getCourse_id(). '\';');        
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {                
                echo '<input type="submit" name="contentlist" value="'.$row['content_id'] .'" >'.$row['content_id'].'</input><br>';
            }
        }
        $connect->close();
        echo '------------<Exam>-------------'
        . '<input type="submit" value="Take Exam" name="subbut" />'
                . '</form>';
        ?>
    </body>
</html>
