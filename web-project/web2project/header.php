<header>
  <div class="nav-bar">
    <div class="social-media">
      <i class="fa-brands fa-instagram"></i>
      <i class="fa-brands fa-facebook-f"></i>
      <i class="fa-brands fa-whatsapp"></i>
    </div>
    <div class="buttons">
      <a href="main-page.php" class="nav-btn">Home</a>
      <a href="e-shop.php" class="nav-btn">Shop</a>
      <a href="orders.php" class="nav-btn">Orders</a>
      <div class="cart-container">
        <!-- ne menyre dinamike merr dhe shfaq numrin e produkteve ne karten e perdoruesit -->
        <?php
          $select_cart_number = mysqli_query($conn, "SELECT * FROM `cart` WHERE user_id = '$user_id'") or die('query failed');
          $cart_rows_number = mysqli_num_rows($select_cart_number); 
        ?>
        <a href="cart.php" class="nav-btn"> <i class="fas fa-shopping-cart"></i> <span>(<?php echo $cart_rows_number; ?>)</span> </a>
        <a href="logout.php" class="delete-btn">Logout</a>      
      </div>
    </div>
  </div>

  <div class="header">
    <div class="logo">
      <img src="images/logo (27).png" alt="">
      <img src="images/logoshkrimi (2).png" alt="" style="width: 480px;">
    </div>
    
    <div class="search-bar">
      <form action="e-shop.php" method="GET">
        <input type="text" id="search-input" name="search" placeholder="Search Store">
        <button type="submit" id="search-btn">Search</button>
      </form>
    </div>
  </div>
</header>
