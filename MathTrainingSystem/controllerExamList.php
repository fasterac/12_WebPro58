<?php

include_once './modelUser.php';
include_once './modelCourse.php';
include_once './modelExamSet.php';

session_start();

$user = new modelUser();
$user = $_SESSION['sesuser'];
$course = new modelCourse();
$course = $_SESSION['ses$course'];

    if($_POST['exsetbut'] !== NULL){
        $exset = new modelExamSet();
        $exset->callExamSet($_POST['exsetbut']);
        $_SESSION["sesexset"] = $exset;
        header("Location: ./examDo.php");
    }
    elseif($_POST['subbut'] === 'Logout'){
        session_destroy();
        header("Location: ./login.php");
    }
    elseif($_POST['subbut'] === 'Confirm Selection'){
        header("Location: ./home.php");
    }
  

