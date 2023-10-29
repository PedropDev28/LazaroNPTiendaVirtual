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
import es.albarregas.models.Calculator;

/**
 *
 * @author Pedro Lazaro
 */
@WebServlet(name = "CargaController", urlPatterns = { "/CargaController" })
public class CargaController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Calculator calculator = new Calculator();
        String disabled = "";
        LinkedList<Articulo> carrito = (LinkedList<Articulo>) request.getSession().getAttribute("carrito");
        Cookie[] cookies = request.getCookies();
        if (carrito != null || cookies != null) {
            carrito = calculator.buscarCarritoEnCookies(cookies);
            if (carrito == null) {
                disabled = "disabled";
            }
            request.getSession().setAttribute("disabled", disabled);
            request.getSession().setAttribute("carrito", carrito);
            request.getRequestDispatcher("JSP/tienda.jsp").forward(request, response);
        } else {
            disabled = "disabled";
            request.getSession().setAttribute("disabled", disabled);
            request.getRequestDispatcher("JSP/tienda.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
