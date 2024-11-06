<?php

include 'config.php';

session_start(); //fillon sesion per aksesim varblash
session_unset(); // ben unset te gjitha variablat e sesionit
session_destroy(); // shkaterron sesionin e momentit
// te drejton te login
header('location:login.php');

?>