package Controlador;

import conexion.Conexion;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Ctrl_Analisis {
    private Connection conexion;
    
    public Ctrl_Analisis() {
        try {
            conexion = Conexion.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e.getMessage());
        }
    }
    
    public List<Venta> obtenerVentasParaAnalisis() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT idventa, total, fecha FROM tb_venta";
        
        try (PreparedStatement pst = conexion.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("idventa"));
                venta.setTotal(rs.getDouble("total"));
                venta.setFecha(rs.getString("fecha"));
                ventas.add(venta);
            }
        }
        return ventas;
    }
    
    public void quickSortVentas(List<Venta> ventas, int inicio, int fin) {
        if (ventas == null || ventas.isEmpty()) {
            return;
        }
        if (inicio < fin) {
            int pivotIndex = particionQuickSort(ventas, inicio, fin);
            quickSortVentas(ventas, inicio, pivotIndex - 1);
            quickSortVentas(ventas, pivotIndex + 1, fin);
        }
    }
    
    private int particionQuickSort(List<Venta> ventas, int inicio, int fin) {
        double pivot = ventas.get(fin).getTotal();
        int i = inicio - 1;
        
        for (int j = inicio; j < fin; j++) {
            if (ventas.get(j).getTotal() <= pivot) {
                i++;
                Venta temp = ventas.get(i);
                ventas.set(i, ventas.get(j));
                ventas.set(j, temp);
            }
        }
        
        Venta temp = ventas.get(i + 1);
        ventas.set(i + 1, ventas.get(fin));
        ventas.set(fin, temp);
        
        return i + 1;
    }
}