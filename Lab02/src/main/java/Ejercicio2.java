

import java.util.*;

public class Ejercicio2 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese una linea de caracteres: ");
        String linea = scanner.nextLine();
        
        char caracterMasRepetido = encontrarCaracterMasRepetido(linea);
        
        String sustituida = sustituirVocales(linea, caracterMasRepetido);
        System.out.println("Cadena con vocales sustituidas: " + sustituida);
        
        String invertida = new StringBuilder(linea).reverse().toString();
        System.out.println("Cadena invertida: " + invertida);
    }
    
    public static char encontrarCaracterMasRepetido(String linea) {
        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : linea.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }
        
        char maxChar = linea.charAt(0);
        int maxFrecuencia = 0;
        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            if (entrada.getValue() > maxFrecuencia) {
                maxFrecuencia = entrada.getValue();
                maxChar = entrada.getKey();
            }
        }
        return maxChar;
    }
    
    public static String sustituirVocales(String linea, char sustituto) {
        return linea.replaceAll("[aeiouAEIOU]", Character.toString(sustituto));
    }
}

