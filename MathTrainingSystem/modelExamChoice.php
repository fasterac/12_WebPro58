<?php


class modelExamChoice {
    private $exnum_id, $fst_choice, $snd_choice, $trd_choice, $fth_choice;
    
    function createExamChoice($exnum_id, $fst_choice, $snd_choice, $trd_choice, $fth_choice) {
        $this->exnum_id = $exnum_id;
        $this->fst_choice = $fst_choice;
        $this->snd_choice = $snd_choice;
        $this->trd_choice = $trd_choice;
        $this->fth_choice = $fth_choice;
    }
    
    function insertNewExamChoice(){
        $connect = new connector();
        $connect->executeUpdate('INSERT INTO choice (exnum_num, fst_choice, snd_choice, trd_choice, fth_choice)'
                . ' VALUES (\'' . $this->exnum_num .'\',\''
                . $this->fst_choice .'\',\'' . $this->snd_choice.'\',\''
                . $this->trd_choice .'\',\'' .$this->fth_choice .'\')');
        $connect->close();
    }
    
    function callExamChoice($exnum_id){
        $connect = new connector();
        $result = $connect->executeQuery('SELECT * FROM choice WHERE course_id = \''. $exnum_id .'\';');
        if ($result->num_rows > 0){
            $row = $result->fetch_assoc();
                $this->exnum_id = $exnum_id;
                $this->fst_choice = $row["fst_choice"];
                $this->snd_choice = $row['snd_choice'];
                $this->trd_choice = $row['trd_choice'];
                $this->fth_choice = $row['fth_choice'];
        }
        else {echo 'no result';}
        $connect->close();
    }
    
    function insertBlankExamChoice(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO choice ($exnum_id, fst_choice, snd_choice, trd_choice, fth_choice)'
                . ' VALUES (\'' . $this->exnum_num .'\', '-', '-', '-', '-')');
        $this->connect->close();
    }
}
