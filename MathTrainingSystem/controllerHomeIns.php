<?php
include_once './modelCourse.php';
include_once './modelUser.php';
include_once './connector.php';
session_start();
$user = new modelUser();
$user = $_SESSION['sesuser'];

    if($_POST['courseInstructor'] !== NULL){
        $course = new modelCourse();
        $course->callCourse($_POST["course"]);
        $_SESSION['sescourse'] = $course;
        header("Location: ./courseInstructor.php");
    }
    elseif($_POST['course'] !== NULL){
        $course = new modelCourse();
        $course->callCourse($_POST["course"]);
        $_SESSION['sescourse'] = $course;
        header("Location: ./course.php");
    }
    if($_POST['subbut'] === 'Logout'){
        session_destroy();
        header("Location: ./login.php");
    }
    else if($_POST['subbut'] === 'Create course'){
        //insert to db
        $course = new modelCourse();
        $course->createNewCourse($_POST['coursename'], $_POST['description'], $user->getUser_id());
        $course->insertNewCourse();
        echo '<form action="controllerHomeIns.php" method="POST">'
        . 'Create course sussessful<br>';
        echo '<input type = "submit" value = "Back to home" name = "subbut" />';
    
    }
    else if($_POST['subbut'] === 'Create new course'){
        $_SESSION['sesuser'] = $user;
        header("Location: ./newCourse.php");
    }
    
    else if($_POST['subbut'] === 'Back to home'){
        header("Location: ./homeInstructor.php");
    }
    else if($_POST['subbut'] === 'all course'){
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
        $connect->close();
    }