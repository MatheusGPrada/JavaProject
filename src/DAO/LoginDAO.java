package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.SQLConnection;
import Entity.Login;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginDAO {

	public void adicionar(Login login) {
		try {
			Connection connection = SQLConnection.getConnection();
			String query = "INSERT INTO Login (user, password) VALUES  (?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, login.getUser());
			statement.setString(2, login.getPassword());
			statement.executeUpdate();

			SQLConnection.closeConnection(connection, statement);
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao realizar conexão com o banco de dados", error.getMessage());
		}
	}

	public void editar(Login login,  String User) {
		try {
			Connection connection = SQLConnection.getConnection();

			String query = "UPDATE login SET user = ?, password = ? WHERE user = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, login.getUser());
			statement.setString(2, login.getPassword());
            statement.setString(3, User);
			statement.executeUpdate();

			SQLConnection.closeConnection(connection, statement);
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao atualizar registros no banco de dados", error.getMessage());
		}
	}

	public Boolean efetuarLogin(String User, String Password) {
		try {
			Connection connection = SQLConnection.getConnection();
	        String query = "select * from login where user = ? AND password = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, User );
			statement.setString(2, Password );
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao recuperar registros no banco de dados", error.getMessage());
		}
		return null;
	}

	public Login pesquisarPorUser(String User) {
		try {
			Connection connection = SQLConnection.getConnection();
	        String query = "select * from login where user = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, User );
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				Login login = new Login();
				login.setUser(result.getString("user"));
				login.setPassword(result.getString("password"));
				return login;
			} else {
				return null;
			}
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao recuperar registros no banco de dados", error.getMessage());
		}
		return null;
	}

	public boolean excluirPorUser(String User){
		try {
			Connection connection = SQLConnection.getConnection();
			String query = "select * from login where user = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, User);
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				query = "delete from login where user = ?";
				statement = connection.prepareStatement(query);
				statement.setString(1, User);
				statement.executeQuery();
				return true;
			}

			SQLConnection.closeConnection(connection, statement, result);
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao realizar exclusão no banco de dados", error.getMessage());
		}
		return false;
	}

	private void alertError(String titulo, String cabecalho, String conteudo) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		alert.showAndWait();
	}

}
