package com.project;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PR110ReadFile {

    public static void main(String[] args) {
        String camiFitxer = System.getProperty("user.dir") + "/data/GestioTasques.java";
        llegirIMostrarFitxer(camiFitxer);  // Només cridem a la funció amb la ruta del fitxer
    }

    // Funció que llegeix el fitxer i mostra les línies amb numeració
    public static void llegirIMostrarFitxer(String camiFitxer) {
        try {
            List<String> linies = Files.readAllLines(Paths.get(camiFitxer), StandardCharsets.UTF_8);
            mostrarLinies(linies);
        } catch (IOException e) {
            System.out.println("El fitxer no existeix o no és accessible.");
            e.printStackTrace();
        }
    }
    
    public static void mostrarLinies(List<String> lineas){
        int numLinea = 1;
        for (String linea:lineas){
            System.out.println(Integer.toString(numLinea)  + ": " + linea);
            numLinea++;
        }
    }
}
