/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.albarregas.beans.Articulo;

/**
 *
 * @author Pedro Lazaro
 */
@WebServlet(name = "CarroController", urlPatterns = {"/CarroController"})
public class CarroController extends HttpServlet {

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
        LinkedList<Articulo> carrito = (LinkedList<Articulo>) request.getSession().getAttribute("carrito");
        String boton = request.getParameter("button");
        String[] botonValue = boton.split(",");

        switch(botonValue[0]){
            case "mas":
                for (Articulo a : carrito) {
                    if (a.getNombre().equals(botonValue[1])) {
                        a.setCantidad(a.getCantidad() + 1);
                    }
                }
            break;
            case "menos":
                for (Articulo a : carrito) {
                    if (a.getNombre().equals(botonValue[1])) {
                        a.setCantidad(a.getCantidad() - 1);
                    }
                }
            break;
            case "eliminarpr":
                for (Articulo a : carrito) {
                    if (a.getNombre().equals(botonValue[1])) {
                        carrito.remove(a);
                    }
                }
            break;
            case "vaciar":
                carrito.clear();
            break;
            case "comprar":
                request.getRequestDispatcher("JSP/factura.jsp").forward(request, response);
            break;
            case "volver":
                request.getRequestDispatcher("JSP/tienda.jsp").forward(request, response);
            break;
            case "terminar": 
                request.getSession().removeAttribute("carrito");
                Cookie[] cookies = request.getCookies();
                for (Cookie c : cookies) {
                    if (c.getName().equals("carritoCookie")) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
                request.getRequestDispatcher("JSP/tienda.jsp").forward(request, response);
            break;
        }

        if (!"terminar".equals(botonValue[0])) {
            if (carrito.isEmpty()) {
                request.getSession().removeAttribute("carrito");
                request.getRequestDispatcher("JSP/tienda.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("carrito", carrito);
                request.getRequestDispatcher("JSP/verCarrito.jsp").forward(request, response);
            }
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
