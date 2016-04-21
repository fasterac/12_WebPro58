<?php
include_once './modelUser.php';
include_once './modelCourse.php';
session_start();

    if ($_POST['course'] !== NULL){
        $course = new modelCourse();
        $course->callCourse($_POST["course"]);
        $_SESSION['sescourse'] = $course;
        header("Location: ./course.php");
    }
    elseif($_POST['subbut'] === 'Logout'){
        session_destroy();
        header("Location: ./login.php");
    }
    
    elseif ($_POST['subbut'] === 'Select new course'){
        header("Location: ./selectCourse.php");
    }
