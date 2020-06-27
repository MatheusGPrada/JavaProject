package Principal;

import java.util.Optional;

import Boundary.ClienteBoundary;
import Control.ClienteControl;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Principal extends Application implements EventHandler<ActionEvent>{

	private static String CPF = "";
	private Button btnIncluirCliente = new Button("Incluir");
	private Button btnEditarCliente = new Button("Editar");
	private Button btnExcluirCliente = new Button("Excluir");

	private ClienteControl control = new ClienteControl();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scene = new Scene(panPrincipal, 400, 250);

		GridPane Campos = new GridPane();

		Campos.add(new Label("                "), 0, 1);
		Campos.add(new Label("Cliente"), 1, 2);
		Campos.add(new Label("                "), 2, 2);
		Campos.add(btnIncluirCliente, 3, 2);
		Campos.add(btnEditarCliente,  4, 2);
		Campos.add(btnExcluirCliente, 5, 2);

		btnIncluirCliente.setOnAction(this);
		btnEditarCliente.setOnAction(this);
		btnExcluirCliente.setOnAction(this);

		panPrincipal.setCenter(Campos);

		stage.setScene(scene);
		stage.setTitle("Cadastro de Cliente");
		stage.show();
	}

	public void handle(ActionEvent event) {
		if (event.getTarget() == btnIncluirCliente) {

		} else if (event.getTarget() == btnEditarCliente){
			TextInputDialog dialog = new TextInputDialog("CPF");
			dialog.setTitle("Editar Cliente");
			dialog.setHeaderText("Insira o CPF que será editado");
			dialog.setContentText("CPF do Cliente");

			Optional<String> result = dialog.showAndWait();
			CPF = result.get();
			ClienteControl control = new ClienteControl();
			control.pesquisarPorCPF(CPF);

		} else if (event.getTarget() == btnExcluirCliente){
			TextInputDialog dialog = new TextInputDialog("CPF");
			dialog.setTitle("Excluir Cliente");
			dialog.setHeaderText("Insira o CPF que será excluído");
			dialog.setContentText("CPF do Cliente");

			Optional<String> result = dialog.showAndWait();
			CPF = result.get();
			ClienteControl control = new ClienteControl();

			if (control.excluirPorCPF(CPF)){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Exclusão de cliente");
				alert.setHeaderText(null);
				alert.setContentText("Cliente excluído com sucesso!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Exclusão de cliente");
				alert.setHeaderText(null);
				alert.setContentText("CPF inválido!");
				alert.showAndWait();
			}
		}
	}


	public static void main(String[] args) {
		Application.launch(Principal.class, args);
	}

}