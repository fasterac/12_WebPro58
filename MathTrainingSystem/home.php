<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <h1> User Student Home Page </h1>
        <?php
        include_once './modelUser.php';
        include_once './modelCourse.php';
        include_once './connector.php';
        session_start();
        
        
        $user = new modelUser();
        $user = $_SESSION['sesuser'];
        $course = new modelCourse();
        
        //header
        echo '<form action="controllerHome.php" method="POST">';
        echo 'Welcome '.$user->getName().' '. $user->getSurname().'<input type="submit" value="Logout" name="subbut" /><br>'
                . '<br><input type="submit" value="Select new course" name="subbut" /><br>';
        
        
        
        //call all parti course
        $connect = new connector();
        $result = $connect->executeQuery("SELECT course_id FROM participant where student_id = '". $user->getUser_id() .'\';');
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $course->callCourse($row['course_id']);
                echo '<input type="submit" name="course" value="'.$row['course_id'] .'" >'.$course->getCourse_name().'</input><br>
                            '.$course->getCourse_description().'<br>'
                        . '&nbsp; Teacher: '. $course->getInstructor_id().'<br><br>';
            }
        }
        $connect->close();
        
        
        echo '<input type="submit" value="see score" name="subbut" /><br>          
            </form>';
        ?>
    </body>
</html>
