package estacio.jdf;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class PrimeiroAcerto {
    public static void mostrar(boolean mesmaPalavra, Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Primeiro passo!");
        alerta.setHeaderText(null);
        alerta.setContentText("Você acertou 1 letra! Com isso essa opção ficará marcada como verde, continue assim!");

        alerta.getDialogPane().setStyle("-fx-background-color: #22CCB5; -fx-border-color: black; -fx-border-width: 2;");
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