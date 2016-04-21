<?php
include_once './modelUser.php';
include_once './modelCourse.php';
include_once './modelContent.php';
include_once './connector.php';
session_start();

$user = new modelUser();
$user = $_SESSION['sesuser'];
$course = new modelCourse();
$course = $_SESSION['sescourse'];
$content = new modelContent();

if($_POST['subbut'] === 'Logout'){
    session_destroy();
    header("Location: ./login.php");
}
elseif($_POST['subbut'] === 'Create content'){
    $content->createNewContent($_POST['contentName'], $_POST['editor1'], $course->getCourse_id());
    $content->insertNewContent();
    //header("Location: ./createContent.php");
    echo $_POST['contentName'].'<br>';
    echo $_POST['editor1'];
}
elseif($_POST['subbut'] === 'Back to Home'){
    header("Location: ./homeInstructor.php");
}



