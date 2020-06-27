package Principal;

import java.util.Optional;

import Boundary.ClienteBoundary;
import Control.ClienteControl;
import Entity.Cliente;
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
	private static Cliente cliente = new Cliente();
	private Button btnSalvarCliente = new Button("Incluir");
	private Button btnEditarCliente = new Button("Editar");
	private Button btnExcluirCliente = new Button("Excluir");

	private ClienteBoundary ClienteBoundary = new ClienteBoundary();
	private ClienteControl control = new ClienteControl();
	private Stage stage = new Stage();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scene = new Scene(panPrincipal, 400, 250);

		GridPane Campos = new GridPane();

		Campos.add(new Label("                "), 0, 1);
		Campos.add(new Label("Cliente"), 1, 2);
		Campos.add(new Label("                "), 2, 2);
		Campos.add(btnSalvarCliente, 3, 2);
		Campos.add(btnEditarCliente,  4, 2);
		Campos.add(btnExcluirCliente, 5, 2);

		btnSalvarCliente.setOnAction(this);
		btnEditarCliente.setOnAction(this);
		btnExcluirCliente.setOnAction(this);

		panPrincipal.setCenter(Campos);

		stage.setScene(scene);
		stage.setTitle("Cadastro de Cliente");
		stage.show();
	}

	public void handle(ActionEvent event) {
		if (event.getTarget() == btnSalvarCliente) {
			try {
				ClienteBoundary ClienteBoundary = new ClienteBoundary();
				ClienteBoundary.start(stage);
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else if (event.getTarget() == btnEditarCliente){
			TextInputDialog dialog = new TextInputDialog("CPF");
			dialog.setTitle("Editar Cliente");
			dialog.setHeaderText("Insira o CPF que será editado");
			dialog.setContentText("CPF do Cliente");

			Optional<String> result = dialog.showAndWait();
			if (CPF.isEmpty()){
				CPF = result.get();
				cliente = control.pesquisarPorCPF(CPF);
			}
			try {
				ClienteBoundary.entityToBoundary(cliente, true);
				ClienteBoundary.start(stage);
			} catch (Exception error) {
				error.printStackTrace();
			}

		} else if (event.getTarget() == btnExcluirCliente){
			TextInputDialog dialog = new TextInputDialog("CPF");
			dialog.setTitle("Excluir Cliente");
			dialog.setHeaderText("Insira o CPF que será excluído");
			dialog.setContentText("CPF do Cliente");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CPF = result.get();
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
	}


	public static void main(String[] args) {
		Application.launch(Principal.class, args);
	}


}