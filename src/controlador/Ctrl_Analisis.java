package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.Conexion;

public class Ctrl_Analisis {
    
    public List<Venta> obtenerVentasParaAnalisis() {
        List<Venta> listaVentas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.conectar();
            if (conn != null) {
                String sql = "SELECT idCabeceraVenta, idCliente, valorPagar, fechaVenta, estado " +
                           "FROM tb_cabecera_venta " +
                           "WHERE estado = 1";
                           
                System.out.println("Ejecutando consulta: " + sql);
                
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    Venta venta = new Venta();
                    venta.setIdCabeceraVenta(rs.getInt("idCabeceraVenta"));
                    venta.setIdCliente(rs.getInt("idCliente"));
                    venta.setValorPagar(rs.getDouble("valorPagar"));
                    venta.setFechaVenta(rs.getDate("fechaVenta"));
                    venta.setEstado(rs.getInt("estado"));
                    listaVentas.add(venta);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }
        return listaVentas;
    }
    
    public void quickSortVentas(List<Venta> ventas, int inicio, int fin) {
        if (inicio < fin) {
            int particion = particionQuickSort(ventas, inicio, fin);
            quickSortVentas(ventas, inicio, particion - 1);
            quickSortVentas(ventas, particion + 1, fin);
        }
    }
    
    private int particionQuickSort(List<Venta> ventas, int inicio, int fin) {
        Venta pivote = ventas.get(fin);
        int i = inicio - 1;
        
        for (int j = inicio; j < fin; j++) {
            if (ventas.get(j).getValorPagar() <= pivote.getValorPagar()) {
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
    
    public Venta busquedaBinariaVenta(List<Venta> ventas, double valorBuscado) {
        int izquierda = 0;
        int derecha = ventas.size() - 1;
        
        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            double valorActual = ventas.get(medio).getValorPagar();
            
            if (Math.abs(valorActual - valorBuscado) < 0.001) {
                return ventas.get(medio);
            }
            
            if (valorActual < valorBuscado) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return null;
    }
}