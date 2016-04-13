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
                . ' VALUES (' . $this->course_id . ',\''
                . $this->course_name .'\',\''. $this->course_description .
                '\', CURRENT_TIMESTAMP ,'. $this->instructor_id . ')');
        $this->connect->close();
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
