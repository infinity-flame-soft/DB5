<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

require_once("conn.php");

$sql="select * from user";

$emparray=array();

    $result = mysqli_query($conn, $sql);
    while ($rs = $result->fetch_array(MYSQLI_ASSOC)) {
    	 $emparray[] = $rs;
    }
    echo json_encode($emparray);
    $conn->close();
?>