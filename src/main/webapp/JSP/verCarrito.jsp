<%-- 
    Document   : verCarrito
    Created on : 26 oct. 2023, 20:49:55
    Author     : Pedro Lazaro
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<c:set var="carrito" value="${sessionScope.carrito}"/>
<c:set var="dis" value=""/>
<html lang="es">
    <head>
        <%@include file="/INC/metas.inc"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="./IMG/ico.png" type="image/x-icon">
        <link rel="stylesheet" href="./CSS/verCarrito.css">
        <title>Carrito</title>
    </head>
    <body>
        <%@include file="/INC/header.inc"%>
        <div class="instr">
            <p>Estás viendo el carrito, puedes añadir un producto, o varios, así como eliminarlos. También puedes vaciar el carrito, finalizar la compra o seguir comprando.</p>
        </div>
        <div class="main-container">
        <form action="<c:out value='${contextPath}' />/CarroController" method="post">
                <table class="container">
                    <tr>
                        <th>Cantidad</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th colspan="3">Opciones</th>
                    </tr>
        <c:if test="${carrito != null}">
        <c:forEach var="item" items="${carrito}">
            <tr>
                <td>${item.getCantidad()}</td>
                <td>${item.getNombre()}</td>
                <td>${item.getPrecioUnitario()}</td>
                <c:choose>
                    <c:when test="${item.getCantidad() == 1}">
                    <c:set var="dis" value="disabled"/>
                    </c:when>
                    <c:otherwise>
                    <c:set var="dis" value=""/>
                    </c:otherwise>
                </c:choose>
                <td><button type="submit" name="button" value='mas,${item.getNombre()}'>+</button></td>
                <td><button type="submit" ${dis} name="button" value='menos,${item.getNombre()}'>-</button></td>
                <td><button type="submit" name="button" value='eliminarpr,${item.getNombre()}'>Eliminar</button></td>
            </tr>
        </c:forEach>
        </c:if>
        </table>
                <div class="botones">
                    <button type="submit" name="button" value="vaciar">Vaciar carrito</button>
                    <button type="submit" name="button" value="comprar">Comprar</button>
                    <button type="submit" name="button" value="volver">Seguir Comprando</button>
                </div>
            </form>
        </div>
    </body>
</html>
