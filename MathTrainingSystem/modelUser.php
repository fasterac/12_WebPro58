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
    
    public function insertNewUser(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO user (user_id, firstname, lastname, email, username, password, user_type)'
                . ' VALUES ( NULL, \''
                . $this->name .'\',\''. $this->surname .'\',\''. $this->email. '\',\''.
                $this->username.'\',\''. $this->password .'\',\''. $this->user_type . '\')');
        $this->connect->close();
    }
    
    function callUser($user_id){
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT * FROM user WHERE user_id = \''. $user_id .'\';');
        if ($result->num_rows > 0){
            $row = $result->fetch_assoc();
                $this->user_id = $user_id;
                $this->name = $row["firstname"];
                $this->surname = $row['lastname'];
                $this->email = $row['email'];
                $this->username = $row['username'];
                $this->password = $row['password'];
                $this->user_type = $row['user_type'];
        }
        $this->connect->close();
    }
    
    public function checkPassword($username) {
        $this->connect = new connector();
        $returner = 'null';
        $result = $this->connect->executeQuery('SELECT password FROM user WHERE username = \''. $username .'\'');
        if ($result->num_rows > 0) {
            $returner = $result->fetch_assoc()["password"];
        }
        $this->connect->close();
        return $returner."";
    }
        
    function checkUser_type($username) {
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
    
    function checkUser_id($username) {
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
    
    
    function getName() {
        return $this->name;
    }

    function getSurname() {
        return $this->surname;
    }
    
    function getUser_id() {
        return $this->user_id;
    }

    function getUsername() {
        return $this->username;
    }

    function getUser_type() {
        return $this->user_type;
    }







}
