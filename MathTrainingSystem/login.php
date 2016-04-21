<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>E-learning Mathematics</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

  </head>

    <style>
        body {
            font-family: 'TH SarabunPSK';
        }

        div.container {
            padding-top: 70px;
        }
    </style>


  <body>

  	<div class="container">
	<div class="row">
		<div class="col-md-12">
		<!--Login-->
			<center>
			<h1>
				>> Login
			</h1>
			</center>
            <!-- add action="controllerLogin.php" from original login form -> login.php -->
			<form action="controllerLogin.php" class="form-horizontal" role="form" method="POST">
				<div class="form-group">
					 <!-- input email-->
					<label for="inputEmail3" class="col-sm-4 control-label">
						Email
					</label>
					<div class="col-sm-5">
						<!-- add name="username" for $_POST["username"] matching in controllerLogin.php from original login form -->
						<input class="form-control" id="inputEmail3" name="username" type="text" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-4 control-label">
						Password
					</label>
					<div class="col-sm-5">
                        <!-- add name="password" for $_POST["password"] matching in controllerLogin.php from original login form -->
						<input class="form-control" id="inputPassword3" name="password" type="password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-5">
						<div class="checkbox">
							<label>
								<input type="checkbox" /> Remember me
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-5">
                        <!-- add name="subbutton", value="login" for $_POST["subbutton"] matching in controller.php from original login form -->
						<button type="submit" class="btn btn-default">
							Login
						</button>
                        <!--
                            add additional button for register
                            name="subbutton", value="register" for $_POST["subbutton"] matching in controller.php from original login form
                        -->
                        <button type="submit" class="btn btn-default">
                            Register
                        </button>
					</div>
				</div>
			</form>


			<!--Bar-->
			<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
				<div class="navbar-header">
					 
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
					</button> <a class="navbar-brand" href="#"> สัญลักษณ์ IT ระบบฝึกทักษะทางคณิตศาสตร์</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-5">
					<ul class="nav navbar-nav navbar-right">
						<li class="active">
							<a href="#">home</a>
						</li>
						<li>
							<a href="#">login</a>
						</li>
					</ul>

				</div>
				
			</nav>
		</div>
	</div>
</div>


    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>