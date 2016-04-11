<?php
/**
 * Description of phpclass
 *
 * @author arm19
 */
class connector {
    private $servername = "localhost";
    private $username = "root";
    private $password = "";
    private $dbname = "isad";
    private $conn;
    
            
    function __construct() {    
        $this->connectDB();
        //$this->createtable();
    }
    
    public function connectDB() {
        $this->conn = new mysqli($this->servername, $this->username, $this->password, $this->dbname);
        // Check connection
        
        if ($this->conn->connect_error) {
            die("Connection failed: " . $this->conn->connect_error);
        }
        else{
            echo " DB Connected ";
        }
    }
    
    public function executeUpdate($sqlcmd){
        return($this->conn->query($sqlcmd));
    }
    
    public function createtable(){
        // sql to create table
        //$this->connectDB();

        $sql = "CREATE TABLE MyGuests11 (
        id INT(6) AUTO_INCREMENT, 
        mlg INT(6),
        firstname VARCHAR(30) NOT NULL,
        lastname VARCHAR(30) NOT NULL,
        email VARCHAR(50),
        reg_date TIMESTAMP default current_timestamp,
        PRIMARY KEY(id, mlg)
        )";

        if ($this->conn->query($sql) === TRUE) {
            echo "Table MyGuests created successfully";
        } else {
            echo "Error creating table: " . $this->conn->error;
        }
        $this->conn->close();
    }
    public function InsertTable(){ 

        $sql = "INSERT INTO MyGuests10(mlg, firstname, lastname, email) VALUES(
        1,'Suchaj','Jongprasit','@gmail.com'
        )";

        if ($this->conn->query($sql) === TRUE) {
            echo "Table MyGuests insert successfully";
        } else {
            echo "Error creating table: " . $this->conn->error;
        }
        $this->conn->close();
    }
    function droptable(){
        //$sql = "DROP TABLE MyGuests";
        $sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='hospital'";
        
        if ($this->conn->query($sql) === TRUE) {
            echo "Table MyGuests drop successfully";
        } else {
            echo "Error droping table: " . $this->conn->error;
        }
        $this->conn->close();
    }
    
    
           
            
        
}

