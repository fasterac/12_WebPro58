<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        
        <?php
        session_start();
        $userid = $_SESSION['userid'];
        $_SESSION['userid'] = $userid;
        
        include_once './modelCourse.php';
        include_once './connector.php';
        
        $connect = new connector();
        $result = $connect->executeQuery("SELECT course_id FROM paticipant where student_id != '". $userid .'\';');
        echo '<table><tbody>';
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {                
                echo '<tr>
                        <td><input type="submit" value="'.$row['course_id'] .'" /><br></td>
                  </tr>';
            }
        }
        $connect->close();
        ?>
    </body>
</html>
