/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.albarregas.beans.Articulo;
import es.albarregas.models.Calculator;

/**
 *
 * @author Pedro Lazaro
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {



    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        LinkedList<Articulo> carrito = new LinkedList<>();

        if (request.getSession().getAttribute("carrito") != null) {
            carrito = (LinkedList<Articulo>) request.getSession().getAttribute("carrito");
        }


        Iterator<Articulo> iterator = carrito.iterator();
        boolean found = false;
        Calculator calc = new Calculator();
        String botonPulsado = request.getParameter("button");
        String cantidad = request.getParameter("cantidad");
        String productos = request.getParameter("productos");
        switch (botonPulsado) {
            case "ver":
                request.getRequestDispatcher("JSP/verCarrito.jsp").forward(request, response);
                break;
            case "anadir":
                if(carrito.isEmpty() || carrito == null){
                    Articulo a = new Articulo();
                    a.setNombre(productos.split(",")[1]);
                    a.setPrecioUnitario(Double.parseDouble(productos.split(",")[0]));
                    a.setCantidad(Integer.parseInt(cantidad));
                    carrito.add(a);
                }else {
                    while (iterator.hasNext()) {
                        Articulo articulo = iterator.next();
                        if (articulo.getNombre().equals(productos.split(",")[1])) {
                            articulo.setCantidad(articulo.getCantidad() + Integer.parseInt(cantidad));
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        Articulo a = new Articulo();
                        a.setNombre(productos.split(",")[1]);
                        a.setPrecioUnitario(Double.parseDouble(productos.split(",")[0]));
                        a.setCantidad(Integer.parseInt(cantidad));
                        carrito.add(a);
                    }
                }       
                Cookie carritoCookie = new Cookie("carritoCookie", calc.listToCookie(carrito));
                carritoCookie.setMaxAge(86400);
                response.addCookie(carritoCookie);
                request.getSession().setAttribute("carrito", carrito);
                request.setAttribute("cantidad", cantidad);
                request.setAttribute("nombre", productos.split(",")[1]);
                request.getRequestDispatcher("JSP/tienda.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("JSP/factura.jsp").forward(request, response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
