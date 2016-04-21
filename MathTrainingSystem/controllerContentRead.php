<?php

include_once './modelUser.php';
include_once './modelCourse.php';
include_once './connector.php';
session_start();

$user = new modelUser();
$user = $_SESSION['sesuser'];
$course = new modelCourse();
$course = $_SESSION['sescourse'];

if($_POST['subbut'] === 'Logout'){
    session_destroy();
    header("Location: ./login.php");
}
elseif($_POST['subbut'] === 'Back to Home'){
    if($user->getUser_type() === "instructor"){
        header("Location: ./homeInstructor.php");
    }
    else {
        header("Location: ./home.php");
    }
    
}
//----------
elseif($_POST['subbut'] === 'Read Content'){
    header("Location: ./login.php");
}

elseif($_POST['subbut'] === 'Take Exam'){
    header("Location: ./login.php");
}
