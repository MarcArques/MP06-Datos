package com.project;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class PR114linies {

    public static void main(String[] args) {
        // Definir el camí del fitxer dins del directori "data"
        String camiFitxer = System.getProperty("user.dir") + "/data/numeros.txt";

        // Crida al mètode que genera i escriu els números aleatoris
        generarNumerosAleatoris(camiFitxer);
    }

    // Mètode per generar 10 números aleatoris i escriure'ls al fitxer
    public static void generarNumerosAleatoris(String camiFitxer) {
        Random random = new Random();
        try (FileWriter writer = new FileWriter(camiFitxer, StandardCharsets.UTF_8)) {
            for (int i = 0; i < 10; i++) {
                writer.write(String.valueOf(random.nextInt(100)));
                if (i < 9) {
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
