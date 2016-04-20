<?php

class modelExamNum {
    private $exnum_id, $exnum_num, $exnum_qst, $exnum_ans, $exnum_maxscr, $exnum_type;
    
    
    function createNewExamNum($exnum_num, $exnum_qst, $exnum_ans, $exnum_maxscr, $exnum_type) {
        $this->exnum_num = $exnum_num;
        $this->exnum_qst = $exnum_qst;
        $this->exnum_ans = $exnum_ans;
        $this->exnum_maxscr = $exnum_maxscr;
        $this->exnum_type = $exnum_type;
    }
    
    function insertNewExamNum(){
        $connect = new connector();
        $connect->executeUpdate('INSERT INTO examnum (exnum_num, exnum_qst, exnum_ans, exnum_maxscr, exnum_type)'
                . ' VALUES (\'' . $this->exnum_num .'\',\''
                . $this->exnum_qst .'\',\'' . $this->exnum_ans .'\',\''.$this->exnum_maxscr
                .'\',\'' .$this->exnum_type .'\')');
        $connect->close();
    }
    
    function callExamNum($exnum_id){
        $connect = new connector();
        $result = $connect->executeQuery('SELECT * FROM examnum WHERE exnum_id = \''. $exnum_id .'\';');
        if ($result->num_rows > 0){
            $row = $result->fetch_assoc();
                $this->exnum_id = $exnum_id;
                $this->exnum_num = $row["exnum_num"];
                $this->exnum_qst = $row['exnum_qst'];
                $this->exnum_ans = $row['exnum_ans'];
                $this->exnum_maxscr = $row['exnum_maxscr'];
                $this->exnum_type = $row['exnum_type'];
        }
        else {echo 'no result';}
        $connect->close();
    }
    
    function createFullExamNum($exnum_id, $exnum_num, $exnum_qst, $exnum_ans, $exnum_maxscr, $exnum_type) {
        $this->exnum_id = $exnum_id;
        $this->exnum_num = $exnum_num;
        $this->exnum_qst = $exnum_qst;
        $this->exnum_ans = $exnum_ans;
        $this->exnum_maxscr = $exnum_maxscr;
        $this->exnum_type = $exnum_type;
    }
}
