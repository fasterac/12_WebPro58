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
        $course = $_SESSION['ses$course'];
        
        
        echo '<form action="controllerExamList.php" method="POST">';
        echo 'Welcome'.$user->getName().' '. $user->getSurname().'<input type="submit" value="Logout" name="subbut" /><br>';
        
        //list all exset that in this course
        $connect = new connector();
        $result = $connect->executeQuery("SELECT * FROM examset WHERE course_id = \'" . $course->getCourse_id() . "\';");
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {                
                echo '<input type = "submit" value = "'.$row['exset_id'].'" name = "exsetbut">'.$row['exset_name'].'</input>';
                echo '';
            }
        }
        $connect->close();
        
        
        
        echo '</form>';
        ?>
    </body>
</html>
