package modelo;

import java.util.Date;

public class Venta {
    private int idCabeceraVenta;
    private int idCliente;
    private double valorPagar;
    private Date fechaVenta;
    private int estado;
    
    public Venta() {
    }
    
    public int getIdCabeceraVenta() {
        return idCabeceraVenta;
    }
    
    public void setIdCabeceraVenta(int idCabeceraVenta) {
        this.idCabeceraVenta = idCabeceraVenta;
    }
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public double getValorPagar() {
        return valorPagar;
    }
    
    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }
    
    public Date getFechaVenta() {
        return fechaVenta;
    }
    
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    
    public int getEstado() {
        return estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Venta #" + idCabeceraVenta + 
               " | Cliente: " + idCliente + 
               " | Valor: $" + String.format("%.2f", valorPagar) + 
               " | Fecha: " + fechaVenta;
    }
}