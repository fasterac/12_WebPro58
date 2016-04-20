<?php
session_start();

if($_POST['subbut'] === 'Logout'){
        session_destroy();
        header("Location: ./login.php");
    }

