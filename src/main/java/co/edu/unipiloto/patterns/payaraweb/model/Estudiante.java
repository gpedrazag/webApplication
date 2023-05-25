/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unipiloto.patterns.payaraweb.model;

/**
 *
 * @author USUARIO
 */
public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private int semestre;
    
    //Constructor

    public Estudiante() {
    }
    
    

    public Estudiante(int id, String nombre, String apellido, int semestre) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.semestre = semestre;
    }
    
    
    //Metodos get y set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    
}
