<?php
session_start();

if($_POST['subbut'] === 'Logout'){
        session_destroy();
        header("Location: ./login.php");
    }
elseif($_POST['subbut'] === 'Delete account'){
    header("Location: ./login.php");
}
elseif($_POST['subbut'] === 'Grant Access'){
    header("Location: ./login.php");
}
    
