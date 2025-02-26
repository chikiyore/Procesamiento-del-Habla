/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ph.pkg6;

/**
 *
 * @author jarro
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PraatScriptGenerator {

    public static void generarScript(String secuenciaFonos, String archivoSalida, String dirdifonos) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(archivoSalida))) {
        // Leer desde archivos de difonos
        int contador = 0;
        for (int i = 0; i < secuenciaFonos.length(); i++) {
            if (contador == 0) {
                // Construye el nombre del archivo de difonos y escribe comandos en el script de Praat para leer el archivo y renombrarlo.
                // Se reemplaza la letra "E" por "E_" si está presente en el fonema.
                String fonema = Character.toString(secuenciaFonos.charAt(i));
                if (fonema.contains("E")) {
                    fonema = fonema.replace("E", "E_");
                }
                writer.println("Read from file: \"" + dirdifonos + fonema + "-.wav" + "\"");
                writer.println("Rename: \"difono" + contador + "\"");
            } else if (contador == secuenciaFonos.length() - 1) {
                // Hace lo mismo que en el caso anterior, pero para el último fonema en la secuencia.
                String fonema3 = Character.toString(secuenciaFonos.charAt(i));
                if (fonema3.contains("E")) {
                    fonema3 = fonema3.replace("E", "E_");
                }
                writer.println("Read from file: \"" + dirdifonos + "-" + fonema3 + ".wav" + "\"");
                writer.println("Rename: \"difono" + contador + "\"");
            } else {
                // Hace lo mismo que en los casos anteriores, pero para fonemas intermedios en la secuencia.
                String fonema2 = Character.toString(secuenciaFonos.charAt(i - 1)) + Character.toString(secuenciaFonos.charAt(i));
                if (fonema2.contains("E")) {
                    fonema2 = fonema2.replace("E", "E_");
                }
                writer.println("Read from file: \"" + dirdifonos + fonema2 + ".wav" + "\"");
                writer.println("Rename: \"difono" + contador + "\"");
            }
            contador++;
        }

        // Para manipular objetos de sonido en Praat.
        int contador2 = 0;
        for (int i = 0; i < secuenciaFonos.length(); i++) {
            if (contador2 == 0) {
                writer.println("selectObject: \"Sound difono" + contador2 + "\"");
            } else if (contador2 == secuenciaFonos.length() - 1) {
                writer.println("plusObject: \"Sound difono" + contador2+ "\"");
            } else {
                writer.println("plusObject: \"Sound difono" + contador2+ "\"");
            }
            contador2++;
        }

        // Concatenar y guardar el archivo como WAV.
        writer.println("Concatenate");
        writer.println("Save as WAV file: \"" + secuenciaFonos + ".wav\"");

        System.out.println("Script de Praat generado exitosamente en: " + archivoSalida);

    } catch (IOException e) {
        
    }
}


    public static void generarScriptPlay(String secuenciaFonos, String archivoSalidaplay) {
        try (PrintWriter writerplay = new PrintWriter(new FileWriter(archivoSalidaplay))) {

            writerplay.println("Read from file: \"" + secuenciaFonos + ".wav\"");

            writerplay.println("selectObject: \"Sound " + secuenciaFonos + "\"");
            writerplay.println("Play");

            System.out.println("Script de Praat generado exitosamente en: " + archivoSalidaplay);
        } catch (IOException e) {
        }
    }

    public static void generarScriptProsodia(String secuenciaFonos, String archivoSalidaprosodia) {
        try (PrintWriter writerplay = new PrintWriter(new FileWriter(archivoSalidaprosodia))) {

            writerplay.println("Read from file: \"" + secuenciaFonos + ".wav\"");

            writerplay.println("duration = Get total duration");
            writerplay.println("comienzo=duration-duration");
            writerplay.println("start_time = duration - 0.2 * duration");
            writerplay.println("sound=To Manipulation: 0.01, 100, 400");
            writerplay.println("Edit");
            writerplay.println("editor: sound");
            writerplay.println("Stylize pitch (2 st)");            
            writerplay.println("Select: start_time,duration");
            writerplay.println("Multiply pitch frequencies: 1.8");
            writerplay.println("Select: comienzo,duration");
            writerplay.println("Publish resynthesis");
            writerplay.println("Close");
            writerplay.println("Save as WAV file: \"" + secuenciaFonos + "prosodia.wav\"");
            writerplay.println("Quit");

            System.out.println("Script de Praat generado exitosamente en: " + archivoSalidaprosodia);
        } catch (IOException e) {
        }
    }
}
