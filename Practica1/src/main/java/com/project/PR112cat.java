package com.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class PR112cat {

    public static void main(String[] args) {
        // Comprovar que s'ha proporcionat una ruta com a paràmetre
        if (args.length == 0) {
            System.out.println("No s'ha proporcionat cap ruta d'arxiu.");
            return;
        }

        // Obtenir la ruta del fitxer des dels paràmetres
        String rutaArxiu = args[0];
        mostrarContingutArxiu(rutaArxiu);
    }

    // Funció per mostrar el contingut de l'arxiu o el missatge d'error corresponent
    public static void mostrarContingutArxiu(String rutaArxiu) {
        File file = new File(rutaArxiu);

        if (!file.exists() || !file.canRead()) {
            System.out.println("El fitxer no existeix o no és accessible.");
            return;
        }

        if (file.isDirectory()) {
            System.out.println("El path no correspon a un arxiu, sinó a una carpeta.");
            return;
        }

        try {
            String contingut = Files.readString(Paths.get(rutaArxiu), StandardCharsets.UTF_8);
            System.out.println(contingut);
        } catch (IOException e) {
            System.out.println("El fitxer no existeix o no és accessible.");
        }
    }
}
