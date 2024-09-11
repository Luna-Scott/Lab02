package com.tuempresa;

import java.util.*;

public class Ejercicio1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
 
        System.out.print("Ingrese el tamaño del arreglo: ");
        int tamaño = scanner.nextInt();
        
        System.out.println("");
        System.out.println("    Resultados    ");
        
        double[] arreglo = new double[tamaño];
        Random random = new Random();
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = random.nextDouble() * 100;
        }
        
        System.out.println("Arreglo generado: " + Arrays.toString(arreglo));
        
        System.out.println("Media: " + calcularMedia(arreglo));
        System.out.println("Mediana: " + calcularMediana(arreglo));
        System.out.println("Varianza: " + calcularVarianza(arreglo));
        System.out.println("Desviacion estandar: " + calcularDesviacionEstandar(arreglo));
        System.out.println("Moda: " + calcularModa(arreglo));
    }
    
    public static double calcularMedia(double[] arreglo) {
        double suma = 0;
        for (double num : arreglo) {
            suma += num;
        }
        return suma / arreglo.length;
    }
    
    public static double calcularMediana(double[] arreglo) {
        double[] temp = arreglo.clone();
        Arrays.sort(temp);
        int mitad = temp.length / 2;
        if (temp.length % 2 == 0) {
            return (temp[mitad - 1] + temp[mitad]) / 2.0;
        } else {
            return temp[mitad];
        }
    }
    
    public static double calcularVarianza(double[] arreglo) {
        double media = calcularMedia(arreglo);
        double suma = 0;
        for (double num : arreglo) {
            suma += Math.pow(num - media, 2);
        }
        return suma / arreglo.length;
    }
    
    public static double calcularDesviacionEstandar(double[] arreglo) {
        return Math.sqrt(calcularVarianza(arreglo));
    }
    
    public static double calcularModa(double[] arreglo) {
        Map<Double, Integer> frecuencias = new HashMap<>();
        for (double num : arreglo) {
            frecuencias.put(num, frecuencias.getOrDefault(num, 0) + 1);
        }
        
        double moda = arreglo[0];
        int maxFrecuencia = 1;
        boolean hayModa = false;

        for (Map.Entry<Double, Integer> entrada : frecuencias.entrySet()) {
            if (entrada.getValue() > maxFrecuencia) {
                maxFrecuencia = entrada.getValue();
                moda = entrada.getKey();
                hayModa = true;
            }
        }
        
        return hayModa ? moda : Double.NaN;
    }
}
