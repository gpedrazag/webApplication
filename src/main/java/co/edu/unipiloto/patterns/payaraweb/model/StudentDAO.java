/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unipiloto.patterns.payaraweb.model;

import java.util.List;

/**
 *
 * @author USUARIO
 */
public interface StudentDAO {
  
    public List<Estudiante> getAllStudents();
    public void updateStudent (Estudiante est);
    public void deleteStudent (Estudiante est);
    public void addStudent (Estudiante est);
    public Estudiante searchStudent(int id);
    
    
}
