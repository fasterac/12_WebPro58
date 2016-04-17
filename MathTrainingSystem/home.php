<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <h1> User Student Home Page </h1>
        <?php
        session_start();
        $userid = $_SESSION['userid'];
        $_SESSION['userid'] = $userid;
        echo '<form action="controllerHome.php" method="POST">';
        echo  $userid.'<br><input type="submit" value="Select new course" name="subbut" /><br>';
        
        echo '<input type="submit" value="that course " name="subbut" /><br>';
        
        echo '<input type="submit" value="see score" name="subbut" /><br>            
            <input type="submit" value="Logout" name="subbut" />
            </form>';
        
        ?>
    </body>
</html>
