
// shtimi i nje event listener kur klikojme ne butonin "take a tour"
document.getElementById("tour-button").addEventListener("click", function() {
  // Merr div-in e ardhshëm
  var nextDiv = document.querySelector(".second-text");

  // Bëj skroll deri te div-i i ardhshëm
  nextDiv.scrollIntoView({ behavior: "smooth" });
});



// Krijimi i funksionalitetit te search bar
document.addEventListener('DOMContentLoaded', function() {
  // Zgjidh formën e kërkimit dhe inputin
  const searchForm = document.getElementById('search-form');
  const searchInput = document.getElementById('search-input');

  // Shto event listener për paraqitjen e formës
  searchForm.addEventListener('submit', function(event) {
      event.preventDefault(); // Parandalo sjelljen e paracaktuar të paraqitjes së formës

      // Merr vlerën nga inputi i kërkimit
      const searchTerm = searchInput.value.toLowerCase().trim();

      // Kontrollo nëse termi i kërkimit përputhet me njërin nga kategoritë
      switch (searchTerm) {
          case 'pijet alkoolike':
              window.location.href = 'pijet-alkoolike.html';
              break;
          case 'pijet joalkoolike':
              window.location.href = 'pijet-joalkoolike.html';
              break;
          case 'pijet verore':
              window.location.href = 'pijet-verore.html';
              break;
          case 'pijet kurative':
              window.location.href = 'pijet-kurative.html';
              break;
          default:
              // Nëse termi i kërkimit nuk përputhet me asnjë kategori do te na shfaqet alert me mesazhin e meposhtem
              alert('Kategoria nuk u gjet!');
              break;
      }
  });
});
