/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;

/**
 *
 * @author Arrunategui
 */
public class Ctrl_RegistrarVenta {
    //ultimo id de la cabecera
    public static int idCabeceraRegistrada;
    java.math.BigDecimal iDColVar;
    /**
     * **************************************************
     * metodo para guardar la cabecera de venta
     * **************************************************
     */
    public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            // Verificar si el idCliente existe en tb_cliente
            PreparedStatement checkCliente = cn.prepareStatement("SELECT * FROM tb_cliente WHERE idCliente = ?");
            checkCliente.setInt(1, objeto.getIdCliente());
            ResultSet rsCheck = checkCliente.executeQuery();
            if (!rsCheck.next()) {
                System.out.println("Error: El idCliente " + objeto.getIdCliente() + " no existe en la tabla tb_cliente.");
                return respuesta;
            }

            // Inserción en tb_cabecera_venta
            PreparedStatement consulta = cn.prepareStatement(
                    "INSERT INTO tb_cabecera_venta (idCliente, valorPagar, fechaVenta, estado) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            // Imprimir para depurar
            System.out.println("idCliente: " + objeto.getIdCliente());

            consulta.setInt(1, objeto.getIdCliente());
            consulta.setDouble(2, objeto.getValorPagar());
            consulta.setString(3, objeto.getFechaVenta());
            consulta.setInt(4, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            ResultSet rs = consulta.getGeneratedKeys();
            while (rs.next()) {
                idCabeceraRegistrada = rs.getInt(1); // Cambiado a getInt ya que es más común para IDs
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta: " + e);
        }
        return respuesta;
    }
    
     /**
     * **************************************************
     * metodo para guardar el detalle de venta
     * **************************************************
     */
    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_detalle_venta values(?,?,?,?,?,?,?,?,?)");
            //consulta.setInt(1, 0);//id
            consulta.setInt(1, idCabeceraRegistrada);
            consulta.setInt(2, objeto.getIdProducto());
            consulta.setInt(3, objeto.getCantidad());
            consulta.setDouble(4, objeto.getPrecioUnitario());
            consulta.setDouble(5, objeto.getSubTotal());
            consulta.setDouble(6, objeto.getDescuento());
            consulta.setDouble(7, objeto.getIva());
            consulta.setDouble(8, objeto.getTotalPagar());
            consulta.setInt(9, objeto.getEstado());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta2: " + e);
        }
        return respuesta;
    }
    
    
         /**
     * **************************************************
     * metodo para actualizar un producto
     * **************************************************
     */
    public boolean actualizar(CabeceraVenta objeto, int idCabeceraVenta) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "update tb_cabecera_venta set idCliente = ?, estado = ? "
                            + "where idCabeceraVenta ='" + idCabeceraVenta + "'");
            consulta.setInt(1, objeto.getIdCliente());
            consulta.setInt(2, objeto.getEstado());
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cabecera de venta: " + e);
        }
        return respuesta;
    }
    
   
    
}
