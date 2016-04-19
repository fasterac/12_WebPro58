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
        $checkpass = $user->getPassword($username);
        if ($checkpass === $password){
            $userid = $user->getUser_id($username);
            $role = $user->getUser_type($username);
            $_SESSION['userid'] = $userid;
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