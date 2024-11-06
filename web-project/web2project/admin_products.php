<?php

include 'config.php';

session_start();

$admin_id = $_SESSION['admin_id'];

if (!isset($admin_id)) {
    header('location:login.php');
    exit();
}

$message = []; 

// shtojme nje produkt
// marrim inputet nga forma
if (isset($_POST['add_product'])) {
   $name = mysqli_real_escape_string($conn, $_POST['name']);
   $price = $_POST['price'];
   $quantity = $_POST['quantity'];
   $image = $_FILES['image']['name'];
   $image_size = $_FILES['image']['size'];
   $image_tmp_name = $_FILES['image']['tmp_name'];
   $image_folder = 'images/'.$image;

   // kontrollojme nese produkti ekziston
   $select_product_name = mysqli_query($conn, "SELECT name FROM `products` WHERE name = '$name'") or die('query failed');

   if (mysqli_num_rows($select_product_name) > 0) {
       $message[] = 'Product name already added';
   } else {

      // Shtojmë produktin e ri në bazën e të dhënave
       $add_product_query = mysqli_query($conn, "INSERT INTO `products`(name, price, image, quantity) VALUES('$name', '$price', '$image', '$quantity')") or die('query failed');

       if ($add_product_query) {
           if ($image_size > 2000000) {
               $message[] = 'Image size is too large';
           } else {
               move_uploaded_file($image_tmp_name, $image_folder);
               $message[] = 'Product added successfully!';
           }
       } else {
           $message[] = 'Product could not be added!';
       }
   }
}


// Fshirja e nje produkti
// Kontrollojmë nëse është kërkuar fshirja e një produkti
if(isset($_GET['delete'])){
   $delete_id = $_GET['delete'];
       // Marrim dhe fshijmë imazhin e produktit
   $delete_image_query = mysqli_query($conn, "SELECT image FROM `products` WHERE id = '$delete_id'") or die('query failed');
   $fetch_delete_image = mysqli_fetch_assoc($delete_image_query);
   unlink('images/'.$fetch_delete_image['image']);
     // Fshijmë produktin nga baza e të dhënave
   mysqli_query($conn, "DELETE FROM `products` WHERE id = '$delete_id'") or die('query failed');
   header('location:admin_products.php');
}

// perditesojme te dhenat e nje produkti
if(isset($_POST['update_product'])){

   //  // Marrim inputet forma
   $update_p_id = $_POST['update_p_id'];
   $update_name = $_POST['update_name'];
   $update_price = $_POST['update_price'];
   $update_quantity = $_POST['update_quantity'];

   // Përditësojmë detajet e produktit në bazën e të dhënave
   mysqli_query($conn, "UPDATE `products` SET name = '$update_name', price = '$update_price', quantity = '$update_quantity' WHERE id = '$update_p_id'") or die('query failed');

   $update_image = $_FILES['update_image']['name'];
   $update_image_tmp_name = $_FILES['update_image']['tmp_name'];
   $update_image_size = $_FILES['update_image']['size'];
   $update_folder = 'images/'.$update_image;
   $update_old_image = $_POST['update_old_image'];

   if(!empty($update_image)){
      // // Përditësojmë imazhin e produktit
      if($update_image_size > 2000000){
         $message[] = 'image file size is too large';
      }else{
         mysqli_query($conn, "UPDATE `products` SET image = '$update_image' WHERE id = '$update_p_id'") or die('query failed');
         move_uploaded_file($update_image_tmp_name, $update_folder);
         unlink('images/'.$update_old_image);
      }
   }

   header('location:admin_products.php');

}


?>

<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Products</title>

   
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

 
   <link rel="stylesheet" href="css/admin_style.css">
</head>
<body>
<?php include 'admin_header.php'; ?>

<section class="add-products">
   <!-- Product CRUD section -->
   <form action="" method="post" enctype="multipart/form-data">
      <h3>Add Product</h3>
      <input type="text" name="name" class="box" placeholder="Enter product name" required>
      <input type="number" min="0" name="price" class="box" placeholder="Enter product price" required>
      <input type="number" min="0" name="quantity" class="box" placeholder="Enter product quantity" required>
      <input type="file" name="image" accept="image/jpg, image/jpeg, image/png" class="box" required>
      <input type="submit" value="Add Product" name="add_product" class="btn">
   </form>
</section>

<section class="show-products">
   <div class="products-title">
    
   </div>
   <div class="box-container">
      <?php
         $select_products = mysqli_query($conn, "SELECT * FROM `products`") or die('query failed');
         if (mysqli_num_rows($select_products) > 0) {
            while ($fetch_products = mysqli_fetch_assoc($select_products)) {
      ?>
      <div class="box product-card">
         <img src="images/<?php echo $fetch_products['image']; ?>" alt="">
         <div class="name product-title"><?php echo $fetch_products['name']; ?></div>
         <div class="price product-price">$<?php echo $fetch_products['price']; ?>/-</div>
         <div class="quantity product-quantity">Quantity: <?php echo $fetch_products['quantity']; ?></div>
         <a href="admin_products.php?update=<?php echo $fetch_products['id']; ?>" class="option-btn">Update</a>
         <a href="admin_products.php?delete=<?php echo $fetch_products['id']; ?>" class="delete-btn" onclick="return confirm('Delete this product?');">Delete</a>
      </div>
      <?php
         }
      } else {
         echo '<p class="empty">No products added yet!</p>';
      }
      ?>
   </div>
</section>


<section class="edit-product-form">

   <?php
      if(isset($_GET['update'])){
         $update_id = $_GET['update']; // Marrim ID-në e produktit që duhet të përditësohet nga URL-ja
       // Ekzekutojmë një pyetje për të marrë të dhënat e produktit me ID-në e marrë
         $update_query = mysqli_query($conn, "SELECT * FROM `products` WHERE id = '$update_id'") or die('query failed');
         if(mysqli_num_rows($update_query) > 0){  // Kontrollojmë nëse ekziston një produkt me këtë ID
            while($fetch_update = mysqli_fetch_assoc($update_query)){ // Marrim të dhënat e produktit dhe i ruajmë në një array
   ?>
   <form action="" method="post" enctype="multipart/form-data">
      <input type="hidden" name="update_p_id" value="<?php echo $fetch_update['id']; ?>">
      <input type="hidden" name="update_old_image" value="<?php echo $fetch_update['image']; ?>">
      <img src="images/<?php echo $fetch_update['image']; ?>" alt="">
      <input type="text" name="update_name" value="<?php echo $fetch_update['name']; ?>" class="box" required placeholder="Enter product name">
      <input type="number" name="update_price" value="<?php echo $fetch_update['price']; ?>" min="0" class="box" required placeholder="Enter product price">
      <input type="number" name="update_quantity" value="<?php echo $fetch_update['quantity']; ?>" min="0" class="box" required placeholder="Enter product quantity">
      <input type="file" class="box" name="update_image" accept="image/jpg, image/jpeg, image/png">

      <div class = "buttons">
      <input type="submit" value="Update" name="update_product" class="btn">
      <input type="reset" value="Cancel" id="close-update" class="option-btn">
      </div>
     
   </form>
   <?php
         }
      }
      }else{
         echo '<script>document.querySelector(".edit-product-form").style.display = "none";</script>';
      }
   ?>

</section>



<script src="admin_script.js"></script>
</body>
</html>
