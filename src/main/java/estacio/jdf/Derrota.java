package estacio.jdf;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Derrota {
    public static void mostrar(boolean mesmaPalavra, Stage primaryStage) {
        primaryStage.getIcons().add(new Image("murloc.png"));
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Derrota");
        alerta.setHeaderText(null);
        alerta.setContentText("Você perdeu! Clique para jogar novamente!");

        alerta.getDialogPane().setStyle("-fx-background-color: red; -fx-border-color: darkred; -fx-border-width: 2;");
        alerta.getDialogPane().lookup(".content").setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment: center;");

        Image icon = new Image("lose.png");
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
                String condicao = "Derrota";
                Banco.inserirNovoRegistro(condicao);
                JogoDaForca jogo = new JogoDaForca();
                jogo.start(primaryStage);
            } else if (buttonType == sairButton) {
                String condicao = "Derrota";
                Banco.inserirNovoRegistro(condicao);
                Inicio jogo = new Inicio();
                jogo.start(primaryStage);
            }
        });
        alerta.close();
    }
}