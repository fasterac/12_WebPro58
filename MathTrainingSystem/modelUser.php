<?php

include_once './connector.php';

class modelUser {
    private $user_id;
    private $name;
    private $surname;
    private $email;
    private $username;
    private $password;
    private $user_type;
    private $connect;
    
    function __construct($user_id, $name, $surname, $email, $username, $password, $user_type) {
        $this->user_id = $user_id;
        $this->name = $name;
        $this->surname = $surname;
        $this->email = $email;
        $this->username = $username;
        $this->password = $password;
        $this->user_type = $user_type;
        $this->connect = new connector();
    }
    
    public function getUsername() {
        return $this->username;
    }

    public function getPassword() {
        return $this->password;
    }
    
    public function insertNewUser(){
        $this->connect->executeUpdate('insert into user values('. $this->user_id .',\''
                . $this->name .'\',\''. $this->surname .'\',\''. $this->email. '\',\''.
                $this->username.'\',\''. $this->password .'\',\''. $this->user_type . ')') or die("insert error");
    }



}
