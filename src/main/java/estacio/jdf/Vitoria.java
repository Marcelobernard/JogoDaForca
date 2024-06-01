package estacio.jdf;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Vitoria {
    public static void mostrar(boolean mesmaPalavra, Stage primaryStage) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Vitória");
        alerta.setHeaderText(null);
        alerta.setContentText("Você venceu! Clique para jogar novamente!");

        alerta.getDialogPane().setStyle("-fx-background-color: green; -fx-border-color: darkgreen; -fx-border-width: 2;");
        alerta.getDialogPane().lookup(".content").setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center;");

        Image icon = new Image("win.png");
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(50);
        iconView.setFitWidth(50);
        alerta.setGraphic(iconView);

        ButtonType jogarNovamenteButton = new ButtonType("Jogar Novamente");
        ButtonType sairButton = new ButtonType("Sair");
        alerta.getButtonTypes().setAll(jogarNovamenteButton, sairButton);

        alerta.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        alerta.showAndWait().ifPresent(buttonType -> {
            if (buttonType == jogarNovamenteButton) {
                String condicao = "Vitória";
                Banco.inserirNovoRegistro(condicao);
                JogoDaForca jogo = new JogoDaForca();
                jogo.start(primaryStage);
            } else if (buttonType == sairButton) {
                String condicao = "Vitória";
                Banco.inserirNovoRegistro(condicao);
                Inicio jogo = new Inicio();
                jogo.start(primaryStage);
            }
        });
        alerta.close();
    }
}