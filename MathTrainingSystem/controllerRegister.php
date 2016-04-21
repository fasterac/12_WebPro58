<?php

include_once './modelUser.php';

    echo'<h2> Controller Register page <h2>';
    
    if($_POST['subbutton'] === 'Check'){
        echo 'check';
    }
    elseif ($_POST['subbutton'] === 'Register') {
        echo 'Register';
        $newUser = new modelUser();
        $newUser->createNewUser('NULL', $_POST['name'], $_POST['lastname'], $_POST['email']
                ,$_POST['username'], $_POST['password'], 'student');
        $newUser->insertNewUser();
        header("Location: ./registerSuccessful.php");
    }
    elseif($_POST['subbutton'] === 'Back to login page'){
        header("Location: ./login.php");
    }

//    function echoer($word){
//        return $word.'5555';
//    }




