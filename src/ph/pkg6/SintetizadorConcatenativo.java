/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ph.pkg6;

/**
 *
 * @author jarro
 */
import java.util.Scanner;
import static ph.pkg6.PraatScriptExecutor.ejecutarScriptPraat;
import static ph.pkg6.PraatScriptExecutor.ejecutarScriptPraatProsodia;
import static ph.pkg6.PraatScriptGenerator.generarScript;
import static ph.pkg6.PraatScriptGenerator.generarScriptPlay;
import static ph.pkg6.PraatScriptGenerator.generarScriptProsodia;

public class SintetizadorConcatenativo {

    public static void main(String[] args) throws InterruptedException {

// Obtener la secuencia de fonos del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la secuencia de fonos:");
        String secuenciaFonos = scanner.nextLine();
        boolean pregunta;
        ComprobarCadena cadenavalida = new ComprobarCadena();
//Direcciones
        String praatExecutable = "Praat/Praat.exe";
        String scriptconcatena = "Praat/scriptconcatenar.praat";
        String scriptplay = "Praat/scriptplay.praat";
        String scriptProsodia = "Praat/modificarpitch.praat";
        String carpetadifonos = "Difonos/";
//Comprobamos si la cadena es valida y si lleva el caracter "?"
        if (cadenavalida.valida(secuenciaFonos)) {
            if (secuenciaFonos.contains("?")) {
                pregunta = true;
                secuenciaFonos = secuenciaFonos.substring(0, secuenciaFonos.length() - 1);                
                generarScript(secuenciaFonos, scriptconcatena, carpetadifonos);
                ejecutarScriptPraat(praatExecutable, scriptconcatena);
                generarScriptProsodia(secuenciaFonos, scriptProsodia);
                ejecutarScriptPraatProsodia(praatExecutable, scriptProsodia);
                generarScriptPlay(secuenciaFonos + "prosodia", scriptplay);
            } else {
                pregunta = false;               
                generarScript(secuenciaFonos, scriptconcatena, carpetadifonos);
                ejecutarScriptPraat(praatExecutable, scriptconcatena);
                generarScriptPlay(secuenciaFonos, scriptplay);
            }
//Pregunta si quieres reproducir el audio
            System.out.println("Quiere reproducir el audio? si/no");
            String reproducir = scanner.nextLine();

            // Imprimir un mensaje acorde a si es una pregunta
            if (pregunta) {
                System.out.println("Se ha generado un archivo de audio con prosodia de pregunta: " + secuenciaFonos + "prosodia.wav");
            } else {
                System.out.println("Se ha generado un archivo de audio: " + secuenciaFonos + ".wav");
            }

            if (reproducir.contains("si")) {
                ejecutarScriptPraat(praatExecutable, scriptplay);     
            }
        } else {
            System.out.println("La cadena no es válida");
        }
    }
}
