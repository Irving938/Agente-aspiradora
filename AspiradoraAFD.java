package com.mycompany.aspiradora;

import java.util.HashMap;
import java.util.Map;

public class AspiradoraAFD {

    private static final Map<String, String> transiciones = new HashMap<>();

    static {
        
        transiciones.put("q0,0", "q0"); 
        transiciones.put("q0,1", "q1"); 

        transiciones.put("q1,0", "q1"); 
        transiciones.put("q1,1", "qf"); 
    }

    public static boolean validar(String input) {
        if (input.length() != 2) return false;

        StringBuilder cadena = new StringBuilder(input);
        String estado = "q0"; 
        int i = 0; 

        while (i < cadena.length()) {
            char simbolo = cadena.charAt(i);

            if (simbolo != '0' && simbolo != '1') return false;

            String clave = estado + "," + simbolo;
            if (!transiciones.containsKey(clave)) return false;

            if (simbolo == '0') {
                cadena.setCharAt(i, '1');
                System.out.println("Limpieza en posicion " + i + ": cadena ahora = " + cadena);
            }

            String siguienteEstado = transiciones.get(clave);

            if (!siguienteEstado.equals(estado)) {
                estado = siguienteEstado;
                i++;
            } else {
               
                estado = siguienteEstado;
            }
        }

        System.out.println("Cadena final: " + cadena);
        return estado.equals("qf");
    }

    public static void main(String[] args) {
        String[] pruebas = {"00"};

        for (String s : pruebas) {
            System.out.println("Probando \"" + s + "\" => " +
                    (validar(s) ? "ACEPTADA" : "RECHAZADA"));
            System.out.println("------");
        }
    }
}
