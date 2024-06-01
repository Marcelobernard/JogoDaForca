package estacio.jdf;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class InicioTutorial {
    public static void mostrar(boolean mesmaPalavra, Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Vamos começar!");
        alerta.setHeaderText(null);
        alerta.setContentText("Você precisa adivinhar a palavra oculta, cada _ representa uma letra, " +
                "podendo haver mais de 1 letra igual." +
                " O jogo consiste em 6 tentativas, ou seja, poderá errar no máximo 5 vezes, escolha uma letra!");

        alerta.getDialogPane().setStyle("-fx-background-color: #CB8800; -fx-border-color: black; -fx-border-width: 2;");
        alerta.getDialogPane().lookup(".content").setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center;");

        Image icon = new Image("chadwin.png");
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(50);
        iconView.setFitWidth(50);
        alerta.setGraphic(iconView);

        ButtonType continuarButton = new ButtonType("Continuar");
        alerta.getButtonTypes().setAll(continuarButton);

        alerta.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alerta.showAndWait().ifPresent(buttonType -> {
            if (buttonType == continuarButton) {
                alerta.close();
            }
        });
        alerta.close();
    }
}