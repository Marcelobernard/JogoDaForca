package estacio.jdf;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tutorial extends Application {
    int foto = 0;
    private List<Character> palavraLista = new ArrayList<>();
    private List<Character> letrasCorretas = new ArrayList<>();
    private Text palavraLabel;
    private boolean primeiroAcertoMostrado = false;

    @Override
    public void start(Stage primaryStage) {
        //Layout
        VBox root = new VBox();
        Random random = new Random();
        String palavraEscolhida = "SKOLL"; //Usada apenas no tutorial
        palavraLista.clear();
        for (char letra : palavraEscolhida.toCharArray()) {
            letra = Character.toUpperCase(letra);
            palavraLista.add(letra);
        }

        ImageView imageView = new ImageView();
        HBox caixaImagem = new HBox(imageView);
        caixaImagem.setAlignment(Pos.CENTER);
        try {
            Image imagem = new Image("boneco40.png");
            imageView.setImage(imagem);
            imageView.setFitWidth(300);
            imageView.setFitHeight(400);
        } catch (IllegalArgumentException e) {
            //System.err.println("Erro ao carregar a imagem: " + e.getMessage());
        }
        root.getChildren().add(caixaImagem);

        //Aparência da palavra a ser adivinhada
        palavraLabel = new Text(getPalavraEscondida());
        palavraLabel.setFont(Font.font("Arial", 100));
        palavraLabel.setFill(Color.WHITE);
        palavraLabel.setStroke(Color.BLACK);
        palavraLabel.setStrokeWidth(1);
        int tamanhoPalavra = palavraLista.size();
        if (tamanhoPalavra > 9) {
            palavraLabel.setFont(Font.font("Arial", 50));
            VBox vbox = new VBox(palavraLabel);
            vbox.setMinHeight(50);
            root.getChildren().add(vbox);
        } else if (tamanhoPalavra > 5) {
            palavraLabel.setFont(Font.font("Arial", 80));
        }
        HBox caixaPalavra = new HBox(palavraLabel);
        caixaPalavra.setAlignment(Pos.CENTER);
        root.getChildren().add(caixaPalavra);

        //Botões virtuais
        GridPane painelBotoes = new GridPane();
        painelBotoes.setPadding(new Insets(10));
        painelBotoes.setHgap(5);
        painelBotoes.setVgap(5);
        //System.out.println(palavraEscolhida);
        int col = 0;
        int row = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            Button botao = new Button(String.valueOf(c));
            botao.setPrefSize(40, 40);
            if (!botao.getStyle().equals("-fx-background-color: green;") && !botao.getStyle().equals("-fx-background-color: red;"))  {
                botao.setStyle(
                        "-fx-background-color: linear-gradient(rgb(17, 30, 42), rgb(155, 44, 28)); " +
                                "-fx-text-fill: white; " +
                                "-fx-border-color: darkgray; " +
                                "-fx-border-width: 1px; " +
                                "-fx-border-radius: 5px; " +
                                "-fx-background-radius: 5px; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 3, 3);"
                );

            }
            // Primeiro popup explicando quantas chances a pessoa tem até perder
            botao.setOnAction(e -> {
                char letra = botao.getText().charAt(0);
                if (palavraLista.contains(letra) && !letrasCorretas.contains(letra)) {
                    botao.setStyle("-fx-background-color: green;");
                    botao.setDisable(true);
                    letrasCorretas.add(letra);
                    palavraLabel.setText(getPalavraEscondida());
                    //Popup avisando sobre o primeiro acerto
                    if (!primeiroAcertoMostrado) {
                        primeiroAcertoMostrado = true;
                        PrimeiroAcerto.mostrar(true, primaryStage);
                    }
                } else {
                    botao.setStyle("-fx-background-color: red;");
                    botao.setDisable(true);
                    foto++;
                    if (foto == 1){
                        Image imagem = new Image("boneco50.png");
                        imageView.setImage(imagem);
                        // Um aviso de que ela errou a letra e com isso perdeu 1 chance, restando 4 tentativas
                        // e que também o botão ficou vermelho porque a letra não está contida na palavra
                        DerrotaTutorial.mostrar(true, primaryStage);
                    }
                    else if (foto == 2){
                        Image imagem = new Image("boneco60.png");
                        imageView.setImage(imagem);
                    }
                    else if (foto == 3){
                        Image imagem = new Image("boneco70.png");
                        imageView.setImage(imagem);
                    }
                    else if (foto == 4){
                        Image imagem = new Image("boneco80.png");
                        imageView.setImage(imagem);
                    }
                    else if (foto == 5){
                        Image imagem = new Image("boneco90.png");
                        imageView.setImage(imagem);
                    }
                    else {
                        Image imagem = new Image("boneco.png");
                        imageView.setImage(imagem);
                        for (Node node : painelBotoes.getChildren()) {
                            if (node instanceof Button) {
                                ((Button) node).setDisable(true);
                            }
                        }
                        DerrotaTutorialEnd.mostrar(true, primaryStage);
                    }

                }
                boolean todasLetrasAdivinhadas = true;
                for (char letraPalavra : palavraLista) {
                    if (!letrasCorretas.contains(letraPalavra)) {
                        todasLetrasAdivinhadas = false;
                        break;
                    }
                }
                if (todasLetrasAdivinhadas) {
                    for (Node node : painelBotoes.getChildren()) {
                        if (node instanceof Button) {
                            ((Button) node).setDisable(true);
                        }
                    }
                    VitoriaTutorial.mostrar(true, primaryStage);
                }
            });

            painelBotoes.add(botao, col, row);
            col++;
            if (col > 12) {
                col = 0;
                row++;
            }
        }

        HBox caixaBotoes = new HBox(painelBotoes);
        caixaBotoes.setAlignment(Pos.CENTER);
        root.getChildren().add(caixaBotoes);

        Scene cena = new Scene(root, 600, 620);

        Image image = new Image("BG_JG1.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        root.setBackground(background);

        primaryStage.setScene(cena);
        primaryStage.setTitle("Jogo da Forca - Tutorial");
        primaryStage.setResizable(false);


        primaryStage.show();
        //O popup ficou embaixo para que ele não seja aberto
        //antes do programa, visando o agradável
        InicioTutorial.mostrar(true, primaryStage);
    }

    private String getPalavraEscondida() {
        StringBuilder palavraEscondida = new StringBuilder();
        for (char letra : palavraLista) {
            if (letrasCorretas.contains(letra)) {
                palavraEscondida.append(letra).append(" ");
            } else {
                palavraEscondida.append("_ ");
            }
        }
        return palavraEscondida.toString();
    }
}