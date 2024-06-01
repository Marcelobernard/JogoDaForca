package estacio.jdf;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class DerrotaTutorialEnd {
    public static void mostrar(boolean mesmaPalavra, Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Desastre!");
        alerta.setHeaderText(null);
        alerta.setContentText("Infelizmente você perdeu, a palavra atual será mantida caso queira 'Continuar'. " +
                "Se quiser sair do tutorial, clique em 'Sair'");

        alerta.getDialogPane().setStyle("-fx-background-color: #CC4E22; -fx-border-color: black; -fx-border-width: 2;");
        alerta.getDialogPane().lookup(".content").setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center;");

        Image icon = new Image("tutorial.png");
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(50);
        iconView.setFitWidth(50);
        alerta.setGraphic(iconView);

        ButtonType continuarButton = new ButtonType("Continuar");
        ButtonType sairButton = new ButtonType("Sair");
        alerta.getButtonTypes().setAll(continuarButton, sairButton);

        alerta.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alerta.showAndWait().ifPresent(buttonType -> {
            if (buttonType == continuarButton) {
                Tutorial jogo = new Tutorial();
                jogo.start(primaryStage);
            }
            else if (buttonType == sairButton) {
                Inicio jogo = new Inicio();
                jogo.start(primaryStage);
            }

        });
        alerta.close();
    }
}