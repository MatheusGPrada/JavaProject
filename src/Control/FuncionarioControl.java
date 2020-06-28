package Control;

import Entity.Funcionario;
import Connection.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FuncionarioControl{


	public FuncionarioControl() {
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

	public void adicionar(Funcionario funcionario) {
		try {
			Connection connection = SQLConnection.getConnection();
			String query = "INSERT INTO Funcionario (nome, cpf, rg, endereco, dt_nascimento, sexo, telefone) VALUES  (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, funcionario.getCodFunc());
			statement.setString(2, funcionario.getnome());
			statement.setString(3, funcionario.getCPFFunc());
            statement.setString(4, funcionario.getRGFunc());
            statement.setString(5, funcionario.getendereco());
            statement.setString(6, funcionario.getdtregistro());
            statement.setString(7, funcionario.getsexo());
			statement.executeUpdate();

			connection.close();
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao realizar conexão com o banco de dados", error.getMessage());
		}
	}

	public void editar(Funcionario funcionario) {
		try {
			Connection connection = SQLConnection.getConnection();
			String query = "UPDATE Funcionario SET nome = ?, endereco = ?, dt_nascimento = ?, sexo = ?, telefone = ? WHERE cpf = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, funcionario.getCodFunc());
			statement.setString(2, funcionario.getnome());
			statement.setString(3, funcionario.getCPFFunc());
            statement.setString(4, funcionario.getRGFunc());
            statement.setString(5, funcionario.getendereco());
            statement.setString(6, funcionario.getdtregistro());
            statement.setString(7, funcionario.getsexo());
			statement.executeUpdate();

			connection.close();
		} catch (SQLException error) {
			alertError("Erro de database", "Erro ao atualizar registros no banco de dados", error.getMessage());
		}
	}

	public Funcionario pesquisarPorCod(int CodFunc) {
		try {

			Connection connection = SQLConnection.getConnection();

            String query = "select * from funcionario where cpf = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, CodFunc );
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCodFunc(result.getInt("codFuncionario"));
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

	public boolean excluirPorCod(int CodFunc) {
		try {

			Connection connection = SQLConnection.getConnection();
			String query = "select * from funcionario where codFuncionario = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, CodFunc);
			ResultSet result = statement.executeQuery();
			if (result.first()) {
				query = "delete from funcionario where codFuncionario = ?";
				statement = connection.prepareStatement(query);
				statement.setInt(1, CodFunc);
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