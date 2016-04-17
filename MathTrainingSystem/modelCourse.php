<?php
include_once './connector.php';

class modelCourse {
    private $course_id, $course_name, $course_description, $course_created, $instructor_id;
    
    function __construct() {
    }

    
    function createNewCourse($course_id, $course_name, $course_description, $course_created, $instructor_id) {
        $this->course_id = $course_id;
        $this->course_name = $course_name;
        $this->course_description = $course_description;
        $this->course_created = $course_created;
        $this->instructor_id = $instructor_id;
    }
    
    function insertNewCourse(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO course (course_id, course_name, course_description, course_created, instructor_id)'
                . ' VALUES (NULL, \''
                . $this->course_name .'\',\''. $this->course_description .
                '\', CURRENT_TIMESTAMP ,'. $this->instructor_id . ')');
        $this->connect->close();
    }
    
    function callCourse($course_id){
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT * FROM course WHERE course_id = \''. course_id .'\';');
        if ($result->num_rows > 0){
            $this->course_id = $course_id;
            $this->course_name = $result->fetch_assoc()['course_name'];
            $this->course_description = $result->fetch_assoc()['course_description'];
            $this->course_created = $result->fetch_assoc()['course_created'];
            $this->instructor_id = $result->fetch_assoc()['instructor_id'];
        }
    }
    
    function queryter($selector, $wherer){
        $this->connect = new connector();
        $returner = 'null';
        $result = $this->connect->executeQuery('SELECT'. $wherer .'FROM course WHERE username = \''. $username .'\'');
        if ($result->num_rows > 0) {
            $returner = $result->fetch_assoc()[$wherer];
        }
        $this->connect->close();
        return $returner."";
    }

}
