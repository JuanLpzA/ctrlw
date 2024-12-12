package vista;

import controlador.Ctrl_Analisis;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import modelo.Venta;

public class InterAnalisisDatos extends JInternalFrame {
    private final Ctrl_Analisis controlador;
    private final JTextArea areaResultados;
    private List<Venta> listaVentas;
    
    public InterAnalisisDatos() {
        super("Análisis de Datos", true, true, true, true);
        controlador = new Ctrl_Analisis();
        
        // Configuración del frame
        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior para botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JButton btnCargarDatos = new JButton("Cargar Datos");
        JButton btnOrdenar = new JButton("Ordenar por Valor");
        JButton btnBuscar = new JButton("Buscar por Valor");
        
        panelBotones.add(btnCargarDatos);
        panelBotones.add(btnOrdenar);
        panelBotones.add(btnBuscar);
        
        // Área de resultados
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaResultados);
        
        // Agregar componentes al frame
        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Eventos
        btnCargarDatos.addActionListener(e -> cargarDatos());
        btnOrdenar.addActionListener(e -> ordenarDatos());
        btnBuscar.addActionListener(e -> buscarVenta());
        
        setVisible(true);
    }
    
    private void cargarDatos() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            System.out.println("Iniciando carga de datos...");
            listaVentas = controlador.obtenerVentasParaAnalisis();
            
            if (listaVentas != null && !listaVentas.isEmpty()) {
                mostrarDatos("Datos de Ventas Cargados:", listaVentas);
            } else {
                areaResultados.setText("No se encontraron datos para mostrar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar los datos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void ordenarDatos() {
        if (listaVentas == null || listaVentas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para ordenar");
            return;
        }
        
        controlador.quickSortVentas(listaVentas, 0, listaVentas.size() - 1);
        mostrarDatos("Datos Ordenados por Valor:", listaVentas);
    }
    
    private void buscarVenta() {
        if (listaVentas == null || listaVentas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para buscar");
            return;
        }
        
        String input = JOptionPane.showInputDialog(this, "Ingrese el valor a buscar:");
        if (input != null && !input.trim().isEmpty()) {
            try {
                double valorBuscado = Double.parseDouble(input);
                Venta ventaEncontrada = controlador.busquedaBinariaVenta(listaVentas, valorBuscado);
                
                if (ventaEncontrada != null) {
                    areaResultados.setText("Venta encontrada:\n" + ventaEncontrada.toString());
                } else {
                    areaResultados.setText("No se encontró una venta con ese valor");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido");
            }
        }
    }
    
    private void mostrarDatos(String titulo, List<Venta> ventas) {
        StringBuilder sb = new StringBuilder(titulo + "\n\n");
        for (Venta venta : ventas) {
            sb.append(venta.toString()).append("\n");
        }
        areaResultados.setText(sb.toString());
    }
}