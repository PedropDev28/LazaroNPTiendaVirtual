
let imagenCambiante = document.getElementById("imagenCambiante");
let productDescription = document.querySelector(".product_descr");
let productPrecio = document.querySelector(".product_precio");
let productos = document.getElementById("productos");

productos.addEventListener("change", function (e) {
    function cambio() {
        var selectedOption = productos.options[productos.selectedIndex];
        var [price, productName] = selectedOption.value.split(",");
        var imageSrc = `./IMG/producto${productos.selectedIndex + 1}.png`;

        // Aplicar transiciones CSS para una experiencia más suave
        imagenCambiante.style.opacity = 0;
        productDescription.style.opacity = 0;
        productPrecio.style.opacity = 0;

        setTimeout(function () {
            // Actualizar elementos con nueva información
            imagenCambiante.src = imageSrc;
            productDescription.textContent = `${productName}`;
            productPrecio.textContent = `Precio: ${price}€`;

            // Restaurar la opacidad para mostrar la información actualizada
            imagenCambiante.style.opacity = 1;
            productDescription.style.opacity = 1;
            productPrecio.style.opacity = 1;
        }, 300); // Asegura que la transición CSS se complete antes de actualizar
    }
    cambio();
});