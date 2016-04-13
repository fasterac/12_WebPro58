<?php
include_once './modelCourse.php';
include_once './connector.php';
session_start();
$userid = $_SESSION['userid'];

    if($_POST['subbut'] === 'Logout'){
        header("Location: ./login.php");
    }
    else if($_POST['subbut'] === 'Create course'){
        //insert to db
        $course = new modelCourse();
        $course->createNewCourse('NULL', $_POST['coursename'], $_POST['description'], NULL, $userid);
        $course->insertNewCourse();
        echo '<form action="controllerHomeIns.php" method="POST">'
        . 'Create course sussessful<br>';
        echo '<input type = "submit" value = "Back to home" name = "subbut" />';
    
    }
    else if($_POST['subbut'] === 'Create new course'){
        $_SESSION['userid'] = $userid;
        header("Location: ./newCourse.php");
    }
    
    else if($_POST['subbut'] === 'Back to home'){
        $_SESSION['userid'] = $userid;
        header("Location: ./homeInstructor.php");
    }
    
    else if($_POST['subbut'] === 'all course'){
        $_SESSION['userid'] = $userid;
        countcouse();
        
        //header("Location: ./homeInstructor.php");
    }
    
    
    
    function countcouse(){
        $connect = new connector();
        $result = $connect->executeQuery("SELECT * FROM course");
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {                
                echo $row['course_name'] . '<br>';
            }
        }
    }