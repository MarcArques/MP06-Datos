package com.project.pr14;

import jakarta.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.project.objectes.Llibre;

/**
 * Classe principal que gestiona la lectura i el processament de fitxers JSON per obtenir dades de llibres.
 */
public class PR14GestioLlibreriaJakartaMain {

    private final File dataFile;

    /**
     * Constructor de la classe PR14GestioLlibreriaJSONPMain.
     *
     * @param dataFile Fitxer on es troben els llibres.
     */
    public PR14GestioLlibreriaJakartaMain(File dataFile) {
        this.dataFile = dataFile;
    }

    public static void main(String[] args) {
        File dataFile = new File(System.getProperty("user.dir"), "data/pr14" + File.separator + "llibres_input.json");
        PR14GestioLlibreriaJakartaMain app = new PR14GestioLlibreriaJakartaMain(dataFile);
        app.processarFitxer();
    }

    /**
     * Processa el fitxer JSON per carregar, modificar, afegir, esborrar i guardar les dades dels llibres.
     */
    public void processarFitxer() {
        List<Llibre> llibres = carregarLlibres();
        if (llibres != null) {
            modificarAnyPublicacio(llibres, 1, 1995);
            afegirNouLlibre(llibres, new Llibre(4, "Històries de la ciutat", "Miquel Soler", 2022));
            esborrarLlibre(llibres, 2);
            guardarLlibres(llibres);
        }
    }

    /**
     * Carrega els llibres des del fitxer JSON.
     *
     * @return Llista de llibres o null si hi ha hagut un error en la lectura.
     */
    public List<Llibre> carregarLlibres() {
        List<Llibre> llibres = new ArrayList<>();
        try (JsonReader reader = Json.createReader(new FileReader(dataFile))) {
            JsonArray jsonArray = reader.readArray();
            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                Llibre llibre = new Llibre(
                    jsonObject.getInt("id"),
                    jsonObject.getString("titol"),
                    jsonObject.getString("autor"),
                    jsonObject.getInt("any")
                );
                llibres.add(llibre);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return llibres;
    }
    /**
     * Modifica l'any de publicació d'un llibre amb un id específic.
     *
     * @param llibres Llista de llibres.
     * @param id Identificador del llibre a modificar.
     * @param nouAny Nou any de publicació.
     */
    public void modificarAnyPublicacio(List<Llibre> llibres, int id, int nouAny) {
        if (llibres == null) return; 
        for (Llibre llibre : llibres) {
            if (llibre.getId() == id) {
                llibre.setAny(nouAny);
                break;
            }
        }
    }

    /**
     * Afegeix un nou llibre a la llista de llibres.
     *
     * @param llibres Llista de llibres.
     * @param nouLlibre Nou llibre a afegir.
     */
    public void afegirNouLlibre(List<Llibre> llibres, Llibre nouLlibre) {
        if (llibres == null) return; 
        llibres.add(nouLlibre);
    }

    /**
     * Esborra un llibre amb un id específic de la llista de llibres.
     *
     * @param llibres Llista de llibres.
     * @param id Identificador del llibre a esborrar.
     */
    public void esborrarLlibre(List<Llibre> llibres, int id) {
        if (llibres == null) return; 
        Iterator<Llibre> iterator = llibres.iterator();
        while (iterator.hasNext()) {
            Llibre llibre = iterator.next();
            if (llibre.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Guarda la llista de llibres en un fitxer nou.
     *
     * @param llibres Llista de llibres a guardar.
     */
    public void guardarLlibres(List<Llibre> llibres) {
        if (llibres == null) return;
        File outputFile = new File(dataFile.getParent(), "llibres_output_jakarta.json");
        try (JsonWriter writer = Json.createWriter(new FileWriter(outputFile))) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Llibre llibre : llibres) {
                JsonObject jsonObject = Json.createObjectBuilder()
                        .add("id", llibre.getId())
                        .add("titol", llibre.getTitol())
                        .add("autor", llibre.getAutor())
                        .add("any", llibre.getAny())
                        .build();
                arrayBuilder.add(jsonObject);
            }
            JsonArray jsonArray = arrayBuilder.build();
            writer.writeArray(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}