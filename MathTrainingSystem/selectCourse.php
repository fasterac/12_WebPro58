<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        
        <?php
        include_once './modelUser.php';
        session_start();
        $user = new modelUser();
        $user = $_SESSION['sesuser'];
        
        include_once './modelCourse.php';
        include_once './connector.php';
        
        echo '<form action="controllerSelectCourse.php" method="POST">';
        echo $user->getName(). $user->getSurname().'<input type="submit" value="Logout" name="subbut" /><br>';
        
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
        echo '<input type="submit" value="Confirm Selection" name="subbut" />'
        . '</form>';
        
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