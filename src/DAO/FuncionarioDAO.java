package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.SQLConnection;
import Entity.Funcionario;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FuncionarioDAO {

	public void adicionar(Funcionario funcionario) {
		try {
			Connection connection = SQLConnection.getConnection();
			String query = "INSERT INTO Funcionario ( nome, cpf, rg, endereco, dt_registro, sexo) VALUES  ( ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, funcionario.getnome());
			statement.setString(2, funcionario.getCPFFunc());
            statement.setString(3, funcionario.getRGFunc());
            statement.setString(4, funcionario.getendereco());
            statement.setString(5, funcionario.getdtregistro());
            statement.setString(6, funcionario.getsexo());
			statement.executeUpdate();

			connection.close();
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao realizar conexão com o banco de dados", error.getMessage());
		}
	}

	public void editar(Funcionario funcionario, String CPF) {
		try {
			Connection connection = SQLConnection.getConnection();
			String query = "UPDATE Funcionario SET nome = ?, cpf = ?, rg = ?, endereco = ?, dt_registro = ?, sexo = ? WHERE cpf = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, funcionario.getnome());
			statement.setString(2, funcionario.getCPFFunc());
            statement.setString(3, funcionario.getRGFunc());
            statement.setString(4, funcionario.getendereco());
            statement.setString(5, funcionario.getdtregistro());
            statement.setString(6, funcionario.getsexo());
            statement.setString(7, CPF);
			statement.executeUpdate();

			connection.close();
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao atualizar registros no banco de dados", error.getMessage());
		}
	}

	public Funcionario pesquisarPorCPF(String CPF) {
		try {

			Connection connection = SQLConnection.getConnection();

            String query = "select * from funcionario where cpf = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, CPF );
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(result.getString("nome"));
				funcionario.setCPFFunc(result.getString("cpf"));
				funcionario.setRGFunc(result.getString("rg"));
				funcionario.setendereco(result.getString("endereco"));
				funcionario.setdtregistro(result.getString("dt_registro"));
				funcionario.setsexo(result.getString("sexo"));

				return funcionario;
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
			String query = "select * from funcionario where cpf = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, CPF);
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				query = "delete from funcionario where cpf = ?";
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

	private void alertError(String titulo, String cabecalho, String conteudo) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		alert.showAndWait();
	}
}
