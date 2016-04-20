<?php
require_once './modelUser.php';
session_start();

    echo 'controllerlogin';

    if($_POST['subbutton'] === 'Register'){
        header("Location: ./register.php");
    }
    elseif ($_POST['subbutton'] === 'Login') {
        $user = new modelUser();
        $username = $_POST['username'];
        $password = $_POST['password'];
        $checkpass = $user->checkPassword($username);
        if ($checkpass === $password){
            $userid = $user->checkUser_id($username);
            echo $userid;
            $user->callUser($userid);
            $_SESSION['sesuser'] = $user;
            $role = $user->getUser_type();
            if($role === 'student') {                
                header("Location: ./home.php");
            }
            elseif ($role === 'instructor') {
                header("Location: ./homeInstructor.php");
            }
            elseif ($role === 'admin') {
                header("Location: ./homeAdmin.php");
            }
        }
        else{
            echo 'Wrong password' . $checkpass;
        
        }
    }