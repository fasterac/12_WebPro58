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
        echo '<form action="controllerHomeIns.php" method="POST">';
        echo '<input type="submit" value="Create new course" name="subbut" /><br>';
        
        //echo all course
        $connect = new connector();
        $result = $connect->executeQuery("SELECT * FROM course");
        echo '<table><tbody>';
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {                
                echo '<tr>
                        <td><input type="submit" value="'.$row['course_name'] .'" /><br></td>
                  </tr>';
            }
        }
        $connect->close();
        

        echo '</tbody></table><br>';
        
        
        echo '<input type="submit" value="Logout" name="subbut" />';
        ?>
    </body>
</html>
