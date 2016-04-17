<?php
include_once './connector.php';

class modelPaticipant {
    private $participant_id, $user_id, $course_id;
    
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
