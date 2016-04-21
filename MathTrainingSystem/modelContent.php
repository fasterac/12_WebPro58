<?php


class modelContent {
    private $content_id, $content_name, $content_file, $content_created, $course_id;
    
    
    
    function createNewContent($content_name, $content_file, $course_id) {
        $this->content_name = $content_name;
        $this->content_file = $content_file;
        $this->course_id = $course_id;
    }
    
    function insertNewContent(){
        $connect = new connector();
        $connect->executeUpdate('INSERT INTO content ( content_name, content_file, course_id)'
                . ' VALUES (\'' . $this->content_name .'\',\''. $this->content_file 
                . '\',\''. $this->course_id . '\');');
        $connect->close();
    }
    
    function callContent($content_id){
        $connect = new connector();
        $result = $connect->executeQuery('SELECT * FROM content WHERE content_id = \''. $content_id .'\';');
        if ($result->num_rows > 0){
            $this->course_id = $content_id;
            $this->content_name = $result->fetch_assoc()['content_name'];
            $this->content_file = $result->fetch_assoc()['content_file'];
            $this->content_created = $result->fetch_assoc()['content_created'];
            $this->course_id = $result->fetch_assoc()['course_id'];
        }
    }
    
    function createFullContent($content_id, $content_name, $content_file, $content_created, $course_id) {
        $this->content_id = $content_id;
        $this->content_name = $content_name;
        $this->content_file = $content_file;
        $this->content_created = $content_created;
        $this->course_id = $course_id;
    }
}
