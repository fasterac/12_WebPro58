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
    
    function __construct() { 
    }
    
    public function createNewUser($user_id, $name, $surname, $email, $username, $password, $user_type){
        $this->user_id = $user_id;
        $this->name = $name;
        $this->surname = $surname;
        $this->email = $email;
        $this->username = $username;
        $this->password = $password;
        $this->user_type = $user_type;
    }
    
    function callUser($user_id){
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT * FROM course WHERE course_id = \''. $user_id .'\';');
        if ($result->num_rows > 0){
            $this->user_id = user_id;
            $this->name = $result->fetch_assoc()['name'];
            $this->surname = $result->fetch_assoc()['surname'];
            $this->email = $result->fetch_assoc()['email'];
            $this->username = $result->fetch_assoc()['username'];
            $this->password = $result->fetch_assoc()['password'];
            $this->user_type = $result->fetch_assoc()['user_type'];

        }
    }

    public function getPassword($username) {
        $this->connect = new connector();
        $returner = 'null';
        $result = $this->connect->executeQuery('SELECT password FROM user WHERE username = \''. $username .'\'');
        if ($result->num_rows > 0) {
            $returner = $result->fetch_assoc()["password"];
        }
        $this->connect->close();
        return $returner."";
    }
        
    function getUser_type($username) {
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT user_type FROM user WHERE username = \''. $username .'\'');
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $returnaer = $row["user_type"].'';
            }
        }        
        $this->connect->close();
        return $returnaer;
    }

        
    public function insertNewUser(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO user (user_id, firstname, lastname, email, username, password, user_type)'
                . ' VALUES ( NULL, \''
                . $this->name .'\',\''. $this->surname .'\',\''. $this->email. '\',\''.
                $this->username.'\',\''. $this->password .'\',\''. $this->user_type . '\')');
        $this->connect->close();
    }
    
    
    function getUser_id($username) {
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT user_id FROM user WHERE username = \''. $username .'\'');
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $returnaer = $row["user_id"].'';
            }
        }        
        $this->connect->close();
        return $returnaer;
    }
}
