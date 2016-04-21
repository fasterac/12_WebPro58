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
elseif($_POST['subbut'] === 'Create Content'){
    header("Location: ./createContent.php");
}
elseif($_POST['subbut'] === 'Create Exam'){
    header("Location: ./login.php");
}
elseif($_POST['subbut'] === 'Back to Home'){
    header("Location: ./homeInstructor.php");

    
}

