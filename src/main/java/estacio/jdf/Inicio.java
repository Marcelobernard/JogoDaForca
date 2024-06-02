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

//Classe Principal de todo o projeto
//Ao longo do projeto será visível que
//todos os println estão marcados como comentário,
//isso é para impedir a abertura do CMD durante a execução
public class Inicio extends Application {
    private static final String DATABASE_URL = "jdbc:h2:~/history";
    @Override
    // Configuração da Interface Gráfica
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("murloc.png"));
        primaryStage.setTitle("Jogo da Forca");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);

        inicializarBancoDeDados();

        //Botões da página inicial
        Button btnComecar = new Button("Começar");
        btnComecar.setFont(new Font("Arial", 21));
        btnComecar.setStyle("-fx-background-color: #2E3031; -fx-text-fill: white; -fx-background-radius: 5px;");
        btnComecar.setOnMouseEntered(e -> {
            btnComecar.setStyle("-fx-background-color: #787C7E; -fx-text-fill: white; -fx-background-radius: 5px;");
            btnComecar.setFont(new Font("Arial", 26));
        });
        btnComecar.setOnMouseExited(e -> {
            btnComecar.setStyle("-fx-background-color: #2E3031; -fx-text-fill: white; -fx-background-radius: 5px;");
            btnComecar.setFont(new Font("Arial", 21));
        });
        btnComecar.setOnAction(e -> {
            JogoDaForca jogo = new JogoDaForca();
            jogo.start(primaryStage);
        });

        Button btnhLog = new Button("Consultar histórico");
        btnhLog.setFont(new Font("Arial", 21));
        btnhLog.setStyle("-fx-background-color: #1e28a7; -fx-text-fill: white; -fx-background-radius: 5px;");
        btnhLog.setOnMouseEntered(e -> {
            btnhLog.setStyle("-fx-background-color: #6632C8; -fx-text-fill: white; -fx-background-radius: 5px;");
            btnhLog.setFont(new Font("Arial", 26));
        });
        btnhLog.setOnMouseExited(e -> {
            btnhLog.setStyle("-fx-background-color: #1e28a7; -fx-text-fill: white; -fx-background-radius: 5px;");
            btnhLog.setFont(new Font("Arial", 21));
        });
        btnhLog.setOnAction(e -> {
            JogosJogados jogo = new JogosJogados();
            jogo.start(primaryStage);
        });

        Button btnTutorial = new Button("Tutorial");
        btnTutorial.setFont(new Font("Arial", 21));
        btnTutorial.setStyle("-fx-background-color: #CB8800; -fx-text-fill: white; -fx-background-radius: 5px;");
        btnTutorial.setOnMouseEntered(e -> {
            btnTutorial.setStyle("-fx-background-color: #D6A540; -fx-text-fill: white; -fx-background-radius: 5px;");
            btnTutorial.setFont(new Font("Arial", 26));
        });
        btnTutorial.setOnMouseExited(e -> {
            btnTutorial.setStyle("-fx-background-color: #CB8800; -fx-text-fill: white; -fx-background-radius: 5px;");
            btnTutorial.setFont(new Font("Arial", 21));
        });
        btnTutorial.setOnAction(e -> {
            Tutorial jogo = new Tutorial();
            jogo.start(primaryStage);
        });

        Button btnSair = new Button("Sair");
        btnSair.setFont(new Font("Arial", 21));
        btnSair.setStyle("-fx-background-color: #c82333; -fx-text-fill: white; -fx-background-radius: 5px;");
        btnSair.setOnMouseEntered(e -> {
                btnSair.setStyle("-fx-background-color: #dc3535; -fx-text-fill: white; -fx-background-radius: 5px;");
                btnSair.setFont(new Font("Arial", 26));
                });
        btnSair.setOnMouseExited(e -> {
                btnSair.setFont(new Font("Arial", 21));
                btnSair.setStyle("-fx-background-color: #c82333; -fx-text-fill: white; -fx-background-radius: 5px;");
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
        primaryStage.show();
    }

    //Inicialização do banco de dados
    //Aqui fará a primeira conexão e a verificação se o arquivo
    //já foi aberto alguma vez ou não para decidir se há necessidade
    //de criar a TABLE do banco de dados
    public static void main(String[] args) {
        launch(args);
    }
    private void inicializarBancoDeDados() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            //System.out.println("Driver H2 não encontrado");
            return;
        }
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS Played (PlayedID INT, Status VARCHAR(255))";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
