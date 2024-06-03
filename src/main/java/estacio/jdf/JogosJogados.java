package estacio.jdf;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.sql.*;

// Aqui é a página de histórico
public class JogosJogados extends Application {

    private static final String DATABASE_URL = "jdbc:h2:~/history";

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Últimos Resultados");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setStyle("-fx-text-fill: #ffffff;");

        VBox resultadosContainer = new VBox(10);
        resultadosContainer.setPadding(new Insets(20));
        resultadosContainer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75); -fx-background-radius: 10px");

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM Played ORDER BY PlayedID DESC LIMIT 10";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int playedID = resultSet.getInt("PlayedID");
                String status = resultSet.getString("Status");

                Text text1 = new Text("Jogo " + playedID + ": ");
                text1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                text1.setFill(Color.WHITE);

                Text text2 = new Text(status);
                text2.setFont(Font.font("Arial", FontWeight.BOLD, 16));

                if (status.equals("Vitória")) {
                    text2.setFill(Color.GREEN);
                } else {
                    text2.setFill(Color.RED);
                }

                TextFlow textFlow = new TextFlow(text1, text2);
                resultadosContainer.getChildren().add(textFlow);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o banco de dados: " + e.getMessage());
        }

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setFont(new Font("Arial", 16));
        btnVoltar.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-background-radius: 5px;");
        btnVoltar.setOnMouseEntered(e -> btnVoltar.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-background-radius: 5px;"));
        btnVoltar.setOnMouseExited(e -> btnVoltar.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-background-radius: 5px;"));
        btnVoltar.setOnAction(e -> {
            Inicio jogo = new Inicio();
            jogo.start(primaryStage);
        });

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(btnVoltar);
        layout.setStyle("-fx-alignment: center;");

        Image image = new Image("BG_JG1.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        root.setBackground(background);
        root.getChildren().addAll(title, resultadosContainer, layout);
        Scene scene = new Scene(root, 600, 620);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Últimos Resultados");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
