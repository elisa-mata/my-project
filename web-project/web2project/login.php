<?php
include 'config.php';
session_start();


// kontrollo nese login form eshte bere submitted
if (isset($_POST['submit'])) {
// merr ne menyre te sigurt inputet e perdoruesit 
    $email = $conn->real_escape_string($_POST['email']);
    $password = md5($conn->real_escape_string($_POST['password']));
    $user_type = $conn->real_escape_string($_POST['user_type']);

    // pergatitet query bazuar ne user type
    if ($user_type == 'user') {
        $query = "SELECT * FROM users WHERE email='$email' AND password='$password'";
    } else if ($user_type == 'admin') {
        $query = "SELECT * FROM admin WHERE email='$email' AND password='$password'";
    }

    //ekzekutohet query
    $result = $conn->query($query);

    // kontrollojme nese useri ekziston ne databaze
    if ($result->num_rows > 0) {
        //merr te dhenat e perdoruesit
        $row = $result->fetch_assoc();

        // kur te logohet admini te drejtohet tek admin_page
        if ($user_type == 'admin') {
            $_SESSION['admin_name'] = $row['name'];
            $_SESSION['admin_email'] = $row['email'];
            $_SESSION['admin_id'] = $row['id'];
            header('Location: admin_page.php');
            exit();
            // kur logohet nje user te shkoje ne main-page
        } elseif ($user_type == 'user') {
            $_SESSION['user_name'] = $row['name'];
            $_SESSION['user_email'] = $row['email'];
            $_SESSION['user_id'] = $row['id'];
            header('Location: main-page.php');
            exit();
        }
    } else {
        // nese login fail do te shfaqet mesazhi
        $message[] = 'Incorrect email or password!';
    }
}


?>


<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>login</title>

   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

   <link rel="stylesheet" href="css/main-page.css">
   <style>
       body, html {
           background-color: #efe3b8;
           color: #a6603a;
           height: 100%;
           margin: 0;
           display: flex;
           justify-content: center;
           align-items: center;
       }
       .contact-info {
           margin-bottom: 350px;
       }
     
       </style>

</head>
<body>
    <!-- shfaq mesazh nese eshte e nevojshme  -->
<div class= "message-container">
<?php
if(isset($message)){
   foreach($message as $message){
      echo '
      <div class="message">
         <span>'.$message.'</span>
         <i class="fas fa-times" onclick="this.parentElement.remove();"></i>
      </div>
      ';
   }
}
?>
   </div>
<div class="contact-info">

   <form action="" method="post">
      <h3>Login now</h3>
      <input type="email" name="email" placeholder="enter your email" required class="styled-input">
      <input type="password" name="password" placeholder="enter your password" required class="styled-input">
      <select name="user_type" required class="styled-input">
         <option value="user">User</option>
         <option value="admin">Admin</option>
      </select>
      <input type="submit" name="submit" value="Login now" class="login-button">
      <p>don't have an account? <a href="main-page.php#contact-info" class="login-button">Register now</a></p>

   </form>

</div>

</body>
</html>