<%-- 
    Document   : index
    Created on : 26 oct. 2023, 20:49:25
    Author     : Pedro Lazaro
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html lang="es">
    <head>
        <%@include file="/INC/metas.inc"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="./IMG/ico.png" type="image/x-icon">
        <script src="./JS/script.js" defer></script>
        <link rel="stylesheet" href="./CSS/style.css">
        <title>Tienda</title>
    </head>
    <c:if test="${sessionScope.carrito != null}">
        <c:set var="disabled" value=""/>
    </c:if>
    <c:set var="dis" value="dis"/>
    <c:choose>
        <c:when test="${requestScope.cantidad == null}">
            <c:set var="dis" value="dis"/>
        </c:when>
        <c:otherwise>
            <c:set var="dis" value=""/>
        </c:otherwise>
    </c:choose>
 <body>
    <section>
        <div class='air air1'></div>
        <div class='air air2'></div>
        <div class='air air3'></div>
        <div class='air air4'></div>
    </section>
    <header>
        <h1>ChipChic</h1>
    </header>
    <div class="instr">
        <p>Para añadir un producto a la cesta, pulse sobre su nombre, establezca la cantidad y pulse en añadir a
            carrito</p>
        <p class="${dis}">Se ha añadido correctamente ${requestScope.cantidad} ${requestScope.nombre} al carrito</p>
    </div>
    <div class="main-container">
        <form action="<c:out value='${contextPath}' />/FrontController" method="post">
            <br>
            <div class="products">
                <h3>Productos</h3>
                <select id="productos" name="productos" size="9">
                    <option value="899.00,ASUS TUF Gaming F15" selected>ASUS TUF Gaming F15</option>
                    <option value="12.90,Logitech M185 Ratón Inalámbrico">Logitech M185 Ratón Inalámbrico</option>
                    <option value="13.00,Logitech K120 Teclado">Logitech K120 Teclado</option>
                    <option value="61.99,MSI MAG FORGE M100R">MSI MAG FORGE M100R</option>
                    <option value="240.76,AMD Ryzen 5 7600">AMD Ryzen 5 7600</option>
                    <option value="19.23,Subblim Elegant Funda Maletín">Subblim Elegant Funda Maletín</option>
                    <option value="136.98,MSI PRO B650M-P">MSI PRO B650M-P</option>
                    <option value="56.99,Seagate BarraCuda">Seagate BarraCuda</option>
                    <option value="4.30,Equip Cable HDMI 2.0">Equip Cable HDMI 2.0</option>
                    <option value="14.04,Soporte para TV/Monitor">Soporte para TV/Monitor</option>
                </select>
                <br>
                <div class="cantidad">
                    <label for="cantidad">Cantidad:</label>
                    <input type="number" name="cantidad" value="1" min="1" max="10">
                </div>
                <br>
                <div class="buttons">
                    <button type="submit" class="button" name="button" value="anadir">Añadir al Carrito</button>
                    <button class="button" type="submit" name="button" ${disabled} value="ver">Ver Carrito</button>
                    <button class="button" type="submit" name="button" ${disabled} value="comprar">Finalizar Compra</button>
                </div>
            </div>
            <div class="imagenes-producto">
                <img id="imagenCambiante" src="./IMG/producto1.png" alt="" class="producto_img">
                <p class="product_descr">ASUS TUF Gaming F15</p>
                <p class="product_precio">899.00€</p>
            </div>
        </form>
    </div>
</body>
</html>
