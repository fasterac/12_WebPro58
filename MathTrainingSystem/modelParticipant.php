<?php
include_once './connector.php';

class modelParticipant {
    private $participant_id, $user_id, $course_id;
    
    function createNewParticipant($user_id, $course_id) {
        $this->user_id = $user_id;
        $this->course_id = $course_id;
    }
    
    function insertNewParticipant(){
        $connect = new connector();
        $connect->executeUpdate('INSERT INTO participant (student_id, course_id)'
                . ' VALUES (\'' . $this->user_id .'\',\''. $this->course_id .'\');');
        $connect->close();
    }
    
    function callParticipant($participant_id){
        $connect = new connector();
        $result = $connect->executeQuery('SELECT * FROM participant WHERE participant_id = \''. $participant_id .'\';');
        if ($result->num_rows > 0){
            $row = $result->fetch_assoc();
                $this->participant_id = $participant_id;
                $this->user_id = $row["user_id"];
                $this->course_id = $row['course_id'];
        }
        else {echo 'no result';}
        $connect->close();
    }
    
    public function getCourse($user_id) {
        $connect = new connector();
        $returner = 'null';
        $result = $connect->executeQuery('SELECT course_id FROM participant WHERE student_id = \''. $user_id .'\'');
        if ($result->num_rows > 0) {
            $returner = $result->fetch_assoc();
        }
        $connect->close();
        return $returner;
    }
    
    function createFullParticipant($participant_id, $user_id, $course_id) {
        $this->participant_id = $participant_id;
        $this->user_id = $user_id;
        $this->course_id = $course_id;
    }
    
    function deleteParticipation($user_id, $course_id){
        $connect = new connector();        
        $connect->executeUpdate('DELETE FROM participant WHERE student_id = \''. $user_id 
                . '\' AND course_id = \''. $course_id . '\';');
        $connect->close();
    }
}
