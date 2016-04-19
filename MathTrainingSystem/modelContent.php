<?php


class modelContent {
    private $content_id, $content_name, $content_file, $content_created, $course_id;
    
    
    
    function createNewContent($content_id, $content_name, $content_file, $content_created, $course_id) {
        $this->content_id = $content_id;
        $this->content_name = $content_name;
        $this->content_file = $content_file;
        $this->content_created = $content_created;
        $this->course_id = $course_id;
    }
    
    function insertNewContent(){
        $this->connect = new connector();
        $this->connect->executeUpdate('INSERT INTO course (content_id, content_name, content_file, content_created, course_id)'
                . ' VALUES (NULL, \''
                . $this->content_name .'\',\''. $this->content_file .
                '\', CURRENT_TIMESTAMP ,'. $this->course_id . ')');
        $this->connect->close();
    }
    
    function callContent($content_id){
        $this->connect = new connector();
        $result = $this->connect->executeQuery('SELECT * FROM course WHERE content_id = \''. $content_id .'\';');
        if ($result->num_rows > 0){
            $this->course_id = $content_id;
            $this->content_name = $result->fetch_assoc()['content_name'];
            $this->content_file = $result->fetch_assoc()['content_file'];
            $this->content_created = $result->fetch_assoc()['content_created'];
            $this->course_id = $result->fetch_assoc()['course_id'];
        }
    }
}
