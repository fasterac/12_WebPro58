<?php

include_once './modelUser.php';

    echo'<h2> Controller Register page <h2>';
    
    if($_POST['subbutton'] === 'Check'){
        echo 'check';
    }
    elseif ($_POST['subbutton'] === 'Register') {
        echo 'Register';
        $newUser = new modelUser(06, $_POST['name'], $_POST['lastname'], $_POST['email'], 
                $_POST['username'], $_POST['password'], 'student');
        $newUser->insertNewUser();
    }

//    function echoer($word){
//        return $word.'5555';
//    }




