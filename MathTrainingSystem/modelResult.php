<?php

class modelResult {
    private $result_id, $stu_score, $time_used, $paticipant_id, $exset_id;
    
    function createNewCourse($result_id, $stu_score, $time_used, $paticipant_id, $exset_id) {
        $this->result_id = $result_id;
        $this->stu_score = $stu_score;
        $this->time_used = $time_used;
        $this->paticipant_id = $paticipant_id;
        $this->exset_id = $exset_id;
    }
    
    function insertNewCourse(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO result (stu_score, time_used, paticipant_id, exset_id)'
                . ' VALUES (\'' . $this->stu_score .'\',\''
                . $this->time_used .'\',\'' . $this->paticipant_id.'\',\''
                . $this->exset_id .'\')');
        $this->connect->close();
    }
    
    function callCourse($result_id){
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT * FROM result WHERE result_id = \''. $result_id .'\';');
        if ($result->num_rows > 0){
            $this->result_id = $result_id;
            $this->stu_score = $result->fetch_assoc()['stu_score'];
            $this->time_used = $result->fetch_assoc()['time_used'];
            $this->paticipant_id = $result->fetch_assoc()['paticipant_id'];
            $this->exset_id = $result->fetch_assoc()['exset_id'];
        }
        $this->connect->close();
    }
}
