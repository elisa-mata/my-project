<?php
include 'config.php'; // perfshin file config.php qe permban lidhjen me databazen

if (isset($_POST['submit'])) { // kontrollon nese forma eshte bere submitted. 

  // pastron inputet per te parandaluar sql injection te futet ne databaze, password hashing me md5
    $name = mysqli_real_escape_string($conn, $_POST['name']);
    $email = mysqli_real_escape_string($conn, $_POST['email']);
    $pass = mysqli_real_escape_string($conn, md5($_POST['password']));
    $cpass = mysqli_real_escape_string($conn, md5($_POST['cpassword']));


    // kontrollo nese perdoruesi ekziston ne tabelen users. Nese po, hedh nje mesazh error.
    $select_users = mysqli_query($conn, "SELECT * FROM `users` WHERE email = '$email' AND password = '$pass'") or die('query failed');
    if (mysqli_num_rows($select_users) > 0) {
        $message[] = ['type' => 'error', 'text' => 'User already exists!'];
    } else {

      // kontrollon nese password dhe confirm password jane te njejte 
      //nese nuk jane do hedhe nje mesazh errori
        if ($pass != $cpass) {
            $message[] = ['type' => 'error', 'text' => 'Password does not match!'];
        // nese jane njesoj te dhenat e perdoruesit te ri ruhen ne databaze
        // dhe perdoruesi drejtohet per tek faqa login.php
          } else {
            mysqli_query($conn, "INSERT INTO `users` (name, email, password) VALUES('$name', '$email', '$cpass')") or die('query failed');
            $message[] = ['type' => 'success', 'text' => 'Registered successfully!'];
            header('location:login.php');
        }
    }
}
?>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pijet Tradicionale Shqiptare</title>
    <link rel="stylesheet" href="css/main-page.css" />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
    />
  </head>
  <body>
    <div class="container">
      <div class="left">
        <div class="nav-bar" style="margin-bottom: 170px">
          <img src="images/logo (27).png" alt="">
          <div class="social-media">
            <i class="fa-brands fa-instagram"></i>
            <i class="fa-brands fa-facebook-f"></i>
            <i class="fa-brands fa-whatsapp"></i>
          </div>
          <div class="search-wrapper">
            <form id="search-form">
              <input id="search-input" type="search" placeholder="Search" />
              <button type="submit" class="search-btn">
                <i id="search-icon" class="fa-solid fa-magnifying-glass"></i>
              </button>
            </form>
          </div>
          
          <a href="e-shop.php">
            <i class="fa-solid fa-cart-shopping">  </i>
          </a>
        </div>

        <h3>NJE UDHETIM NE HISTORINE TONE</h3>
        <h2>
          Pijet tradicionale <br />
          shqiptare
        </h2>
        <button class="tour-btn" id="tour-button">Take a Tour</button>
      </div>
      <div class="right">
        <img src="images/beer.png" alt="" class="right-image" />
      </div>
    </div>

    <div class="container">
      <div class="left">
        <img src="images/cheers.png" alt="" class="left-image" />
      </div>

      <div class="right" style="background-color: #efe3b8">
        <div class="second-text">
          <h2 style="font-size: 30px">
            Zbulo thesaret e <br />
            kulturës sonë në një <br />
            udhëtim shijesh dhe <br />
            historie.
          </h2>
          <p style="font-size: 20px">
            Ne sjellim përpara jush një përvojë tërheqëse për të <br />
            mësuar dhe eksploruar thellësinë e kulturës sonë <br />
            përmes një game të gjerë të pijesh tradicionale. Çdo <br />
            gotë qe pini përmban një histori të fshehur dhe një <br />
            trashëgimi kulturore, dhe ne jemi këtu për të zbuluar <br />
            dhe për të ndarë këto shije dhe histori me ju. <br />
            Nëpërmjet nesh, ju do të zhyteni në botën e <br />
            mrekullueshme të aromave dhe traditave të pijes <br />
            shqiptare.
          </p>
        </div>
      </div>
    </div>
    <div class="category-container">
      <h4>
        Nga pijet alkoolike dhe joalkoolike, deri tek ato verore dhe kurative,
        <br />
        secila kategori përmban një pasuri të trashëgimisë sonë kulturore.
      </h4>

      <div class="category-row">
        <div class="category">
          <a href="pijet-alkoolike.html">
            <img src="images/pijet-alkoolike.png" alt="Pijet Alkoolike" />
            <h3>Pijet Alkoolike</h3>
          </a>
        </div>
        <div class="category">
          <a href="pijet-joalkoolike.html">
            <img src="images/pijet-joalkoolike.png" alt="Pijet Joalkoolike" />
            <h3>Pijet Joalkoolike</h3>
          </a>
        </div>
      </div>
      <div class="category-row">
        <div class="category">
          <a href="pijet-verore.html">
            <img src="images/pijet-verore.png" alt="Pijet Verore" />
            <h3>Pijet Verore</h3>
          </a>
        </div>
        <div class="category">
          <a href="pijet-kurative.html">
            <img src="images/pijet-kurative.png" alt="Pijet Kurative" />
            <h3>Pijet Kurative</h3>
          </a>
        </div>
      </div>
    </div>
    <div class="contact-container">
      <div class="contact-head">
        <h2 style="font-size: 30px">Regjistrohu</h2>
        <p style="font-size: 20px">
          Nëse keni pyetje, sugjerime ose dëshironi të na ndani <br />
          eksperiencën tuaj, jemi këtu për të dëgjuar.
        </p>
      </div>
      <div>
        <img
          src="images/kontakt.png"
          alt="Contact Image"
          class="contact-image"
        />
      </div>
     
  
      <div class="contact-info" >
      <form action="" method="post">
  <label for="username">Username:</label><br>
  <input type="text" placeholder="Your Name" name="name" required class="styled-input" /><br>
  <label for="email">Email:</label><br>
  <input type="text" placeholder="Your Email" name="email" required class="styled-input" /><br>
  <label for="password">Password:</label><br>
  <input type="password" placeholder="Password" id="password" name="password" required class="styled-input"><br>
  <label for="password">Confirm Password:</label><br>
  <input type="password" placeholder="Confirm Password" id="cpassword" name="cpassword" required class="styled-input"><br>
  <input type="submit" value="Register" name="submit" class="register-button">
</form>

<!-- nese $message array permban ndonje mesazh, nepermjet ciklit iterohet neper mesazhe dhe shfaqet ai qe duhet -->
<div class="message-container">
<?php
  if (isset($message)) {
      foreach ($message as $msg) {
          echo '
          <div class="message ' . $msg['type'] . '">
            <span>' . $msg['text'] . '</span>
            <i class="fas fa-times" onclick="this.parentElement.remove()"></i>
          </div>
          ';
      }
  }
  ?>
  </div>
        <p>Already have an account? <a href = "login.php" class="login-button">Login</a></p>
      </div>
      
    </div>
    <script src="main-page.js"></script>
  </body>
</html>
