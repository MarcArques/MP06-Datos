package com.project;

import java.io.File;
import java.io.IOException;

public class PR111Files {

    public static void main(String[] args) {
        String camiFitxer = System.getProperty("user.dir") + "/data/pr111";
        gestionarArxius(camiFitxer);
    }

    public static void gestionarArxius(String camiFitxer) {
        String filePath = camiFitxer + "/myFiles";
        try {
            fileMaker(filePath);
            
            File file1 = new File(filePath + "/file1.txt");
            File file2 = new File(filePath + "/file2.txt");
            file1.createNewFile();
            file2.createNewFile();
            
            File renamedFile = new File(filePath + "/renamedFile.txt");
            if (file2.renameTo(renamedFile)) {
                System.out.println("file2.txt ha estat renombrat a renamedFile.txt");
            }

            listFiles(filePath, "Els arxius de la carpeta són:");

            if (file1.delete()) {
                System.out.println("file1.txt ha estat eliminat");
            }

            listFiles(filePath, "Els arxius de la carpeta són:");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileMaker(String filePath) throws IOException {
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            throw new IOException("Error: El fitxer ja existeix");
        }

        if (file.exists() && file.isDirectory()) {
            if (!file.canRead() || !file.canWrite()) {
                throw new IOException("Error: La carpeta existeix però no té els permisos adequats");
            }
        } else {
            if (!file.mkdirs()) {
                throw new IOException("Error: creant la carpeta en el camí " + file.getPath());
            }
        }
    }

    public static void listFiles(String filePath, String message) {
        File dir = new File(filePath);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            System.out.println(message);
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                }
            }
        }
    }
}
