package modelo.algoritmos;

import java.util.List;
import modelo.Venta;

public class AlgoritmosBusqueda {
    
    public static Venta busquedaBinaria(List<Venta> lista, double valorBuscado) {
        int izquierda = 0;
        int derecha = lista.size() - 1;
        
        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;
            double valorActual = lista.get(medio).getValorPagar();
            
            if (Math.abs(valorActual - valorBuscado) < 0.001) {
                return lista.get(medio);
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