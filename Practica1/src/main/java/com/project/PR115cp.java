package com.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PR115cp {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error: Has d'indicar dues rutes d'arxiu.");
            System.out.println("Ús: PR115cp <origen> <destinació>");
            return;
        }

        // Ruta de l'arxiu origen
        String rutaOrigen = args[0];
        // Ruta de l'arxiu destinació
        String rutaDesti = args[1];

        // Crida al mètode per copiar l'arxiu
        copiarArxiu(rutaOrigen, rutaDesti);
    }

    // Mètode per copiar un arxiu de text de l'origen al destí
    public static void copiarArxiu(String rutaOrigen, String rutaDesti) {
        File fitxerOrigen = new File(rutaOrigen);
        File fitxerDesti = new File(rutaDesti);

        if (!fitxerOrigen.exists() || !fitxerOrigen.isFile()) {
            System.out.println("Error: El fitxer d'origen no existeix o no és un fitxer vàlid.");
            return;
        }

        if (fitxerDesti.exists()) {
            System.out.println("Advertència: L'arxiu de destinació ja existeix i serà sobreescrit.");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fitxerOrigen));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerDesti))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }

            bw.flush();
            System.out.println("Còpia realitzada correctament.");

        } catch (IOException e) {
            System.out.println("Error en copiar l'arxiu: " + e.getMessage());
        }
    }
}
