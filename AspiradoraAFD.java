package com.mycompany.aspiradora;

import java.util.Random;
import java.util.*;

public class AspiradoraAFD {
    
    
    
    private static final Map<String, String> transiciones = new HashMap<>();

    static {
        
        transiciones.put("q0,0", "q0"); 
        transiciones.put("q0,1", "q1"); 

        transiciones.put("q1,0", "q1"); 
        transiciones.put("q1,1", "q2"); 
        
        transiciones.put("q2,0", "q2"); 
        transiciones.put("q2,1", "q3");
        
        transiciones.put("q3,0", "q3"); 
        transiciones.put("q3,1", "q4");
        
        transiciones.put("q4,0", "q4"); 
        transiciones.put("q4,1", "q5");
        
        transiciones.put("q5,0", "q5"); 
        transiciones.put("q5,1", "q6");
        
        transiciones.put("q6,0", "q6"); 
        transiciones.put("q6,1", "q7");
        
        transiciones.put("q7,0", "q7"); 
        transiciones.put("q7,1", "q8");
        
        transiciones.put("q8,0", "q8"); 
        transiciones.put("q8,1", "q9");
        
        transiciones.put("q9,0", "q9"); 
        transiciones.put("q9,1", "qf");
        
    }
    
    public static void mostrarEstado(StringBuilder cadena, int posicionRobot) {
    
    for (int i = 0; i < cadena.length(); i++) {
        System.out.print(" ---  ");
    }
    System.out.println();

    for (int i = 0; i < cadena.length(); i++) {
        char estado;
        if (i == posicionRobot) estado = 'R';
        else if (cadena.charAt(i) == '0') estado = 'S';
        else estado = 'L';
        System.out.print("| " + estado + " | ");
    }
    System.out.println();

    for (int i = 0; i < cadena.length(); i++) {
        System.out.print(" ---  ");
    }
    System.out.println("\n");
}
    public static boolean validar(String input) throws InterruptedException {
        int t = 0;
        int l = 0;
        Random rnumber = new Random();
        if (input.length() != 10) return false;
        StringBuilder cadena = new StringBuilder(input);
        StringBuilder copia = new StringBuilder(input);
        String estado = "q0";
        
        
        int c = 0; 
        
        while (c < 5) {
        
         
        int i = 0;    
        estado = "q0"; 
            
            
            while (i < cadena.length()) { 
            
            char simbolo = cadena.charAt(i);
            
            
            if (simbolo != '0' && simbolo != '1') return false;

            String clave = estado + "," + simbolo;
            if (!transiciones.containsKey(clave)) return false;

            if (simbolo == '0') {
                cadena.setCharAt(i, '1');  
            }
            
            mostrarEstado(cadena,i);
            Thread.sleep(250);

            String siguienteEstado = transiciones.get(clave);

            if (!siguienteEstado.equals(estado)) {
                estado = siguienteEstado;
                i++;
            } else {
               
                estado = siguienteEstado;
            }
            
            int x = rnumber.nextInt(5);
            
            if(x == 3){
                int pos = rnumber.nextInt(10);
                cadena.setCharAt(pos, '0');
                System.out.println("Se ensucio de nuevo la posicion " + pos);
            }
            
            }
            
            t = t + cadena.length();
            c++;
            System.out.println("Cadena final: " + cadena);
            cadena = new StringBuilder(copia);
            
        }

        
        return estado.equals("qf");
    }

    public static void main(String[] args) throws InterruptedException {
        String[] pruebas = {"0000000000"};

        for (String s : pruebas) {
            System.out.println("Probando \"" + s + "\" => " +
                    (validar(s) ? "ACEPTADA" : "RECHAZADA"));
            System.out.println("------");
        }
    }
}
