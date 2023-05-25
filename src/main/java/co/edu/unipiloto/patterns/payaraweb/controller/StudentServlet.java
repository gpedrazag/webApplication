/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.unipiloto.patterns.payaraweb.controller;

import co.edu.unipiloto.patterns.payaraweb.model.Estudiante;
import co.edu.unipiloto.patterns.payaraweb.model.StudentDAO;
import co.edu.unipiloto.patterns.payaraweb.model.StudentDAOImplementation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USUARIO
 */
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;
    private List<Estudiante> lista;
    private Estudiante student;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        studentDAO = new StudentDAOImplementation();
        lista = new ArrayList<Estudiante>();

        String firstName = " ", lastName = " ", resultado = " ";
        String idStr = " ", yearStr = "0";
        int id = 0, yearLevel = 0;

        String accion = request.getParameter("action");

        idStr = request.getParameter("studentId");
        id = Integer.parseInt(idStr);
        if (!accion.equals("Search")) {
            firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");

            yearStr = request.getParameter("yearLevel");
            yearLevel = Integer.parseInt(yearStr);
        }

        if (accion.equals("Add")) {
            student = new Estudiante(id, firstName, lastName, yearLevel);
            if (studentDAO.searchStudent(id) == null) {
                studentDAO.addStudent(student);
                resultado = "ADD ok";
            }
        }
        if (accion.equals("Edit")) {
            student = new Estudiante(id, firstName, lastName, yearLevel);
            if (studentDAO.searchStudent(id) != null) {
                System.out.println("Actualizando id " + id + " Nombre " + firstName + " Apellido " + lastName + " Semestre " + yearLevel);
                studentDAO.updateStudent(student);
                resultado = "EDIT ok";
            } else {
                System.out.println("ID no encontrado" + id + " NO se actualiza el registro ");
                resultado="Id no encontrado";
            }
        }
        if (accion.equals("Delete")) {
            student = new Estudiante(id, firstName, lastName, yearLevel);
            studentDAO.deleteStudent(student);
        }
        if (accion.equals("Search")) {

            student = studentDAO.searchStudent(id);
            if (student == null) {
                resultado = "No encontrado";
                student = new Estudiante();
            }
        }

        //Consultar la lista de estudiantes
        lista = studentDAO.getAllStudents();
        //Asignar valores a variables en index.jsp
        request.setAttribute("estado", resultado);
        request.setAttribute("student", student);
        request.setAttribute("allStudents", lista);
        request.getRequestDispatcher("index.jsp").forward(request, response);

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
