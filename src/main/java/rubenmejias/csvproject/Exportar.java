package rubenmejias.csvproject;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class Exportar {
    public static void exportarContenido(float media, String seleccionPais){
        String nombreFichero = "media_muertes.csv";
        String textoEncabezado = "Pais,Media de Muertes";
        String textoDatos = seleccionPais+","+media;
        //String texto = "Media de muertes en "+seleccionPais+" a lo largo de la historia: "+media;
        BufferedWriter bw = null;
        
        try {
        //Crear un objeto BufferedWriter. Si ya existe el fichero, 
        //  se borra automáticamente su contenido anterior.
            bw = new BufferedWriter(new FileWriter(nombreFichero));
        //Escribir en el fichero el texto con un salto de línea
            bw.write(textoEncabezado + "\n");
            bw.write(textoDatos + "\n");
        }
        // Comprobamos si se ha producido algún error
        catch(Exception ex) {
            System.out.println("Error de escritura del fichero");
            ex.printStackTrace();
        }
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(bw != null)
                    bw.close();
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }
    }
}