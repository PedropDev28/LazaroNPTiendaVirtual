/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * La clase Calculator proporciona métodos para calcular y manipular información relacionada con carritos de compra.
 */
package es.albarregas.models;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import es.albarregas.beans.Articulo;

/**
 * Este modelo proporciona métodos para convertir información de carrito de
 * compra entre representaciones de lista y cookies, así como para realizar
 * cálculos relacionados con el carrito.
 *
 * @author Pedro Lazaro
 */
public class Metodos {

    /**
     * Constructor por defecto de la clase Calculator.
     */
    public Metodos() {
    }

    /**
     * Convierte una cadena de cookie codificada en una lista de objetos Articulo.
     *
     * @param cookie La cadena de cookie codificada que representa el carrito de
     *               compra.
     * @return Una lista de objetos Articulo que representa el carrito de compra.
     * @throws UnsupportedEncodingException Si se produce un error al decodificar la
     *                                      cadena de cookie.
     */
    public LinkedList<Articulo> cookietoList(String cookie) throws UnsupportedEncodingException {
        cookie = URLDecoder.decode(cookie, "UTF-8");
        LinkedList<Articulo> carrito = new LinkedList<Articulo>();
        String[] cookies = cookie.split(":");
        for (String c : cookies) {
            c = URLDecoder.decode(c, "UTF-8");
            String[] cookieValue = c.split(",");
            if (cookieValue.length == 3) {
                String nombre = cookieValue[0];
                int cantidad = Integer.parseInt(cookieValue[1]);
                double precioUnitario = Double.parseDouble(cookieValue[2]);
                Articulo a = new Articulo();
                a.setCantidad(cantidad);
                a.setNombre(nombre);
                a.setPrecioUnitario(precioUnitario);
                carrito.add(a);
            }
        }
        return carrito;
    }

    /**
     * Convierte una lista de objetos Articulo en una cadena de cookie codificada.
     *
     * @param list La lista de objetos Articulo que representa el carrito de compra.
     * @return Una cadena de cookie codificada que representa el carrito de compra.
     * @throws UnsupportedEncodingException Si se produce un error al codificar la
     *                                      cadena de cookie.
     */
    public String listToCookie(LinkedList<Articulo> list) throws UnsupportedEncodingException {
        StringBuilder cookieBuilder = new StringBuilder();
        for (Articulo a : list) {
            if (list.size() > 1) {
                cookieBuilder.append(a.getNombre()).append(",").append(a.getCantidad()).append(",")
                        .append(a.getPrecioUnitario()).append(":");
            } else {
                cookieBuilder.append(a.getNombre()).append(",").append(a.getCantidad()).append(",")
                        .append(a.getPrecioUnitario());
            }
        }
        String cookie = cookieBuilder.toString();
        cookie = URLEncoder.encode(cookie, "UTF-8");
        return cookie;
    }

    /**
     * Busca una cookie con nombre "carritoCookie" en la matriz de cookies y la
     * convierte en una lista de objetos Articulo.
     *
     * @param cookies La matriz de cookies en la que se buscará la cookie.
     * @return Una lista de objetos Articulo que representa el carrito de compra
     *         encontrado en la cookie.
     * @throws UnsupportedEncodingException Si se produce un error al decodificar la
     *                                      cadena de cookie.
     */
    public LinkedList<Articulo> buscarCarritoEnCookies(Cookie[] cookies) throws UnsupportedEncodingException {
        LinkedList<Articulo> carrito = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("carritoCookie")) {
                carrito = cookietoList(c.getValue());
            }
        }
        return carrito;
    }

    /**
     * Comprueba si existe una cookie con el nombre especificado en la matriz de
     * cookies.
     *
     * @param cookies La matriz de cookies en la que se buscará la cookie.
     * @param nombre  El nombre de la cookie que se buscará.
     * @return true si la cookie existe, false si no.
     */
    public boolean existeCookie(Cookie[] cookies, String nombre) {
        for (Cookie c : cookies) {
            if (c.getName().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Cambia el tiempo de vida de una cookie con el nombre especificado en la
     * matriz de cookies.
     *
     * @param cookies  La matriz de cookies en la que se buscará la cookie.
     * @param nombre   El nombre de la cookie que se buscará.
     * @param maxAge   El nuevo tiempo de vida de la cookie en segundos. Un valor
     *                 negativo indica que la Cookie es temporal y se eliminará al
     *                 cerrar el navegador.
     * @param response La respuesta HTTP a la que se agregará la Cookie.
     */
    public void cambiarAgeCookie(Cookie[] cookies, String nombre, int maxAge, HttpServletResponse response) {
        for (Cookie c : cookies) {
            if (c.getName().equals(nombre)) {
                c.setMaxAge(maxAge);
                response.addCookie(c);
            }
        }
    }
    /**
     * Calcula el costo total multiplicando el precio unitario por la cantidad.
     *
     * @param precioUnitario El precio unitario del artículo.
     * @param cantidad       La cantidad de artículos en el carrito.
     * @return El costo total calculado.
     */
    public double calcularTotal(double precioUnitario, int cantidad) {
        return precioUnitario * cantidad;
    }

    /**
     * Calcula el resultado del IVA aplicando una tasa del 21% al total
     * proporcionado.
     *
     * @param total El total al que se aplicará el IVA.
     * @return El resultado del IVA redondeado a dos decimales.
     */
    public double resultadoIva(double total) {
        total = total * 0.21;
        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * Calcula el resultado total sumando el total y el IVA proporcionados.
     *
     * @param total El total de la compra.
     * @param iva   El resultado del IVA a sumar.
     * @return El resultado total redondeado a dos decimales.
     */
    public double resultadoTotal(double total, double iva) {
        double resultado = total + iva;
        return Math.round(resultado * 100.0) / 100.0;
    }

    /**
     * Crea una nueva Cookie con el nombre, valor y tiempo de vida especificados,
     * y la agrega a la respuesta HTTP.
     *
     * @param nombre   El nombre de la Cookie.
     * @param valor    El valor de la Cookie.
     * @param maxAge   El tiempo de vida de la Cookie en segundos. Un valor negativo
     *                 indica que la Cookie es temporal y se eliminará al cerrar el
     *                 navegador.
     * @param response La respuesta HTTP a la que se agregará la Cookie.
     * @return La Cookie recién creada.
     */
    public Cookie crearCookie(String nombre, String valor, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(nombre, valor);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
        return cookie;
    }
}
