<?php

class modelResult {
    private $result_id, $stu_score, $time_used, $paticipant_id, $exset_id;
    
    function createNewResult($stu_score, $time_used, $paticipant_id, $exset_id) {
        $this->stu_score = $stu_score;
        $this->time_used = $time_used;
        $this->paticipant_id = $paticipant_id;
        $this->exset_id = $exset_id;
    }
    
    function insertNewResult(){
        $connect = new connector();
        $connect->executeUpdate('INSERT INTO result (stu_score, time_used, paticipant_id, exset_id)'
                . ' VALUES (\'' . $this->stu_score .'\',\''
                . $this->time_used .'\',\'' . $this->paticipant_id.'\',\''
                . $this->exset_id .'\')');
        $connect->close();
    }
    
    
    function callResult($result_id){
        $connect = new connector();
        $result = $connect->executeQuery('SELECT * FROM result WHERE result_id = \''. $result_id .'\';');
        if ($result->num_rows > 0){
            $row = $result->fetch_assoc();
                $this->result_id = $result_id;
                $this->stu_score = $row["stu_score"];
                $this->time_used = $row['time_used'];
                $this->paticipant_id = $row['paticipant_id'];
                $this->exset_id = $row['exset_id'];
        }
        else {echo 'no result';}
        $connect->close();
    }
    
    function createFullResult($result_id, $stu_score, $time_used, $paticipant_id, $exset_id) {
        $this->result_id = $result_id;
        $this->stu_score = $stu_score;
        $this->time_used = $time_used;
        $this->paticipant_id = $paticipant_id;
        $this->exset_id = $exset_id;
    }
}
