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

}
