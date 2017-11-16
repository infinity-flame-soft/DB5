<?php

require "conn.php";

$name=$_POST["post_name"];
$password=$_POST["post_pass"];
$email=$_POST["post_email"];

$sql="INSERT INTO `user`(`name`, `password`, `email`) VALUES ('$name','$password','$email');";

if ($conn->query($sql)=== TRUE){
	echo "success";
}
else{
    echo "error";
}
$conn->close();
?>