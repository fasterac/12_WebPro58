<?php
include_once './connector.php';

class modelCourse {
    private $course_id, $course_name, $course_description, $course_created, $instructor_id;
    
    function __construct() {
    }
    
    function createNewCourse($course_name, $course_description, $instructor_id) {
        $this->course_name = $course_name;
        $this->course_description = $course_description;
        $this->instructor_id = $instructor_id;
    }
    
    function insertNewCourse(){
        $connect = new connector();
        $connect->executeUpdate('INSERT INTO course (course_name, course_description, instructor_id)'
                . ' VALUES (\''
                . $this->course_name .'\',\''. $this->course_description .'\',\''
                . $this->instructor_id . '\')');
        $connect->close();
    }
    
    function callCourse($course_id){
        $connect = new connector();
        $result = $connect->executeQuery('SELECT * FROM course WHERE course_id = \''. $course_id .'\';');
        if ($result->num_rows > 0){
            $row = $result->fetch_assoc();
                $this->course_id = $course_id;
                $this->course_name = $row["course_name"];
                $this->course_description = $row['course_description'];
                $this->course_created = $row['course_created'];
                $this->instructor_id = $row['instructor_id'];
        }
        else {echo 'no result';}
        $connect->close();
    }
    
    function queryter($selector, $wherer){
        $connect = new connector();
        $returner = 'null';
        $result = $connect->executeQuery('SELECT'. $wherer .'FROM course WHERE username = \''. $username .'\'');
        if ($result->num_rows > 0) {
            $returner = $result->fetch_assoc()[$wherer];
        }
        $connect->close();
        return $returner."";
    }
    
    
    function getCourse_id() {
        return $this->course_id;
    }

    function getCourse_name() {
        return $this->course_name;
    }

    function getCourse_description() {
        return $this->course_description;
    }

    function getInstructor_id() {
        return $this->instructor_id;
    }


}
