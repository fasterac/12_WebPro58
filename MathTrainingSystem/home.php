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
        echo '<form action="controllerHome.php" method="POST">';
        echo  $user->getName(). $user->getSurname().'<input type="submit" value="Logout" name="subbut" />'
                . '<br><input type="submit" value="Select new course" name="subbut" /><br>';
        
        
        
        //call all parti course
        $connect = new connector();
        $result = $connect->executeQuery("SELECT course_id FROM participant where student_id = '". $user->getUser_id() .'\';');
        echo '<table><tbody>';
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {                
                echo '<tr>
                        <td><input type="submit" value="'.$row['course_id'] .'" /><br></td>
                  </tr>';
            }
        }
        $connect->close();
        
        
        echo '<input type="submit" value="see score" name="subbut" /><br>          
            </form>';
        ?>
    </body>
</html>
