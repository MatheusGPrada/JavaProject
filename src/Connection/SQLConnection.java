package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SQLConnection {

	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost/farmacia?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";

	public static void alertError(String titulo, String cabecalho, String conteudo) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		alert.showAndWait();
	}

	public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException exception) {
            throw new RuntimeException("Erro na conexão: ", exception);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statmente, ResultSet result) {

    	try {
            if (connection != null) {
            	connection.close();
            }

            if (statmente != null) {
            	statmente.close();
            }

            if (result != null) {
            	result.close();
            }

        } catch (SQLException error) {
        	alertError("Erro de database", "Erro ao encerrar conexão", error.getMessage());
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statmente) {

    	try {
            if (connection != null) {
            	connection.close();
            }

            if (statmente != null) {
            	statmente.close();
            }


        } catch (SQLException error) {
        	alertError("Erro de database", "Erro ao encerrar conexão", error.getMessage());
        }
    }



}
