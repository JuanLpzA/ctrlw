/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controlador.Ctrl_Analisis;
import Modelo.Venta;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class InterAnalisisDatos extends JInternalFrame {
    private JTable tablaVentas;
    private DefaultTableModel modelo;
    private Ctrl_Analisis controlador;
    private List<Venta> listaVentas;
    
    public InterAnalisisDatos() {
        this.setTitle("Análisis de Ventas");
        this.setSize(800, 600);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
        
        controlador = new Ctrl_Analisis();
        listaVentas = new ArrayList<>();
        inicializarComponentes();
        cargarDatos();
    }
    
    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        
        JButton btnOrdenarTotal = new JButton("Ordenar por Total (QuickSort)");
        panelBotones.add(btnOrdenarTotal);
        
        String[] columnas = {"ID", "Total", "Fecha"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaVentas = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaVentas);
        
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        btnOrdenarTotal.addActionListener(e -> ordenarPorTotal());
        
        this.add(panel);
    }
    
    private void cargarDatos() {
        try {
            modelo.setRowCount(0);
            listaVentas = controlador.obtenerVentasParaAnalisis();
            
            if (listaVentas != null) {
                for (Venta venta : listaVentas) {
                    modelo.addRow(new Object[]{
                        venta.getIdVenta(),
                        venta.getTotal(),
                        venta.getFecha()
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar datos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void ordenarPorTotal() {
        try {
            if (listaVentas != null && !listaVentas.isEmpty()) {
                controlador.quickSortVentas(listaVentas, 0, listaVentas.size() - 1);
                actualizarTabla();
                JOptionPane.showMessageDialog(this, 
                    "Datos ordenados por total usando QuickSort\n" +
                    "Este algoritmo tiene una complejidad de O(n log n) en promedio",
                    "Ordenamiento Completado",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "No hay datos para ordenar",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al ordenar datos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarTabla() {
        modelo.setRowCount(0);
        for (Venta venta : listaVentas) {
            modelo.addRow(new Object[]{
                venta.getIdVenta(),
                venta.getTotal(),
                venta.getFecha()
            });
        }
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}