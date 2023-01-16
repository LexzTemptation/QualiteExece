/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exece.oq.core;

import com.exece.oq.db.ConexionMySQL;
import com.exece.oq.model.Cliente;
import com.exece.oq.model.Persona;
import java.sql.*;
import java.util.*;

/**
 *
 * @author adria
 */
public class ControllerCliente {

    public int insert(Cliente c) throws Exception {
        String sql = "{call insertarCliente(?,?,?,?,?,?,?,"
                +//datos personales (primeros siete)
                "?,?,?,?,?,?,?,"
                + //datos personales (últimos siete)
                "?,?,?)}";//valores de retorno
        int idPersonaGenerado = -1;
        int idClienteGenerado = -1;
        String numeroUnicoGenerado = "";

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        CallableStatement cstmt = conn.prepareCall(sql);

        //primeros siete datos del cliente
        cstmt.setString(1, c.getPersona().getNombre());
        cstmt.setString(2, c.getPersona().getApellidoPaterno());
        cstmt.setString(3, c.getPersona().getApellidoMaterno());
        cstmt.setString(4, c.getPersona().getGenero());
        cstmt.setString(5, c.getPersona().getFechaNacimiento());
        cstmt.setString(6, c.getPersona().getCalle());
        cstmt.setString(7, c.getPersona().getNumero());
        //segundos siete datos del cliente
        cstmt.setString(8, c.getPersona().getColonia());
        cstmt.setString(9, c.getPersona().getCp());
        cstmt.setString(10, c.getPersona().getCiudad());
        cstmt.setString(11, c.getPersona().getEstado());
        cstmt.setString(12, c.getPersona().getTelCasa());
        cstmt.setString(13, c.getPersona().getTelMovil());
        cstmt.setString(14, c.getPersona().getEmail());
        //Valores de retorno
        cstmt.registerOutParameter(15, Types.INTEGER);
        cstmt.registerOutParameter(16, Types.INTEGER);
        cstmt.registerOutParameter(17, Types.VARCHAR);

        cstmt.executeUpdate();
        //recuperar id's
        idPersonaGenerado = cstmt.getInt(15);
        idClienteGenerado = cstmt.getInt(16);
        numeroUnicoGenerado = cstmt.getString(17);
        //enviar id's previos)
        c.getPersona().setIdPersona(idPersonaGenerado);
        c.setIdCliente(idClienteGenerado);
        c.setNumeroUnico(numeroUnicoGenerado);

        cstmt.close();
        connMySQL.close();
        return idClienteGenerado;
    }

    public void update(Cliente c) throws Exception {
        String sql = "{call actualizarCliente(?,?,?,?,?,?,?,"
                +//datos personales (primeros siete)
                "?,?,?,?,?,?,?,"
                + //datos personales (últimos siete)
                "?)}";//id persona
        ConexionMySQL connMysql = new ConexionMySQL();

        Connection conn = connMysql.open();

        CallableStatement cstmt = conn.prepareCall(sql);
        //enviar nuevos datos para persona
        cstmt.setString(1, c.getPersona().getNombre());
        cstmt.setString(2, c.getPersona().getApellidoPaterno());
        cstmt.setString(3, c.getPersona().getApellidoMaterno());
        cstmt.setString(4, c.getPersona().getGenero());
        cstmt.setString(5, c.getPersona().getFechaNacimiento());
        cstmt.setString(6, c.getPersona().getCalle());
        cstmt.setString(7, c.getPersona().getNumero());
        cstmt.setString(8, c.getPersona().getColonia());
        cstmt.setString(9, c.getPersona().getCp());
        cstmt.setString(10, c.getPersona().getCiudad());
        cstmt.setString(11, c.getPersona().getEstado());
        cstmt.setString(12, c.getPersona().getTelCasa());
        cstmt.setString(13, c.getPersona().getTelMovil());
        cstmt.setString(14, c.getPersona().getEmail());
        //enviar id persona
        cstmt.setInt(15, c.getPersona().getIdPersona());

        cstmt.executeUpdate();

        cstmt.close();
        connMysql.close();
    }

    /*
    void delete(int id) throw Exception{}
     */
    public List<Cliente> getAll(String filtro) throws Exception {
        String sql = "SELECT * FROM v_clientes";

        ConexionMySQL connMysql = new ConexionMySQL();

        Connection conn = connMysql.open();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<Cliente> clientes = new ArrayList<>();

        while (rs.next()) {
            clientes.add(fill(rs));
        }

        rs.close();
        pstmt.close();
        connMysql.close();

        return clientes;
    }

    private Cliente fill(ResultSet rs) throws Exception {
        Cliente c = new Cliente();
        Persona p = new Persona();

        p.setApellidoMaterno(rs.getString("apellidoMaterno"));
        p.setApellidoPaterno(rs.getString("apellidoPaterno"));
        p.setCalle(rs.getString("calle"));
        p.setCiudad(rs.getString("ciudad"));
        p.setColonia(rs.getString("colonia"));
        p.setCp(rs.getString("cp"));
        p.setEmail(rs.getString("email"));
        p.setEstado(rs.getString("estado"));
        p.setFechaNacimiento(rs.getString("fechaNacimiento"));
        p.setGenero(rs.getString("genero"));
        p.setIdPersona(rs.getInt("idPersona"));
        p.setNombre(rs.getString("nombre"));
        p.setNumero(rs.getString("numero"));
        p.setTelCasa(rs.getString("telcasa"));
        p.setTelMovil(rs.getString("telmovil"));
        c.setEstatus(rs.getInt("estatus"));
        c.setIdCliente(rs.getInt("idCliente"));
        c.setNumeroUnico(rs.getString("numeroUnico"));
        c.setPersona(p);

        return c;
    }

    public int delete(int id) throws Exception {
        String sql = "UPDATE cliente SET estatus = 0 WHERE idCliente = " + id;

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        CallableStatement cstmt = conn.prepareCall(sql);

        int respuesta = cstmt.executeUpdate();

        return respuesta;
    }
}
