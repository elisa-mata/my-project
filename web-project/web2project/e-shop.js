// Ndryshimi ne menyre dinamike i titullit per produktet e gjetura
// Merr të gjitha opsionet e menysë dropdown
const dropdownLinks = document.querySelectorAll('.dropdown-content a');

// Merr elementin e titullit të nënkategorisë
const subcategoryTitle = document.getElementById('subcategory-title');

// Shkon nëpër çdo opsion të menysë dropdown
dropdownLinks.forEach(dropdownLink => {
  // Shto event listener të klikimit për çdo opsion të menysë dropdown
  dropdownLink.addEventListener('click', function(event) {
    // Parandalo sjelljen paracaktuar të opsionit
    event.preventDefault();
    // Përditëso tekstin e titullit të nënkategorisë me tekstin e lidhjes së zgjedhur të menysë dropdown
    subcategoryTitle.textContent = `Produktet e gjetura për ${this.textContent}`;
  });
});



//funksionaliteti i dropdown button per te bere sortimin e produkteve sipas opsioneve
// Event Listener për ngjarjen DOMContentLoaded për të siguruar që të gjithë elementët janë ngarkuar para se të ndërveprojmë me ta
document.addEventListener("DOMContentLoaded", function() {
  // Merr të gjitha opsionet e renditjes
  const sortOptions = document.querySelectorAll(".sort-option");
  // Merr butonin e renditjes
  const sortBtn = document.querySelector(".sort-btn");
  // Merr kontenierin e produkteve
  const productsContainer = document.querySelector(".products");

  // Event Listener për çdo opsion të renditjes
  sortOptions.forEach(option => {
    option.addEventListener("click", function(e) {
      // Parandalo sjelljen e paracaktuar të opsionit
      e.preventDefault();
      // Merr tipin e renditjes nga atributi i të dhënave i opsionit të klikuar
      const sortType = this.dataset.sort;
      // Rendit produktet bazuar në tipin e zgjedhur
      sortProducts(sortType);
      // Përditëso tekstin e butonit të renditjes për të pasqyruar opsionin e zgjedhur të renditjes
      sortBtn.textContent = this.textContent;
    });
  });

  // Funksioni për të renditur produktet bazuar në nje lloj specifik
  function sortProducts(type) {
    // Merr të gjitha kartat e produkteve dhe konvertoji NodeList në array
    const productCards = Array.from(document.querySelectorAll(".product-card"));
    // Deklarimi i shkëmbimit për të përcaktuar logjikën e renditjes bazuar në tip
    switch (type) {
      case "name-asc":
        // Rendit produktet alfabetikisht sipas titullit të produktit në rend rritës
        productCards.sort((a, b) => a.querySelector(".product-title").textContent.localeCompare(b.querySelector(".product-title").textContent));
        break;
      case "name-desc":
        // Rendit produktet  alfabetikisht sipas titullit të produktit në rend zbritës
        productCards.sort((a, b) => b.querySelector(".product-title").textContent.localeCompare(a.querySelector(".product-title").textContent));
        break;
      case "price-asc":
        // Rendit produktet  numerikisht sipas çmimit të produktit në rend rritës
        productCards.sort((a, b) => parseFloat(a.querySelector(".product-price").textContent.replace("$", "")) - parseFloat(b.querySelector(".product-price").textContent.replace("$", "")));
        break;
      case "price-desc":
        // Rendit produktet  numerikisht sipas çmimit të produktit në rend zbritës
        productCards.sort((a, b) => parseFloat(b.querySelector(".product-price").textContent.replace("$", "")) - parseFloat(a.querySelector(".product-price").textContent.replace("$", "")));
        break;
      case "all":
        
        break;
    }
    // Pastroni konteinerin e produkteve
    productsContainer.innerHTML = "";
    // Shtoni produktet të renditura në konteinerin e produkteve
    productCards.forEach(card => productsContainer.appendChild(card));
  }
});


