<?php
session_start();

if($_POST['subbut'] === 'Logout'){
        header("Location: ./login.php");
    }

