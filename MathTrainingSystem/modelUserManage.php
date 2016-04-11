<?php

include_once './modelUser.php';

class modelUserManage {
    private $user_id;
    private $name;
    private $surname;
    private $email;
    private $username;
    private $password;
    private $user_type;
    
    public function setUser($user_id, $name, $surname, $email, $username, $password, $user_type){
        $user = new modelUser($user_id, $name, $surname, $email, $username, $password, $user_type);
    }
}