//funksionaliteti i butonit add to cart
// Event Listener për ngjarjen DOMContentLoaded për të siguruar që të gjithë elementët janë ngarkuar para se të ndërveprojmë me ta
document.addEventListener("DOMContentLoaded", function() {
  // Inicializimi i numrit të shportës
  let cartCount = 0;

  // Funksioni për të përditësuar numrin e shportës 
  function updateCartCount() {
    document.getElementById('cart-count').textContent = cartCount;
  }

  // Funksioni për të trajtuar klikimin e butonit 'Add to cart'
  function addToCart() {
    // Shtoni numrin e shportës
    cartCount++;
    // Përditëso numrin e shportës 
    updateCartCount();
    // Skroll lart tek shopping bag
    const buttonsContainer = document.querySelector('.buttons');
    buttonsContainer.scrollIntoView({ behavior: 'smooth' });
  }

  // Merr të gjitha butonat 'Add to cart' dhe shtoni event listener për secilin
  const addToCartBtns = document.querySelectorAll('.add-to-cart-btn');
  addToCartBtns.forEach(btn => {
    btn.addEventListener('click', addToCart);
  });
});

// Event Listener për ngjarjen DOMContentLoaded për të siguruar që të gjithë elementët janë ngarkuar para se të ndërveprojmë me ta
document.addEventListener("DOMContentLoaded", function() {
  // Inicializimi i numrit të shportës dhe subtotalit
  let cartCount = 0;
  let subtotal = 0;

  

  // Funksioni për të trajtuar klikimin e butonit 'Add to cart'
  function addToCart(event) {
    // Merr kartën e produktit të klikuar
    const product = event.target.closest('.product-card');
    if (product) {
      // Nxirr detajet e produktit
      const title = product.querySelector('.product-title').textContent;
      const price = parseFloat(product.querySelector('.product-price').textContent.replace('$', ''));
      
      // Kontrollo nëse produkti është tashmë në shportë
      const cartItems = document.querySelectorAll('.cart-item');
      let existingItem = null;
      cartItems.forEach(item => {
        if (item.querySelector('.cart-item-title').textContent === title) {
          existingItem = item;
        }
      });
      
      if (existingItem) {
        // Nëse produkti është tashmë në shportë, përditëso sasinë e tij
        const quantityElement = existingItem.querySelector('.cart-item-quantity');
        const currentQuantity = parseInt(quantityElement.textContent.split(' ')[1]);
        const newQuantity = currentQuantity + 1;
        quantityElement.textContent = `Sasia: ${newQuantity}`;
      } else {
        // Nëse produkti nuk është në shportë, shtoje
        const quantity = 1;
        cartCount++;
        subtotal += price;
        updateCartCount();
        updateSubtotal();
      
        // Krijo një element të ri të produktit të shportës
        const cartItem = document.createElement('div');
        cartItem.classList.add('cart-item');
        cartItem.innerHTML = `
          <div class="cart-item-info">
            <span class="cart-item-title">${title}</span>
            <span class="cart-item-quantity">Sasia: ${quantity}</span>
          </div>
        `;
        // Shto elementin e produktit të shportës te kontejneri i produkteve të shportës
        document.querySelector('.cart-items').appendChild(cartItem);
      }
    }
  }

  // Merr të gjitha butonat 'Add to cart' dhe shto event listener për secilin
  const addToCartBtns = document.querySelectorAll('.add-to-cart-btn');
  addToCartBtns.forEach(btn => {
    btn.addEventListener('click', addToCart);
  });

  // Merr butonin e shportës dhe dropdown e shportës
  const cartBtn = document.querySelector('.cart-btn');
  const cartDropdown = document.querySelector('.cart-dropdown');

  // Event Listener për ngjarjen mouseenter në butonin e shportës për të shfaqur dropdown e shportës
  cartBtn.addEventListener('mouseenter', function() {
    cartDropdown.classList.add('show');
  });

  // Event Listener për ngjarjen mouseleave në butonin e shportës për të fshehur dropdown e shportës
  cartBtn.addEventListener('mouseleave', function() {
    cartDropdown.classList.remove('show');
  });
});



