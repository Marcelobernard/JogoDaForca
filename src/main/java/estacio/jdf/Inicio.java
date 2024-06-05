package estacio.jdf;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.sql.*;
import javafx.concurrent.Task;
import javafx.stage.StageStyle;

public class Inicio extends Application {
    private static final String DATABASE_URL = "jdbc:h2:~/history";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("murloc.png"));
        primaryStage.setTitle("Jogo da Forca");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);

        Font TextoFonte = Font.loadFont(getClass().getResourceAsStream("/fonts/Pixel Emulator.otf"), 20);
        Font TextoFonteGrande = Font.loadFont(getClass().getResourceAsStream("/fonts/Pixel Emulator.otf"), 23);

        Task<Void> databaseInitTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                inicializarBancoDeDados();
                return null;
            }
        };

        Button btnComecar = new Button("Começar");
        btnComecar.setFont(TextoFonte);
        btnComecar.setStyle(
                "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: darkgray; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 3, 3);"
        );
        btnComecar.setOnMouseEntered(e -> {
            btnComecar.setStyle(
                    "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                            "-fx-text-fill: white; " +
                            "-fx-border-color: darkgray; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(91, 0, 0, 1), 15, 0.5, 0, 0);"
            );
            btnComecar.setFont(TextoFonteGrande);
        });
        btnComecar.setOnMouseExited(e -> {
            btnComecar.setStyle(
                    "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                            "-fx-text-fill: white; " +
                            "-fx-border-color: darkgray; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 3, 3);"
            );
            btnComecar.setFont(TextoFonte);
        });
        btnComecar.setOnAction(e -> {
            JogoDaForca jogo = new JogoDaForca();
            jogo.start(primaryStage);
        });

        Button btnhLog = new Button("Consultar histórico");
        btnhLog.setFont(TextoFonte);
        btnhLog.setStyle(
                "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: darkgray; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 3, 3);"
        );
        btnhLog.setOnMouseEntered(e -> {
            btnhLog.setStyle(
                    "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                            "-fx-text-fill: white; " +
                            "-fx-border-color: darkgray; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(91, 0, 0, 1), 15, 0.5, 0, 0);"
            );
            btnhLog.setFont(TextoFonteGrande);
        });
        btnhLog.setOnMouseExited(e -> {
            btnhLog.setStyle(
                    "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                            "-fx-text-fill: white; " +
                            "-fx-border-color: darkgray; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 3, 3);"
            );
            btnhLog.setFont(TextoFonte);
        });
        btnhLog.setOnAction(e -> {
            JogosJogados jogo = new JogosJogados();
            jogo.start(primaryStage);
        });

        Button btnTutorial = new Button("Tutorial");
        btnTutorial.setFont(TextoFonte);
        btnTutorial.setStyle(
                "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: darkgray; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 3, 3);"
        );
        btnTutorial.setOnMouseEntered(e -> {
            btnTutorial.setStyle(
                    "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                            "-fx-text-fill: white; " +
                            "-fx-border-color: darkgray; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(91, 0, 0, 1), 15, 0.5, 0, 0);"
            );
            btnTutorial.setFont(TextoFonteGrande);
        });
        btnTutorial.setOnMouseExited(e -> {
            btnTutorial.setStyle(
                    "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                            "-fx-text-fill: white; " +
                            "-fx-border-color: darkgray; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 3, 3);"
            );
            btnTutorial.setFont(TextoFonte);
        });
        btnTutorial.setOnAction(e -> {
            Tutorial jogo = new Tutorial();
            jogo.start(primaryStage);
        });

        Button btnSair = new Button("Sair");
        btnSair.setFont(TextoFonte);
        btnSair.setStyle(
                "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: darkgray; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 3, 3);"
        );
        btnSair.setOnMouseEntered(e -> {
            btnSair.setStyle(
                    "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                            "-fx-text-fill: white; " +
                            "-fx-border-color: darkgray; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(91, 0, 0, 1), 15, 0.5, 0, 0);"
            );
            btnSair.setFont(TextoFonteGrande);
        });
        btnSair.setOnMouseExited(e -> {
            btnSair.setFont(TextoFonte);
            btnSair.setStyle(
                    "-fx-background-color: linear-gradient(rgb(4, 9, 34), rgb(114, 64, 77)); " +
                            "-fx-text-fill: white; " +
                            "-fx-border-color: darkgray; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 3, 3);"
            );
        });
        btnSair.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.getChildren().addAll(btnComecar, btnhLog, btnTutorial, btnSair);
        layout.setStyle("-fx-alignment: center;");

        Image image = new Image("BG_INI.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        layout.setBackground(background);

        Scene cena = new Scene(layout, 600, 620);
        primaryStage.setScene(cena);

        databaseInitTask.setOnSucceeded(event -> {
            primaryStage.show();
        });

        Thread thread = new Thread(databaseInitTask);
        thread.start();
    }

    private void inicializarBancoDeDados() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            return;
        }
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS Played (PlayedID INT, Status VARCHAR(255))";
            statement.executeUpdate(query);
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
