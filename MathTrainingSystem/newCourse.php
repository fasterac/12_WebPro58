<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        
        <?php        
        include_once './modelUser.php';
        
        session_start();
        $user = new modelUser();
        $user = $_SESSION['sesuser'];
        echo '<form action="controllerHomeIns.php" method="POST">'.
            '<h2>'. $user->getName(). $user->getSurname() . 'Create New Course </h2>'.
            '<input type="submit" value="Logout" name="subbut" />
             <input type="submit" value="Back to home" name="subbut" />
            Course Name:<input type="text" name="coursename" /><br>
            Description: <textarea name="description" rows="2" cols="60"></textarea><br>
            <input type="submit" value="Create course" name="subbut" />
        </form>';
        ?>
    </body>
</html>
