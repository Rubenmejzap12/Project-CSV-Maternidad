package rubenmejias.csvproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Operaciones {
    
    String paisActual = "";
    String annoActual = "";
    String MuertesObtenidas = "";
    
    int muertesTotales = 0;
    int numeroSumasMedia = 0;
    float media = 0;
    boolean annadirAnno = true;
    boolean mostrarRegistro = false;
    boolean pulsacionBuscar = false;
    
    String seleccionPais = "";
    String seleccionAnno = "";
    
     Exportar exportar = new Exportar();
    
    public void leerFichero(HBox hBoxComboBox, HBox hBoxDatos, HBox hBoxMediaMuertes, VBox root){
       
        // Lista que contiene los valores del archivo de pais y año
        ArrayList<String> listaPais = new ArrayList();
        ArrayList<String> listaAnno = new ArrayList();
 
        
        String nombreFichero = "maternal-mortality.csv";

        
        BufferedReader br = null;
        try {
            // Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre que tengamos en el fichero
            br = new BufferedReader(new FileReader(nombreFichero));
            String texto = br.readLine();
            // Repetir mientras no se llegue al final del fichero
            while(texto != null) {
                String[] valores = texto.split(",");
                MostrarDatos mostrarDatos = new MostrarDatos();
                mostrarDatos.setMuertesMaternidad(valores[3]);
                mostrarDatos.setAnno(valores[2]);
                mostrarDatos.setPais(valores[0]);
                if(!paisActual.equals(mostrarDatos.getPais()) && !mostrarDatos.getPais().equals("Entity") && !mostrarDatos.getPais().isEmpty()){
                    paisActual = mostrarDatos.getPais();
                    listaPais.add(paisActual);
                }
                
                for(int i=0;i<listaAnno.size();i++){
                    if(mostrarDatos.getAnno().equals(listaAnno.get(i))){
                        annadirAnno = false;
                    }
                }
                if(annadirAnno==true && !mostrarDatos.getAnno().equals("Year")){
                    listaAnno.add(mostrarDatos.getAnno());
                }
                texto = br.readLine();
            }
        }
        // Fichero no encontrado
        catch (FileNotFoundException ex) {
            System.out.println("Error el fichero no se ha encontrado correctamente");
            ex.printStackTrace();
        }
        catch(Exception ex) {
            System.out.println("Error datos del archivo");
            ex.printStackTrace();
        }
        // Aseguramos el cierre del fichero 
        finally {
            try {
                if(br != null) {
                    br.close();
                }
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }

        //Combobox para seleccionar el pais
        ComboBox<String> comboBoxPais = new ComboBox(FXCollections.observableList(listaPais));
        hBoxComboBox.getChildren().add(comboBoxPais);
        comboBoxPais.setPromptText("Selecciona país");

        // Añadir un label en el que se mostrará el elemento seleccionado
        Label paisSeleccionado = new Label();
        hBoxDatos.getChildren().add(paisSeleccionado);
        comboBoxPais.setOnAction((t) -> {
            seleccionPais = comboBoxPais.getValue();
        });
        

        //COMBOBOX AÑO 
        Collections.sort(listaAnno); // ORDENA EL ARRAYLIST POR ORDEN ALFABETICO
        ComboBox<String> comboBoxAnno = new ComboBox(FXCollections.observableList(listaAnno));
        hBoxComboBox.getChildren().add(comboBoxAnno);
        comboBoxAnno.setPromptText("Selecciona año");

        // Añadir un label en el que se mostrará el elemento seleccionado
        Label annoSeleccionado = new Label();
        hBoxDatos.getChildren().add(annoSeleccionado);

        // Cuando el usuario seleccione algo del ComboBox, se mostrará en el Label
        comboBoxAnno.setOnAction((t) -> {
            seleccionAnno = comboBoxAnno.getValue();
        });
        
        
        // LABELS 
        Label numeroMuertesLabel = new Label();
        root.getChildren().add(numeroMuertesLabel);
        
        Label mediaMuertesLabel = new Label();
        root.getChildren().add(mediaMuertesLabel);
        
        
        // BOTONES 
        Button buttonBuscar = new Button("Buscar");
        buttonBuscar.setStyle("-fx-background-color: #cccc00;");
        root.getChildren().add(buttonBuscar);
        
        Button buttonExport = new Button("Exportar");
        buttonExport.setStyle("-fx-background-color: #cccc00;");
        root.getChildren().add(buttonExport);
        
        buttonBuscar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {
                BufferedReader br = null;
                try {
                    // Crear un objeto BufferedReader al que se le pasa
                    //   un objeto FileReader con el nombre del fichero
                    br = new BufferedReader(new FileReader(nombreFichero));
                    // Leer la primera línea, guardando en un String
                    String texto = br.readLine();
                    // Repetir mientras no se llegue al final del fichero
                    while(texto != null) {
                        String[] valores = texto.split(",");
                        MostrarDatos mostrarDatos = new MostrarDatos();
                        mostrarDatos.setMuertesMaternidad(valores[3]);
                        mostrarDatos.setAnno(valores[2]);
                        mostrarDatos.setPais(valores[0]);
                        
                        if(mostrarDatos.getPais().equals(seleccionPais) && mostrarDatos.getAnno().equals(seleccionAnno)){
                            MuertesObtenidas = mostrarDatos.getMuertesMaternidad();
                            mostrarRegistro = true;
                        }
                        if(mostrarDatos.getPais().equals(seleccionPais)){
                            muertesTotales += Integer.parseInt(mostrarDatos.getMuertesMaternidad());
                            numeroSumasMedia++;
                        }
                        // Leer la siguiente línea
                        texto = br.readLine();
                    }
                    if(mostrarRegistro==true){
                        numeroMuertesLabel.setText("Muertes en "+seleccionPais+" en "+seleccionAnno+": "+MuertesObtenidas);
                        mostrarRegistro = false;
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error en la búsqueda");
                        alert.setHeaderText("No se ha encontrado ningún registro");
                        alert.setContentText("No hay registros");

                        alert.showAndWait();
                    }
                    media = 0;
                    media = muertesTotales/numeroSumasMedia;
                    mediaMuertesLabel.setText("Media de Muertes en "+seleccionPais+": "+media);
                    numeroSumasMedia = 0;
                    muertesTotales = 0;
                    pulsacionBuscar = true;
                }
                // Captura de excepción por fichero no encontrado
                catch (FileNotFoundException ex) {
                    System.out.println("Error: Fichero no encontrado");
                    ex.printStackTrace();
                }
                catch(Exception ex) {
                    System.out.println("Error de lectura del fichero");
                    ex.printStackTrace();
                }
                // Asegurar el cierre del fichero 
                finally {
                    try {
                        // Cerrar el fichero si se ha abierto
                        if(br != null) {
                            br.close();
                        }
                    }
                    catch (Exception ex) {
                        System.out.println("Error al cerrar el fichero");
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        buttonExport.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {
                if(pulsacionBuscar == true){
                    System.out.println(pulsacionBuscar);
                exportar.exportarContenido(media, seleccionPais);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exportación");
                alert.setHeaderText("Exportación correcta");
                alert.setContentText("Se han exportado los datos");
                alert.showAndWait();
                }
                
            }
        });
        
    }
}
