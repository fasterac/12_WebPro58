<?php
/**
 * Description of phpclass
 *
 * @author arm19
 */
class connector {
    private $servername = "mysql.hostinger.in.th";
    private $username = "u709201636_data";
    private $password = "project123";
    private $dbname = "u709201636_data";
    private $conn;
    
            
    function __construct() {
        $this->connectDB();
        //$this->createtable();
    }
    
    public function connectDB() {
        $this->conn = new mysqli($this->servername, $this->username, $this->password, $this->dbname);
        $this->conn->set_charset("utf8");
        // Check connection
        
        if ($this->conn->connect_error) {
            die("Connection failed: " . $this->conn->connect_error);
        }
        else{
            echo " <h6>DB Connected </h6>";
        }
    }
    
    public function executeUpdate($sqlcmd){
        if ($this->conn->query($sqlcmd) === TRUE) {
            echo " <h6>Execute Update successfully</h6>";
        } else {
            echo " Error : " . $this->conn->error;
        }
    }
    
    public function executeQuery($sqlcmd){
        $result = $this->conn->query($sqlcmd);        
        return $result;
    }
    
    public function close(){
        $this->conn->close();
    }
    
    
    
    
    
//    public function createtable(){
//        // sql to create table
//        //$this->connectDB();
//
//        $sql = "CREATE TABLE MyGuests11 (
//        id INT(6) AUTO_INCREMENT, 
//        mlg INT(6),
//        firstname VARCHAR(30) NOT NULL,
//        lastname VARCHAR(30) NOT NULL,
//        email VARCHAR(50),
//        reg_date TIMESTAMP default current_timestamp,
//        PRIMARY KEY(id, mlg)
//        )";
//
//        if ($this->conn->query($sql) === TRUE) {
//            echo "Table MyGuests created successfully";
//        } else {
//            echo "Error creating table: " . $this->conn->error;
//        }
//        $this->conn->close();
//    }
//    public function InsertTable(){ 
//
//        $sql = "INSERT INTO MyGuests10(mlg, firstname, lastname, email) VALUES(
//        1,'Suchaj','Jongprasit','@gmail.com'
//        )";
//
//        if ($this->conn->query($sql) === TRUE) {
//            echo "Table MyGuests insert successfully";
//        } else {
//            echo "Error creating table: " . $this->conn->error;
//        }
//        $this->conn->close();
//    }
//    function droptable(){
//        //$sql = "DROP TABLE MyGuests";
//        $sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='hospital'";
//        
//        if ($this->conn->query($sql) === TRUE) {
//            echo "Table MyGuests drop successfully";
//        } else {
//            echo "Error droping table: " . $this->conn->error;
//        }
//        $this->conn->close();
//    }

}

