<?php

class modelExamSet {
    private $exset_id, $exset_sum, $ex_timer, $lanch_time, $stop_time, $course_id;
    
    function createNewExamSet($exset_sum, $ex_timer, $lanch_time, $stop_time, $course_id) {
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
            $row = $result->fetch_assoc();
                $this->exset_id = $exset_id;
                $this->exset_sum = $row["exset_sum"];
                $this->ex_timer = $row['ex_timer'];
                $this->lanch_time = $row['lanch_time'];
                $this->stop_time = $row['stop_time'];
                $this->course_id = $row['course_id'];
        }
        else {echo 'no result';}
        $this->connect->close();
    }
    
    function createFullExamSet($exset_id, $exset_sum, $ex_timer, $lanch_time, $stop_time, $course_id) {
        $this->exset_id = $exset_id;
        $this->exset_sum = $exset_sum;
        $this->ex_timer = $ex_timer;
        $this->lanch_time = $lanch_time;
        $this->stop_time = $stop_time;
        $this->course_id = $course_id;
    }
}
