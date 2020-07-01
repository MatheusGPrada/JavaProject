package Principal;

import Control.LoginControl;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Principal extends Application implements EventHandler<ActionEvent>{

	private TextField txtUser = new TextField();
    private TextField txtPassword = new TextField();
	private Button btnLogin = new Button("Login");

	private Stage stageNew = new Stage();

	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scene = new Scene(panPrincipal, 350, 130);

		GridPane Campos = new GridPane();

		Campos.add(new Label("                "), 0, 1);
		Campos.add(new Label("User: "), 1, 1);
		Campos.add(txtUser, 2, 1);
		Campos.add(new Label("Password: "), 1, 2);
		Campos.add(txtPassword, 2, 2);
		Campos.add(btnLogin, 2, 3);

		btnLogin.setOnAction(this);

		panPrincipal.setCenter(Campos);

		stage.setScene(scene);
		stage.setTitle("Login");
		stage.show();
	}

	public void handle(ActionEvent event) {
		LoginControl control = new LoginControl();
		if (control.efetuarLogin(txtUser.getText(), txtPassword.getText())){
			try {
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				menuPrincipal.start(stageNew);
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Login");
			alert.setHeaderText(null);
			alert.setContentText("Usuário ou senha inválido!");
			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		Application.launch(Principal.class, args);
	}


}