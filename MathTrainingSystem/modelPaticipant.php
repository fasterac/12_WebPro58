<?php
include_once './connector.php';

class modelPaticipant {
    private $participant_id, $user_id, $course_id;
    
    function createNewCourse($participant_id, $user_id, $course_id) {
        $this->participant_id = $participant_id;
        $this->user_id = $user_id;
        $this->course_id = $course_id;
    }
    
    function insertNewCourse(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO participant (user_id, course_id)'
                . ' VALUES (\'' . $this->user_id .'\',\''. $this->course_id .'\')');
        $this->connect->close();
    }
    
    function callCourse($exnum_id){
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT * FROM participant WHERE participant_id = \''. $participant_id .'\';');
        if ($result->num_rows > 0){
            $this->participant_id = participant_id;
            $this->user_id = $result->fetch_assoc()['user_id'];
            $this->course_id = $result->fetch_assoc()['course_id'];
        }
        $this->connect->close();
    }
    
    public function getCourse($user_id) {
        $this->connect = new connector();
        $returner = 'null';
        $result = $this->connect->executeQuery('SELECT course_id FROM participant WHERE student_id = \''. $user_id .'\'');
        if ($result->num_rows > 0) {
            $returner = $result->fetch_assoc();
        }
        $this->connect->close();
        return $returner;
    }
}
