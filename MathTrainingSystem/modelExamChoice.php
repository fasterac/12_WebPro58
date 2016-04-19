<?php


class modelExamChoice {
    private $exnum_id, $fst_choice, $snd_choice, $trd_choice, $fth_choice;
    
    function createNewCourse($exnum_id, $fst_choice, $snd_choice, $trd_choice, $fth_choice) {
        $this->exnum_id = $exnum_id;
        $this->fst_choice = $fst_choice;
        $this->snd_choice = $snd_choice;
        $this->trd_choice = $trd_choice;
        $this->fth_choice = $fth_choice;
    }
    
    function insertNewCourse(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO choice (exnum_num, fst_choice, snd_choice, trd_choice, fth_choice)'
                . ' VALUES (\'' . $this->exnum_num .'\',\''
                . $this->fst_choice .'\',\'' . $this->snd_choice.'\',\''
                . $this->trd_choice .'\',\'' .$this->fth_choice .'\')');
        $this->connect->close();
    }
    
    function callCourse($exnum_id){
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT * FROM choice WHERE exnum_id = \''. $exnum_id .'\';');
        if ($result->num_rows > 0){
            $this->exnum_id = $exnum_id;
            $this->fst_choice = $result->fetch_assoc()['fst_choice'];
            $this->snd_choice = $result->fetch_assoc()['snd_choice'];
            $this->trd_choice = $result->fetch_assoc()['trd_choice'];
            $this->fth_choice = $result->fetch_assoc()['fth_choice'];
        }
        $this->connect->close();
    }
    
    function insertBlankChoice(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO choice ($exnum_id, fst_choice, snd_choice, trd_choice, fth_choice)'
                . ' VALUES (\'' . $this->exnum_num .'\', '-', '-', '-', '-')');
        $this->connect->close();
    }
}
