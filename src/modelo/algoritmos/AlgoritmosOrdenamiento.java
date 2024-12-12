package modelo.algoritmos;

import java.util.List;
import modelo.Venta;

public class AlgoritmosOrdenamiento {
    
    public static void quickSort(List<Venta> lista, int inicio, int fin) {
        if (inicio < fin) {
            int particion = particionQuickSort(lista, inicio, fin);
            quickSort(lista, inicio, particion - 1);
            quickSort(lista, particion + 1, fin);
        }
    }
    
    private static int particionQuickSort(List<Venta> lista, int inicio, int fin) {
        Venta pivote = lista.get(fin);
        int i = inicio - 1;
        
        for (int j = inicio; j < fin; j++) {
            if (lista.get(j).getValorPagar() <= pivote.getValorPagar()) {
                i++;
                intercambiar(lista, i, j);
            }
        }
        
        intercambiar(lista, i + 1, fin);
        return i + 1;
    }
    
    private static void intercambiar(List<Venta> lista, int i, int j) {
        Venta temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
}