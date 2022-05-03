package rubenmejias.csvproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        VBox root = new VBox(30);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #ffff99;");
        var scene = new Scene (root, 640, 480);
        stage.setScene(scene);
        stage.show();
        
        Label enun = new Label("MUERTES POR MATERNIDAD");
        enun.setStyle("-fx-font: 50 Impact; -fx-text-fill: brown;");
        enun.setLayoutX(180);
        enun.setLayoutY(60);
        root.getChildren().add(enun);
         
        HBox hBoxComboBox = new HBox(15);
        hBoxComboBox.setAlignment(Pos.CENTER);
        root.getChildren().add(hBoxComboBox);
        
       
        HBox hBoxDatos = new HBox(20);
        hBoxDatos.setAlignment(Pos.CENTER);
        hBoxDatos.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, 
                                  new BorderWidths(8))));
        hBoxDatos.setMaxSize(200,400);
        root.getChildren().add(hBoxDatos);
        
        
        HBox hBoxMediaMuertes = new HBox(30);
        hBoxMediaMuertes.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hBoxMediaMuertes);
        

        Operaciones operaciones = new Operaciones();
        operaciones.leerFichero(hBoxComboBox, hBoxDatos, hBoxMediaMuertes, root);
    }

    
    public static void main(String[] args) {
        launch();
    }

}