package Control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.SQLConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import Entity.Cliente;
import Entity.Produto;

public class ProdutoControl {
	
	
		private static final String URL = "jdbc:mariadb://localhost/Clienteshop?allowMultiQueries=true";
		private static final String USER = "root";
		private static final String PASS = "";

		public ProdutoControl() {
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

		public void adicionar(Produto produto) {
			try {
				Connection connection = DriverManager.getConnection(URL, USER, PASS);
				String query = "INSERT INTO Produto (nome, valor, quantidade, codigo_produto) VALUES  (?, ?, ?, ?)";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, produto.getNome());
				statement.setString(2, produto.getValor());
	            statement.setInt(3, produto.getQtd());
	            statement.setInt(4, produto.getCodProd());
	            
				statement.executeUpdate();

				connection.close();
			} catch (SQLException error) {
				alertError("Erro de database", "Erro ao realizar conexão com o banco de dados", error.getMessage());
			}
		}
		
		public void editar(Produto produto) {
			try {
				Connection connection = SQLConnection.getConnection();
				String query = "UPDATE Produto SET nome = ?, valor = ?, quantidade = ?, cod_produto = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, produto.getNome());
	            statement.setString(2, produto.getValor());
	            statement.setLong(3, produto.getQtd());
	            statement.setLong(4, produto.getCodProd());
	            statement.executeUpdate();

				connection.close();
			} catch (SQLException error) {
				alertError("Erro de database", "Erro ao atualizar registros no banco de dados", error.getMessage());
			}
		}

		public Produto pesquisarPorCod(int CodProd) {
			try {

	            Connection connection = DriverManager.getConnection(URL, USER, PASS);

	            String query = "SELECT * FROM Produto WHERE cod_cliente = ?";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, CodProd );
				ResultSet result = statement.executeQuery();
				if (result.first()) {
					Produto produto = new Produto();
					produto.setNome(result.getString("nome"));
					produto.setValor(result.getString("valor"));
					produto.setQtd(result.getInt("quantidade"));
					produto.setCodProd(result.getInt("codigo_produto"));
					
					return produto;
				}
				connection.close();

			} catch (SQLException error) {
				alertError("Erro de database", "Erro ao realizar consulta no banco de dados", error.getMessage());
			}
			return null;
		}
		
		public boolean excluirPorCod(int CodProd) {
			try {

				Connection connection = SQLConnection.getConnection();
				String query = "select * from produto where cod_produto = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, CodProd);
				ResultSet result = statement.executeQuery();
				if (result.first()) {
					query = "delete from produto where cod_produto = ?";
					statement = connection.prepareStatement(query);
					statement.setInt(1, CodProd);
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
