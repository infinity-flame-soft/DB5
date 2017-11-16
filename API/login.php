<?php

require "conn.php";

$email=$_POST["post_email"];
$pass=$_POST["post_pass"];

$sql="select * from user where email like '$email' and password like '$pass';";

$check = mysqli_fetch_array(mysqli_query($conn,$sql));

if(isset($check)){

	echo "success";

	}
	else{
		echo "error";
	}
?>