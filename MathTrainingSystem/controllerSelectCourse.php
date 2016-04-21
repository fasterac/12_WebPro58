<?php

include_once './modelUser.php';
include_once './modelCourse.php';
include_once './modelParticipant.php';

session_start();

$user = new modelUser();
$user = $_SESSION['sesuser'];
$parti = new modelParticipant();

    if($_POST['subbut'] === 'Logout'){
        session_destroy();
        header("Location: ./login.php");
    }
    elseif($_POST['subbut'] === 'Confirm Selection'){
        header("Location: ./home.php");
    }
    elseif($_POST['subbut'] === 'Back to Home'){
        header("Location: ./home.php");
    }
    
$connect = new connector();
$result = $connect->executeQuery("SELECT * FROM course;");
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $num = $row["course_id"];
        if($_POST[$row["course_id"]] === 'selected'){
            if(!inCourse($row["course_id"], $user->getUser_id())){
                $parti->createNewParticipant($user->getUser_id(), $row["course_id"]);
                $parti->insertNewParticipant();
            }
        }
        elseif($_POST[$row["course_id"]] !== 'selected'){
            if(inCourse($row["course_id"], $user->getUser_id())){
                $parti->deleteParticipation($user->getUser_id(), $row["course_id"]);
            }
        }
    }
}
$connect->close();


function inCourse($course_id, $user_id){
    $connect = new connector();
    $rs = $connect->executeQuery("SELECT course_id FROM participant where student_id = '". $user_id .'\';');
    if ($rs->num_rows > 0) {
        while($row = $rs->fetch_assoc()) {                
            if($row["course_id"] === $course_id){
                $connect->close();
                return TRUE ;
            }
        }
    }
    $connect->close();
    return FALSE;
}


