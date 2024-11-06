<?php

include 'config.php'; //file qe permban lidhjen me databazen 

session_start(); // inicializon nje sesion te ri

$user_id = $_SESSION['user_id']; // merr id e userit nga sesioni

if(!isset($user_id)){
   header('location:login.php');
} // nese useri nuk eshte i loguar e drejton ne nje faqe ku duhet te logohet fillimisht


// kontrollon nese add to cart button eshte klikuar
if(isset($_POST['add_to_cart'])){

// merr detajet e produkteve nga form submission
  $product_name = $_POST['product_name'];
  $product_price = $_POST['product_price'];
  $product_image = $_POST['product_image'];
  $product_quantity = $_POST['product_quantity'];

  // query qe kontrollon sasine e produktit 
  $check_product_quantity = mysqli_query($conn, "SELECT quantity FROM `products` WHERE name = '$product_name'") or die('query failed');
  $product_data = mysqli_fetch_assoc($check_product_quantity);

  // nese produkti nuk eshte ne gjendje,shton nje mesazh errori
  if ($product_data['quantity'] <= 0) {
      $message[] = 'This product is out of stock!';
  } else {
      // kontrollon nese produkti eshte ne karte
      $check_cart_numbers = mysqli_query($conn, "SELECT * FROM `cart` WHERE name = '$product_name' AND user_id = '$user_id'") or die('query failed');

      if(mysqli_num_rows($check_cart_numbers) > 0){
         $message[] = 'Already added to cart!';
      } else {
          // nese produkti nuk eshte ne karte, e insert ne tabelen cart
         mysqli_query($conn, "INSERT INTO `cart`(user_id, name, price, quantity, image) VALUES('$user_id', '$product_name', '$product_price', '$product_quantity', '$product_image')") or die('query failed');
         $message[] = 'Product added to cart!';
      }
  }
}


$category = isset($_GET['category']) ? $_GET['category'] : '';
$search = isset($_GET['search']) ? $_GET['search'] : '';

$query = "SELECT * FROM `products`";
// filtrimi sipas kategorise
if ($category) {
    $query .= " WHERE category='$category'";
}
// filtrimi nga search bar sipas emrit te produktit
if ($search) {
    $query .= ($category ? " AND" : " WHERE") . " name LIKE '%$search%'";
}

$select_products = mysqli_query($conn, $query) or die('query failed');

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>E Shop</title>
    <link rel="stylesheet" href="css/e-shop.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
</head>
<body>
<div class="message-container">
    <?php
    // shfaq mesazhet per perdoruesin nese eshte e nevojshme
    if (isset($message)) {
        foreach ($message as $msg) {
            echo '<div class="message">' . $msg . '
            <i class="fas fa-times" onclick="this.parentElement.remove();"></i>
            </div>';
        }
    }
    ?>
</div>
<?php include 'header.php'; ?>


<div style="background: #a6603a; margin-bottom: 0;">
    <div class="dropdown">
        <button class="nav-btn" onclick="filterProducts('Pijet Alkoolike')">Pijet Alkoolike <i class="fas fa-caret-down"></i></button>
        <div class="dropdown-content">
            <a href="#" onclick="filterProducts('konjaku')">Konjaku</a>
            <a href="#" onclick="filterProducts('rakia')">Rakia</a>
            <a href="#" onclick="filterProducts('vera')">Vera</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="nav-btn" onclick="filterProducts('Pijet Joalkoolike')">Pijet Joalkoolike<i class="fas fa-caret-down"></i></button>
        <div class="dropdown-content">
            <a href="#" onclick="filterProducts('boze')">Bozë</a>
            <a href="#" onclick="filterProducts('pekmez')">Pekmez</a>
            <a href="#" onclick="filterProducts('rehani')">Rehani</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="nav-btn" onclick="filterProducts('Pijet Verore')">Pijet Verore <i class="fas fa-caret-down"></i></button>
        <div class="dropdown-content">
            <a href="#" onclick="filterProducts('aranxhata')">Aranxhata</a>
            <a href="#" onclick="filterProducts('shurup-trendafili')">Shurup trendafili</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="nav-btn" onclick="filterProducts('Pijet Kurative')">Pijet Kurative<i class="fas fa-caret-down"></i></button>
        <div class="dropdown-content">
            <a href="#" onclick="filterProducts('caj-bliri')">Caj bliri</a>
            <a href="#" onclick="filterProducts('caj-hithre')">Caj hithre</a>
            <a href="#" onclick="filterProducts('caj-mali')">Caj mali</a>
        </div>
    </div>
</div>
<div class="separator"></div>
<div class="products-title">
    <h2 id="subcategory-title">Produktet e gjetura per Pijet</h2>
</div>
<div class="separator"></div>
<div class="sort-by">
    <p>Sort by</p>
    <div class="sort-by-dropdown">
        <button class="sort-btn">All products</button>
        <div class="sort-by-content">
            <a href="#" class="sort-option" data-sort="all">All products</a>
            <a href="#" class="sort-option" data-sort="name-asc">Name: A to Z</a>
            <a href="#" class="sort-option" data-sort="name-desc">Name: Z to A</a>
            <a href="#" class="sort-option" data-sort="price-asc">Price low to high</a>
            <a href="#" class="sort-option" data-sort="price-desc">Price high to low</a>
        </div>
    </div>
</div>
<div class="separator"></div>
<div class="products">
    <div class="box-container">

        <?php
        // iteron tek produktet dhe i shfaq
        if(mysqli_num_rows($select_products) > 0){
            while($fetch_products = mysqli_fetch_assoc($select_products)){
        ?>
        <form action="" method="post" class="product-card">
            <img class="image" src="images/<?php echo $fetch_products['image']; ?>" alt="">
            <div class="name product-title"><?php echo $fetch_products['name']; ?></div>
            <div class="price product-price">$<?php echo $fetch_products['price']; ?>/-</div>
            <div class="quantity product-quantity">Available: <?php echo $fetch_products['quantity']; ?></div>
            <input type="number" min="1" name="product_quantity" value="1" class="qty">
            <input type="hidden" name="product_name" value="<?php echo $fetch_products['name']; ?>">
            <input type="hidden" name="product_price" value="<?php echo $fetch_products['price']; ?>">
            <input type="hidden" name="product_image" value="<?php echo $fetch_products['image']; ?>">
            <input type="submit" value="Add to cart" name="add_to_cart" class="add-to-cart-btn">
        </form>
        <?php
            }
        }else{
            echo '<p class="empty">No products added yet!</p>';
        }
        ?>
    </div>
</div>

<?php include 'footer.php'; ?>
<script src="e-shop.js"></script>
<script>
function filterProducts(category) {
    window.location.href = "e-shop.php?category=" + category;
}
</script>
</body>
</html>
