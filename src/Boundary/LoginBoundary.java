package Boundary;

import Control.LoginControl;
import Entity.Login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginBoundary implements EventHandler<ActionEvent> {

	private TextField txtUser = new TextField();
    private TextField txtPassword = new TextField();

	private Button btnSalvar = new Button("Salvar");
	private Button btnLimpar = new Button("Limpar");

	private LoginControl control = new LoginControl();

	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scene = new Scene(panPrincipal, 300, 150);

		GridPane Campos = new GridPane();

		Campos.add(new Label("                "), 0, 1);
		Campos.add(new Label("Usuário: "), 1, 1);
		Campos.add(txtUser, 2, 1);
		Campos.add(new Label("Senha: "), 1, 2);
		Campos.add(txtPassword, 2, 2);
		Campos.add(btnSalvar, 1, 3);
		Campos.add(btnLimpar, 2, 3);

		btnSalvar.setOnAction(this);
		btnLimpar.setOnAction(this);

		panPrincipal.setCenter(Campos);

		stage.setScene(scene);
		stage.setTitle("Cadastro de Usuário");
		stage.show();
	}

	public void handle(ActionEvent event) {
		if (event.getTarget() == btnSalvar) {
			Login login = boundaryToEntity();
			if (control.pesquisarPorUser(login.getUser()) == null ) {
				control.adicionar(login);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Inclusão do usuário");
				alert.setHeaderText(null);
				alert.setContentText("Usuário incluído com sucesso!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Inclusão do usuário");
				alert.setHeaderText(null);
				alert.setContentText("Usuário já existe na base de dados!");
				alert.showAndWait();
			}
		} else if (event.getTarget() == btnLimpar){
			txtUser.setText("");
			txtPassword.setText("");
		}
	}

	public Login boundaryToEntity() {
		Login cliente = new Login();
		try {
            cliente.setUser( txtUser.getText() );
            cliente.setPassword( txtPassword.getText() );
		} catch (Exception ex) {
			System.out.println("Erro inserir o registro na tabela!");
		}
		return cliente;
	}

	public void entityToBoundary(Login login, String User) {
		txtUser.setText( login.getUser() );
		txtPassword.setText( login.getPassword() );
	}

}
