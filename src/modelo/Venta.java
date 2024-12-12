package Modelo;

public class Venta {
    private int idVenta;
    private double total;
    private String fecha;
    
    public Venta() {
    }
    
    public Venta(int idVenta, double total, String fecha) {
        this.idVenta = idVenta;
        this.total = total;
        this.fecha = fecha;
    }
    
    public int getIdVenta() {
        return idVenta;
    }
    
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}