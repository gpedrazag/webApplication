/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.unipiloto.patterns.payaraweb;

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
        
        String idStr = request.getParameter("studentId");
        int id = Integer.parseInt(idStr);
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        
        String yearStr = request.getParameter("yearLevel");
        int yearLevel = Integer.parseInt(yearStr);
        
        String accion = request.getParameter("action");
        
        if (accion.equals("Add")) {
            student = new Estudiante(id, firstName, lastName, yearLevel);
            if (studentDAO.searchStudent(id)==null) {
                studentDAO.addStudent(student);
                
            }
        }
        if (accion.equals("Edit")) {
            student = new Estudiante(id, firstName, lastName, yearLevel);
            studentDAO.updateStudent(student);
        }
        if (accion.equals("Delete")) {
            student = new Estudiante(id, firstName, lastName, yearLevel);
            studentDAO.deleteStudent(student);
        }
        if (accion.equals("Search")) {
            
            student= studentDAO.searchStudent(id);
            if (student==null)
                student=new Estudiante();
        }

        //lista.add(student);
        //lista.add(new Estudiante (1,"Pedro","Picapiedra",3));
        //studentDAO.addStudent(student);
        lista = studentDAO.getAllStudents();
        //studentFacade.create(student);
        request.setAttribute("estado", accion);
        //String.valueOf(lista.get(0).getSemestre()));
        
        request.setAttribute("student", student);
        request.setAttribute("allStudents", lista);
        //studentDAO.getAllStudents()
        request.getRequestDispatcher("index.jsp").forward(request, response);

        /*       response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
            out.println( "Hola "+idStr+" "+firstName+" "+lastName);
            out.println("</body>");
            out.println("</html>");
        }
         */
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
