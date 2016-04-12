<?php
require_once './modelUser.php';

    echo 'controllerlogin';

    if($_POST['subbutton'] === 'Register'){
        header("Location: ./register.php");
    }
    elseif ($_POST['subbutton'] === 'Login') {
        $user = new modelUser();
        $username = $_POST['username'];
        $password = $_POST['password'];
        $checkpass = $user->getPassword($username);
        if ($checkpass === $password){
            $role = $user->getUser_type($username);
            if($role === 'student') {
                header("Location: ./home.php");
            }
            elseif ($role === 'instructor') {
                header("Location: ./homeInstructor.php");
            }
        }
        else{
            echo 'Wrong password' . $checkpass;
        
        }
    }