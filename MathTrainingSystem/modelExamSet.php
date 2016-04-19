<?php

class modelExamSet {
    private $exset_id, $exset_sum, $ex_timer, $lanch_time, $stop_time, $course_id;
    
    function createNewExamSet($exset_id, $exset_sum, $ex_timer, $lanch_time, $stop_time, $course_id) {
        $this->exset_id = $exset_id;
        $this->exset_sum = $exset_sum;
        $this->ex_timer = $ex_timer;
        $this->lanch_time = $lanch_time;
        $this->stop_time = $stop_time;
        $this->course_id = $course_id;
    }
    
    function insertNewExamSet(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO examset (exset_sum, ex_timer, lanch_time, stop_time, course_id)'
                . ' VALUES (\'' . $this->exset_sum .'\',\''
                . $this->ex_timer .'\',\'CURRENT_TIMESTAMP \',\''. $this->stop_time .
                '\',\'' .$this->course_id .'\')');
        $this->connect->close();
    }
    
    function callExamSet($exset_id){
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT * FROM examset WHERE exset_id = \''. $exset_id .'\';');
        if ($result->num_rows > 0){
            $this->exset_id = $exset_id;
            $this->exset_sum = $result->fetch_assoc()['exset_sum'];
            $this->ex_timer = $result->fetch_assoc()['ex_timer'];
            $this->lanch_time = $result->fetch_assoc()['lanch_time'];
            $this->stop_time = $result->fetch_assoc()['stop_time'];
            $this->course_id = $result->fetch_assoc()['course_id'];
        }
        $this->connect->close();
    }
}
