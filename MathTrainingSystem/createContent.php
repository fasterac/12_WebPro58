<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Create content</title>
        <script src="js/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        
        <?php
        include_once './modelUser.php';
        include_once './modelCourse.php';
        include_once './connector.php';
        session_start();
        
        
        $user = new modelUser();
        $user = $_SESSION['sesuser'];
        $course = new modelCourse();
        $course = $_SESSION['sescourse'];
        
        
        echo '<form action="controllerContentCreate.php" method="POST">';
        echo 'Welcome '.$user->getName().' '. $user->getSurname().'<input type="submit" value="Logout" name="subbut" /><br>'
                . '<input type="submit" value="Back to Home" name="subbut" /><br>';

        echo '<h1> Create content for course -course- </h1><br>
            Content name: <input type="text" name="contentName" value="" size="30" /><br>
            <textarea name="editor1" id="editor1" rows="10" cols="80">
                This is my textarea to be replaced with CKEditor.
            </textarea>
            <script>
                CKEDITOR.replace( \'editor1\' );
            </script>
            <br>
            <input type="submit" value="Create content" name="subbut" />
        </form>';
        ?>
    </body>
</html>
