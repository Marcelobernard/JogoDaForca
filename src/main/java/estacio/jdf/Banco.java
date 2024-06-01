package estacio.jdf;

import java.sql.*;

public class Banco {
    public static void inserirNovoRegistro(String condicao) {
        String url = "jdbc:h2:~/history";

        try (Connection connection = DriverManager.getConnection(url)) {
            String queryLastID = "SELECT PlayedID FROM Played ORDER BY PlayedID DESC LIMIT 1";
            PreparedStatement getLastIDStatement = connection.prepareStatement(queryLastID);
            ResultSet resultSet = getLastIDStatement.executeQuery();

            int lastID = 0;
            if (resultSet.next()) {
                lastID = resultSet.getInt("PlayedID");
            }

            int newID = lastID + 1;

            String queryInsert = "INSERT INTO Played (PlayedID, Status) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(queryInsert);
            insertStatement.setInt(1, newID);
            insertStatement.setString(2, condicao);
            insertStatement.executeUpdate();

            //System.out.println("Novo registro inserido com sucesso!");
        } catch (SQLException e) {
            //System.out.println("Erro ao inserir novo registro: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
    }
}
