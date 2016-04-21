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
        
        
        echo '<form action="controllerSelectCourse.php" method="POST">';
        echo 'Welcome'.$user->getName().' '. $user->getSurname().'<input type="submit" value="Logout" name="subbut" /><br>'
                . '<input type="submit" value="Back to Home" name="subbut" /><br><br>';
        
        $connect = new connector();
        $result = $connect->executeQuery("SELECT * FROM course;");
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {                
                echo '<input type="checkbox" name="'.$row["course_id"].'" value="selected" '
                        . inCourse($row["course_id"], $user->getUser_id());
                echo '> Course: '. $row["course_name"]. ' Teach by: '. $row["instructor_id"]. '</input><br>';
            }
        }
        $connect->close();
        echo '<br><input type="submit" value="Confirm Selection" name="subbut" />'
        . '</form>';
        
        //chece is user has been in this course
        function inCourse($course_id, $user_id){
            $connect = new connector();
            $rs = $connect->executeQuery("SELECT course_id FROM participant where student_id = '". $user_id .'\';');
            if ($rs->num_rows > 0) {
                while($row = $rs->fetch_assoc()) {                
                    if($row["course_id"] === $course_id){
                        $connect->close();
                        return 'checked="checked"' ;
                    }
                }
            }
            $connect->close();
            return " ";
        }
        ?>
    </body>
</html>