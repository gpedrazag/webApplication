/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unipiloto.patterns.payaraweb.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class StudentDAOImplementation implements StudentDAO {

    //Atributos
    private String driver;
    private String url;
    private String login;
    private String password;
    private String sentencia;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;
    private Estudiante est;

    //Constructor
    public StudentDAOImplementation() {
        driver = "org.apache.derby.jdbc.ClientDriver";
        url = "jdbc:derby://localhost:1527/sample";
        login = "app";
        password = "app";
        sentencia = "";
        connection = null;
        statement = null;
        rs = null;
    }

    //Metodos 
    protected void conectar() {
        try {
            //Cargar los controladores para el acceso a la BD
            Class.forName(driver);
            System.out.println("Cargar los controladores para el acceso a la BD");
            //Establecer la conexión con la BD Empresa
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error 1-" + cnfe.getMessage());
        } catch (SQLException sqle) {
            System.out.println("Error 2-" + sqle.getMessage());
        }

    }

    public void desconectar(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Estudiante> getAllStudents() {
        if (connection == null) {
            conectar();
        }
        List<Estudiante> lista = new ArrayList();

        sentencia = "SELECT * FROM APP.ESTUDIANTE"; //SELECT * FROM APP.MICRO_MARKET 
        try {
            //Crear una sentencia
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //Crear un objeto ResultSet para guardar lo obtenido
            //y ejecutar la sentencia SQL
            rs = statement.executeQuery(sentencia);
            System.out.println("consulta ok");
            if (rs == null) {
                System.out.println("vacio");
            }
            System.out.println(rs.first() + " " + rs.getRow() + " " + rs.getCursorName());
            do {
                //int id=rs.getInt(1);
                //System.out.println("rs next "+rs.getString(1)+" "+rs.getDouble(2));

                int id = rs.getInt(1);
                System.out.println("Id " + id);
                String nombre = rs.getString(2);
                System.out.println("Nombre " + nombre);
                String apellido = rs.getString(3);
                System.out.println("Apellido " + apellido);
                int semestre = rs.getInt(4);
                System.out.println("Semestre " + semestre);
                /*              String cargo = rs.getString(5);
                System.out.println("Cargo " + cargo);
               
                Double salario = rs.getDouble(6);
                System.out.println("Salario " + salario);
                 */
                lista.add(new Estudiante(id, nombre, apellido, semestre));
                System.out.println(id + "\t\t" + nombre + "\t\t" + apellido);
            } while (rs.next());

        } catch (SQLException sqle) {
            System.out.println("Excepcion " + sqle.getSQLState());
        }

        return lista;

    }

    @Override
    public void updateStudent(Estudiante est) {
        if (connection == null) {
            conectar();
        }
    }

    @Override
    public void deleteStudent(Estudiante est) {
        if (connection == null) {
            conectar();
        }
    }

    @Override
    public void addStudent(Estudiante est) {
        if (connection == null) {
            conectar();
        }
        System.out.println("Guardar estudiante");
        //est=new Estudiante(4,"Pedro","Lozano",1);
        try {
            sentencia = "INSERT INTO estudiante VALUES("
                    + est.getId() + ",'"
                    + est.getNombre() + "','"
                    + est.getApellido() + "',"
                    + est.getSemestre() + ""
                    + ")";

            System.out.println("Sentencia " + sentencia);
            //Crear una sentencia
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            //Crear un objeto ResultSet para guardar lo obtenido
            //y ejecutar la sentencia SQL
            statement.executeUpdate(sentencia);
        } catch (SQLException sqle) {
            System.out.println("Error en " + sqle.getSQLState());
        }

    }

    @Override
    public Estudiante searchStudent(int idBuscar) {
        Estudiante est = null;
        if (connection == null) {
            conectar();
        }
        sentencia = "SELECT * FROM APP.ESTUDIANTE WHERE ID=" + idBuscar + ";";

        try {

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sentencia);
            System.out.println("consulta individual ok");
            if (rs == null) {
                System.out.println("vacio");
            }
            rs.first();
            // System.out.println(rs.first() + " " + rs.getRow() + " " + rs.getCursorName());
            int id = rs.getInt(1);
            System.out.println("Id " + id);
            String nombre = rs.getString(2);
            System.out.println("Nombre " + nombre);
            String apellido = rs.getString(3);
            System.out.println("Apellido " + apellido);
            int semestre = rs.getInt(4);
            System.out.println("Semestre " + semestre);
            /*              String cargo = rs.getString(5);
                System.out.println("Cargo " + cargo);
               
                Double salario = rs.getDouble(6);
                System.out.println("Salario " + salario);
             */
            est = new Estudiante(id, nombre, apellido, semestre);
            System.out.println(id + "\t\t" + nombre + "\t\t" + apellido);
        } catch (SQLException sqle) {
            System.out.println("Excepcion " + sqle.getSQLState());
        }

        return est;

    }

}
