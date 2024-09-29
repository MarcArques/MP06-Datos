package com.project;

import java.io.FileWriter;
import java.io.IOException;

public class PR113sobreescriu {

    public static void main(String[] args) {
        // Definir el camí del fitxer dins del directori "data"
        String camiFitxer = System.getProperty("user.dir") + "/data/frasesMatrix.txt";

        // Crida al mètode que escriu les frases sobreescrivint el fitxer
        escriureFrases(camiFitxer);
    }

    // Mètode que escriu les frases sobreescrivint el fitxer amb UTF-8 i línia en blanc final
    public static void escriureFrases(String camiFitxer) {
        try (FileWriter fw = new FileWriter(camiFitxer)) {
            fw.write("I can only show you the door\n");
            fw.write("You're the one that has to walk through it\n");
            System.out.println("Frases sobreescrites correctament.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
