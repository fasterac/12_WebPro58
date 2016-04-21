<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <h1> User Instructor Home Page  </h1>
        <?php
        include_once './modelUser.php';
        include_once './modelCourse.php';
        include_once './connector.php';
        session_start();
        
        $user = new modelUser();
        $user = $_SESSION['sesuser'];
        
//        psudofilter
//        if($_SESSION === null or $user == NULL or $user->getUser_type()==NULL){
//            session_destroy();
//            header("Location: ./login.php");
//        }
        
        echo '<form action="controllerHomeIns.php" method="POST">';
        echo 'Welcome'.$user->getName().' '. $user->getSurname().'<input type="submit" value="Logout" name="subbut" /><br>';
        echo '<input type="submit" value="Create new course" name="subbut" /><br>';
        
        echo '-----------------your course---------------------<br>';
        //-~up course that this teacher created
        $connect = new connector();
        $result = $connect->executeQuery('SELECT * FROM course WHERE instructor_id =\''. $user->getUser_id(). '\';');        
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {                
                echo '<input type="submit" name="courseInstructor" value="'.$row['course_name'] .'" /><br>';
            }
        }
        $connect->close();
        
        echo '-----------------other course---------------------<br>';
        //echo all course
        $connect = new connector();
        $result = $connect->executeQuery('SELECT * FROM course WHERE instructor_id !=\''. $user->getUser_id(). '\';');
        
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {                
                echo '<input type="submit" name="course" value="'.$row['course_name'] .'" /><br>';
            }
        }
        
$connect->close();
        echo '<br></form>';
        
        
        ?>
    </body>
</html>
