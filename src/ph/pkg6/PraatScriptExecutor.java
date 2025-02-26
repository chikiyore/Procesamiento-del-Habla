/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ph.pkg6;

/**
 *
 * @author jarro
 */
import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PraatScriptExecutor {

    public static void ejecutarScriptPraat(String praatExecutable, String scriptPath) {
        try {
            // Construir el comando para ejecutar Praat con el script
            String comando = "\"" + praatExecutable + "\" \"" + scriptPath + "\"";

            // Configurar el proceso para ejecutar el comando
            ProcessBuilder builder = new ProcessBuilder(comando);
            builder.redirectErrorStream(true);

            // Iniciar el proceso
            Process proceso = builder.start();

            // Capturar la salida del proceso
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            // Esperar a que el proceso termine
            int exitCode = proceso.waitFor();

            System.out.println("Script de Praat ejecutado exitosamente. Código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
   


    public static void ejecutarScriptPraatProsodia(String praatExecutable, String scriptPath) throws InterruptedException {
        try {
            // Construir el comando para ejecutar Praat con el script
            String comando = "\"" + praatExecutable + "\" --send \"" + scriptPath + "\"";

            // Configurar el proceso para ejecutar el comando
            ProcessBuilder builder = new ProcessBuilder(comando);
            builder.redirectErrorStream(true);

            // Iniciar el proceso
            Process proceso = builder.start();
            //Esperamos a que termine 
            int codigosalida=proceso.waitFor();

            System.out.println("Script de Praat ejecutado exitosamente. Código de salida: "+codigosalida );

            
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    


}
