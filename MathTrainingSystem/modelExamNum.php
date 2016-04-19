<?php

class modelExamNum {
    private $exnum_id, $exnum_num, $exnum_qst, $exnum_ans, $exnum_maxscr, $exnum_type;
    
    
    function createNewExamNum($exnum_id, $exnum_num, $exnum_qst, $exnum_ans, $exnum_maxscr, $exnum_type) {
        $this->exnum_id = $exnum_id;
        $this->exnum_num = $exnum_num;
        $this->exnum_qst = $exnum_qst;
        $this->exnum_ans = $exnum_ans;
        $this->exnum_maxscr = $exnum_maxscr;
        $this->exnum_type = $exnum_type;
    }
    
    function insertNewExamNum(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO examnum (exnum_num, exnum_qst, exnum_ans, exnum_maxscr, exnum_type)'
                . ' VALUES (\'' . $this->exnum_num .'\',\''
                . $this->exnum_qst .'\',\'' . $this->exnum_ans .'\',\''.$this->exnum_maxscr
                .'\',\'' .$this->exnum_type .'\')');
        $this->connect->close();
    }
    
    function callExamNum($exnum_id){
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT * FROM examnum WHERE exnum_id = \''. $exnum_id .'\';');
        if ($result->num_rows > 0){
            $this->exnum_id = $exnum_id;
            $this->exnum_num = $result->fetch_assoc()['exnum_num'];
            $this->exnum_qst = $result->fetch_assoc()['exnum_qst'];
            $this->exnum_ans = $result->fetch_assoc()['exnum_ans'];
            $this->exnum_maxscr = $result->fetch_assoc()['exnum_maxscr'];
            $this->exnum_type = $result->fetch_assoc()['exnum_type'];
        }
        $this->connect->close();
    }
}
