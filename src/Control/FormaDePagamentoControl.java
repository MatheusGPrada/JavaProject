package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.SQLConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import Entity.FormaDePagamento;

public class FormaDePagamentoControl {
	private static final String URL = "jdbc:mariadb://localhost/Clienteshop?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";

	public FormaDePagamentoControl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException error) {
			alertError("Erro de database", "Erro ao carregar a classe JDBC", error.getMessage());
		}
	}

	private void alertError(String titulo, String cabecalho, String conteudo) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		alert.showAndWait();
	}


	public void adicionar(FormaDePagamento FormaPag) {
		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASS);
			String query = "INSERT INTO FormaPag (id, descricao, num_cartao, codigo_seguranca) VALUES  (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, FormaPag.getId());
			statement.setString(2, FormaPag.getDescricao());
            statement.setInt(3, FormaPag.getNumCartao());
            statement.setInt(4, FormaPag.getCodSeg());

			statement.executeUpdate();

			connection.close();
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao realizar conexão com o banco de dados", error.getMessage());
		}
	}

	public boolean excluirPorId(int Id) {
		try {

			Connection connection = SQLConnection.getConnection();
			String query = "select * from FormaPag where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, Id);
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				query = "delete from FormaPag where id = ?";
				statement = connection.prepareStatement(query);
				statement.setInt(1, Id);
				statement.executeQuery();
				return true;
			}

			connection.close();

		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao realizar exclusão no banco de dados", error.getMessage());
		}
		return false;
	}

}
