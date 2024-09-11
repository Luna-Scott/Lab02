import java.util.Random;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] tamanos = {100, 500, 1000, 5000, 10000}; 
        System.out.println("Seleccione el metodo de ordenamiento:");
        System.out.println("1. Burbuja");
        System.out.println("2. Insercion");
        System.out.println("3. Seleccion");
        System.out.println("4. Mergesort");
        System.out.print("Ingrese su opcion: ");
        int opcion = scanner.nextInt();
        
        for (int tamaño : tamanos) {
            double[] arregloOriginal = generarArregloAleatorio(tamaño);
            double[] arregloCopia;
            System.out.println(" ");
            System.out.println(" -> Resultados <- ");
            System.out.println("\nOrdenando un arreglo de tamaño: " + tamaño);
            
            switch (opcion) {
                case 1 -> {
                    arregloCopia = arregloOriginal.clone();
                    long inicioBurbuja = System.nanoTime();
                    ordenarBurbuja(arregloCopia);
                    long tiempoBurbuja = System.nanoTime() - inicioBurbuja;
                    System.out.println("Tiempo para Burbuja: " + tiempoBurbuja + " nanosegundos");
                }
                case 2 -> {
                    arregloCopia = arregloOriginal.clone();
                    long inicioInsercion = System.nanoTime();
                    ordenarInsercion(arregloCopia);
                    long tiempoInsercion = System.nanoTime() - inicioInsercion;
                    System.out.println("Tiempo para Inserción: " + tiempoInsercion + " nanosegundos");
                }
                case 3 -> {
                    arregloCopia = arregloOriginal.clone();
                    long inicioSeleccion = System.nanoTime();
                    ordenarSeleccion(arregloCopia);
                    long tiempoSeleccion = System.nanoTime() - inicioSeleccion;
                    System.out.println("Tiempo para Selección: " + tiempoSeleccion + " nanosegundos");
                }
                case 4 -> {
                    arregloCopia = arregloOriginal.clone();
                    long inicioMergesort = System.nanoTime();
                    ordenarMergesort(arregloCopia, 0, arregloCopia.length - 1);
                    long tiempoMergesort = System.nanoTime() - inicioMergesort;
                    System.out.println("Tiempo para Mergesort: " + tiempoMergesort + " nanosegundos");
                }
                default -> {
                    System.out.println("Opción no válida.");
                    return;
                }
            }
        }
    }
    
    public static double[] generarArregloAleatorio(int tamaño) {
        double[] arreglo = new double[tamaño];
        Random random = new Random();
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = random.nextDouble() * 1000;
        }
        return arreglo;
    }

    public static void ordenarBurbuja(double[] arreglo) {
        int n = arreglo.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    double temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
        }
    }

    public static void ordenarInsercion(double[] arreglo) {
        int n = arreglo.length;
        for (int i = 1; i < n; i++) {
            double clave = arreglo[i];
            int j = i - 1;
            while (j >= 0 && arreglo[j] > clave) {
                arreglo[j + 1] = arreglo[j];
                j = j - 1;
            }
            arreglo[j + 1] = clave;
        }
    }

    public static void ordenarSeleccion(double[] arreglo) {
        int n = arreglo.length;
        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < n; j++) {
                if (arreglo[j] < arreglo[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }
            double temp = arreglo[indiceMinimo];
            arreglo[indiceMinimo] = arreglo[i];
            arreglo[i] = temp;
        }
    }

    public static void ordenarMergesort(double[] arreglo, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int mitad = (izquierda + derecha) / 2;
            ordenarMergesort(arreglo, izquierda, mitad);
            ordenarMergesort(arreglo, mitad + 1, derecha);
            merge(arreglo, izquierda, mitad, derecha);
        }
    }

    public static void merge(double[] arreglo, int izquierda, int mitad, int derecha) {
        int n1 = mitad - izquierda + 1;
        int n2 = derecha - mitad;

        double[] L = new double[n1];
        double[] R = new double[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arreglo[izquierda + i];
        for (int j = 0; j < n2; j++)
            R[j] = arreglo[mitad + 1 + j];

        int i = 0, j = 0;
        int k = izquierda;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arreglo[k] = L[i];
                i++;
            } else {
                arreglo[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arreglo[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arreglo[k] = R[j];
            j++;
            k++;
        }
    }
}
