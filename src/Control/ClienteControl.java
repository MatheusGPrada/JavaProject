package Control;

import Entity.Cliente;
import Connection.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ClienteControl {


	public ClienteControl() {
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

	public void adicionar(Cliente cliente) {
		try {
			Connection connection = SQLConnection.getConnection();
			String query = "INSERT INTO Cliente (nome, cpf, rg, endereco, dt_nascimento, sexo, telefone) VALUES  (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cliente.getnome());
			statement.setString(2, cliente.getCPFCliente());
            statement.setString(3, cliente.getRGCliente());
            statement.setString(4, cliente.getendereco());
            statement.setString(5, cliente.getdtnascimento());
            statement.setString(6, cliente.getsexo());
            statement.setString(7, cliente.gettelefone());
			statement.executeUpdate();

			connection.close();
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao realizar conexão com o banco de dados", error.getMessage());
		}
	}

	public void editar(Cliente cliente,  String CPF) {
		try {
			Connection connection = SQLConnection.getConnection();

			String query = "UPDATE Cliente SET nome = ?, cpf = ?, rg = ?, endereco = ?, dt_nascimento = ?, sexo = ?, telefone = ? WHERE cpf = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cliente.getnome());
			statement.setString(2, cliente.getCPFCliente());
			statement.setString(3, cliente.getRGCliente());
            statement.setString(4, cliente.getendereco());
            statement.setString(5, cliente.getdtnascimento());
            statement.setString(6, cliente.getsexo());
            statement.setString(7, cliente.gettelefone());
            statement.setString(8, CPF);
			statement.executeUpdate();

			connection.close();
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao atualizar registros no banco de dados", error.getMessage());
		}
	}

	public Cliente pesquisarPorCPF(String CPF) {
		try {

			Connection connection = SQLConnection.getConnection();

            String query = "select * from cliente where cpf = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, CPF );
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				Cliente cliente = new Cliente();
				cliente.setNome(result.getString("nome"));
				cliente.setCPFCliente(result.getString("cpf"));
				cliente.setRGCliente(result.getString("rg"));
				cliente.setendereco(result.getString("endereco"));
				cliente.setdtnascimento(result.getString("dt_nascimento"));
				cliente.setsexo(result.getString("sexo"));
				cliente.settelefone(result.getString("telefone"));
				return cliente;
			}
			connection.close();

		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao realizar consulta no banco de dados", error.getMessage());
		}
		return null;
	}

	public boolean excluirPorCPF(String CPF) {
		try {

			Connection connection = SQLConnection.getConnection();
			String query = "select * from cliente where cpf = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, CPF);
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				query = "delete from cliente where cpf = ?";
				statement = connection.prepareStatement(query);
				statement.setString(1, CPF);
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