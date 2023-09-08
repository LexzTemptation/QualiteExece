/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

/**
 *
 * @author FELIPE.HERRERA
 */
public class EmpleadoDAB {

    ConecctionMySQL conMySQL = new ConecctionMySQL();

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public static int idEmpleado_user = 0;
    public static String nombre_user = "";
    public static String userNombre_user = "";
    public static String direccion_user = "";
    public static String telefono_user = "";
    public static String correo_user = "";
    public static String rol_user = "";

    public Empleado login(String user, String password) {

        String sql = "SELECT * FROM Empleado WHERE userName = ? AND contrasenia = ?";

        Empleado emp = new Empleado();

        conn = conMySQL.getConnection();

        try {

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                emp.setIdEmpleado(rs.getInt("idEmpleado"));
                idEmpleado_user = emp.getIdEmpleado();

                emp.setNombre(rs.getString("nombre"));
                nombre_user = emp.getNombre();

                emp.setUserName(rs.getString("userName"));
                userNombre_user = emp.getUserName();

                emp.setDireccion(rs.getString("direccion"));
                direccion_user = emp.getDireccion();

                emp.setTelefono(rs.getString("telefono"));
                telefono_user = emp.getTelefono();

                emp.setCorreo(rs.getString("correo"));
                correo_user = emp.getCorreo();

                emp.setRol(rs.getString("rol"));
                rol_user = emp.getRol();

            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al optener al empleado: " + e);
        }

        return emp;
    }
    
    public boolean insertarEmpleado(Empleado emp){
        
        boolean res = false;
        
        String sql = "INSERT INTO Empleado VALUES(?,?,?,?,?,?,?,?,NOW(),NOW())";
        
        try {
            
            conn = conMySQL.getConnection();
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, emp.getIdEmpleado());
            pstmt.setString(2,emp.getNombre());
            pstmt.setString(3,emp.getUserName());
            pstmt.setString(4,emp.getRol());
            pstmt.setString(5,emp.getDireccion());
            pstmt.setString(6,emp.getTelefono());
            pstmt.setString(7,emp.getCorreo());
            pstmt.setString(8, emp.getContrasenia());
            
            
            pstmt.execute();
            
            res = true;
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al insertar el registro del empleado: " + e);
            
            res = false;
        }
        
        return res;
    }
}
