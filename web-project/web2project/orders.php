<?php

include 'config.php'; //file qe permban lidhjen me databazen 


session_start(); // inicializon nje sesion te ri

$user_id = $_SESSION['user_id']; // merr id e userit nga sesioni

if(!isset($user_id)){
   header('location:login.php');
} // nese useri nuk eshte i loguar e drejton ne nje faqe ku duhet te logohet fillimisht

?>

<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>orders</title>

   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

   <link rel="stylesheet" href="css/e-shop.css">

</head>
<body>
   
<?php include 'header.php'; ?>

<div class="heading">
   <h3>Your orders</h3>
   <p> <a href="e-shop.php">home</a> / orders </p>
</div>

<section class="placed-orders">

  

   <div class="products">

      <?php
      // query per te marre te gjtiha porosite nga databaza te perdoruesit qe eshte loguar
         $order_query = mysqli_query($conn, "SELECT * FROM `orders` WHERE user_id = '$user_id'") or die('query failed');
        // kontrollon nese ka porosi. iteron ne cdo porosi duke shfaqur detajet e cdo porosie
         if(mysqli_num_rows($order_query) > 0){
            while($fetch_orders = mysqli_fetch_assoc($order_query)){
      ?>
      <div class="product-card">
         <p> placed on : <span><?php echo $fetch_orders['placed_on']; ?></span> </p>
         <p> name : <span><?php echo $fetch_orders['name']; ?></span> </p>
         <p> number : <span><?php echo $fetch_orders['number']; ?></span> </p>
         <p> email : <span><?php echo $fetch_orders['email']; ?></span> </p>
         <p> address : <span><?php echo $fetch_orders['address']; ?></span> </p>
         <p> payment method : <span><?php echo $fetch_orders['method']; ?></span> </p>
         <p> your orders : <span><?php echo $fetch_orders['total_products']; ?></span> </p>
         <p> total price : <span>$<?php echo $fetch_orders['total_price']; ?>/-</span> </p>
         </div>
      <?php
       }
      }else{
         // ne rast se ska porosi per te shfaqur dhe databaza eshte bosh
         echo '<p class="empty">no orders placed yet!</p>';
      }
      ?>
   </div>

</section>
<?php include 'footer.php'; ?>



</body>
</html>