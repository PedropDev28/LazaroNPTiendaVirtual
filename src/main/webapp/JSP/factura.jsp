<%-- 
    Document   : factura
    Created on : 26 oct. 2023, 20:49:55
    Author     : Pedro Lazaro
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="metodos" scope="page" 
    class="es.albarregas.models.Metodos" />
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
        <title>Factura</title>
    </head>
    <body>
        <%@include file="/INC/header.inc"%>
        <div class="instr">
            <p>Esta viendo su factura, gracias por confiar en nosotros, para terminar pulse en el botón</p>
        </div>
        <div class="main-container">
        <form action="<c:out value='${contextPath}' />/CarroController" method="post">
                <table class="container">
                    <tr>
                        <th>Cantidad</th>
                        <th>Nombre</th>
                        <th>Precio Unitario</th>
                        <th>Total</th>
                    </tr>
        <c:if test="${carrito != null}">
        <c:forEach var="item" items="${carrito}">
            <tr>
                <td>${item.getCantidad()}</td>
                <td>${item.getNombre()}</td>
                <td>${item.getPrecioUnitario()} €</td>
                <c:set var="total" value="${metodos.calcularTotal(item.getPrecioUnitario(), item.getCantidad())}" scope="page"/>
                <td>${total} €</td>
                <c:choose>
                    <c:when test="${item.getCantidad() == 1}">
                    <c:set var="dis" value="disabled"/>
                    </c:when>
                    <c:otherwise>
                    <c:set var="dis" value=""/>
                    </c:otherwise>
                </c:choose>
                <c:set var="totalSinIVA" value="${totalSinIVA + item.getPrecioUnitario() * item.getCantidad()}" scope="page" />
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3">Total sin IVA</td>
            <td>${totalSinIVA} €</td>
        </tr>
        <tr>
            <td colspan="3">IVA (21%)</td>
            <c:set var="totaliva" value="${metodos.resultadoIva(totalSinIVA)}" scope="page"/>
            <td>${totaliva} €</td>
        </tr>
        <tr>
            <td colspan="3">Total</td>
            <td>${metodos.resultadoTotal(totalSinIVA, totaliva)} €</td>
        </tr>
        </c:if>
        </table>
                <div class="botones">
                    <button type="submit" name="button" value="terminar">Terminar</button>
                </div>
            </form>
        </div>
    </body>
</html>
