<?php
include_once 'dbconfig.php';

echo "Here";
 // variables for input data
 $email=$_POST['email'];
 $name=$_POST['name'];
 $phone=$_POST['phone'];
 $password=$_POST['password'];
 // sql query for inserting data into database
 
        $sql = "INSERT INTO `0fe_17505155_scu`.`tbl_donaters` (`EMAIL`, `NAME`, `PHONE`, `PASSWORD`) VALUES ('".$email."',".$name."',".$phone.",'".$password."');";

        mysql_query($sql);
        
        // sql query for inserting data into database
 
?>