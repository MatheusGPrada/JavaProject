package Principal;

import java.util.Optional;

import Boundary.ClienteBoundary;
import Boundary.ProdutoBoundary;
import Control.ClienteControl;
import Control.ProdutoControl;
import Entity.Cliente;
import Entity.Produto;
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
	private Button btnIncluirCliente = new Button("Incluir");
	private Button btnEditarCliente = new Button("Editar");
	private Button btnVisualizarCliente = new Button("Visualizar");
	private Button btnExcluirCliente = new Button("Excluir");

	private static String CodProd = "";
	private static Produto produto = new Produto();
	private Button btnIncluirProduto = new Button("Incluir");
	private Button btnEditarProduto = new Button("Editar");
	private Button btnVisualizarProduto = new Button("Visualizar");
	private Button btnExcluirProduto = new Button("Excluir");

	private ClienteBoundary clienteBoundary = new ClienteBoundary();
	private ClienteControl clienteControl = new ClienteControl();
	private ProdutoBoundary produtoBoundary = new ProdutoBoundary();
	private ProdutoControl produtoControl = new ProdutoControl();
	private Stage stage = new Stage();

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
		Campos.add(btnVisualizarCliente,  5, 2);
		Campos.add(btnExcluirCliente, 6, 2);
		Campos.add(new Label("                "), 0, 3);
		Campos.add(new Label("Produto"), 1, 4);
		Campos.add(new Label("                "), 2, 4);
		Campos.add(btnIncluirProduto, 3, 4);
		Campos.add(btnEditarProduto,  4, 4);
		Campos.add(btnVisualizarProduto,  5, 4);
		Campos.add(btnExcluirProduto, 6, 4);

		btnIncluirCliente.setOnAction(this);
		btnEditarCliente.setOnAction(this);
		btnVisualizarCliente.setOnAction(this);
		btnExcluirCliente.setOnAction(this);

		btnIncluirProduto.setOnAction(this);
		btnEditarProduto.setOnAction(this);
		btnVisualizarProduto.setOnAction(this);
		btnExcluirProduto.setOnAction(this);

		panPrincipal.setCenter(Campos);

		stage.setScene(scene);
		stage.setTitle("Cadastro de Cliente");
		stage.show();
	}

	public void handle(ActionEvent event) {
		if (event.getTarget() == btnIncluirCliente) {
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
			if (result.isPresent()){
				CPF = result.get();
				cliente = clienteControl.pesquisarPorCPF(CPF);
				if (cliente != null){
					try {
						clienteBoundary.entityToBoundary(cliente, "editar", CPF);
						clienteBoundary.start(stage);
					} catch (Exception error) {
						error.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Edição de cliente");
					alert.setHeaderText(null);
					alert.setContentText("CPF inválido!");
					alert.showAndWait();
				}
			}

		} else if (event.getTarget() == btnVisualizarCliente) {
			TextInputDialog dialog = new TextInputDialog("CPF");
			dialog.setTitle("Visualizar Cliente");
			dialog.setHeaderText("Insira o CPF que deseja visualizar");
			dialog.setContentText("CPF do Cliente");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CPF = result.get();
				cliente = clienteControl.pesquisarPorCPF(CPF);
				if (cliente != null){
					try {
						clienteBoundary.entityToBoundary(cliente, "visualizar", CPF);
						clienteBoundary.start(stage);
					} catch (Exception error) {
						error.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Visualização de cliente");
					alert.setHeaderText(null);
					alert.setContentText("CPF inválido!");
					alert.showAndWait();
				}
			}
		}else if (event.getTarget() == btnExcluirCliente){
			TextInputDialog dialog = new TextInputDialog("CPF");
			dialog.setTitle("Excluir Cliente");
			dialog.setHeaderText("Insira o CPF que será excluído");
			dialog.setContentText("CPF do Cliente");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CPF = result.get();
				if (clienteControl.excluirPorCPF(CPF)){
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
		} else if (event.getTarget() == btnIncluirProduto) {
			try {
				ProdutoBoundary produtoBoundary = new ProdutoBoundary();
				produtoBoundary.start(stage);
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else if (event.getTarget() == btnEditarProduto) {
			TextInputDialog dialog = new TextInputDialog("Código Produto");
			dialog.setTitle("Editar Produto");
			dialog.setHeaderText("Insira o código do produto que será editado");
			dialog.setContentText("Código do Produto");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CodProd = result.get();
				produto = produtoControl.pesquisarPorCod(Integer.parseInt(CodProd));
				try {
					produtoBoundary.entityToBoundary(produto, "editar", CodProd);
					produtoBoundary.start(stage);
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		} else if (event.getTarget() == btnVisualizarProduto) {
			TextInputDialog dialog = new TextInputDialog("Código Produto");
			dialog.setTitle("Visualizar Produto");
			dialog.setHeaderText("Insira o código do produto que deseja visualizar");
			dialog.setContentText("Código Produto");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CodProd = result.get();
				produto = produtoControl.pesquisarPorCod(Integer.parseInt(CodProd));
				if (produto != null){
					try {
						produtoBoundary.entityToBoundary(produto, "visualizar", CodProd);
						produtoBoundary.start(stage);
					} catch (Exception error) {
						error.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Visualização de cliente");
					alert.setHeaderText(null);
					alert.setContentText("CPF inválido!");
					alert.showAndWait();
				}
			}

		} else if (event.getTarget() == btnExcluirProduto){
			TextInputDialog dialog = new TextInputDialog("Código Produto");
			dialog.setTitle("Excluir Produto");
			dialog.setHeaderText("Insira o código do produto que será excluído");
			dialog.setContentText("Código do Produto");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CodProd = result.get();
				if (produtoControl.excluirPorCod(Integer.parseInt(CodProd))){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclusão de produto");
					alert.setHeaderText(null);
					alert.setContentText("Produto excluído com sucesso!");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclusão de produto");
					alert.setHeaderText(null);
					alert.setContentText("Código de produto inválido!");
					alert.showAndWait();
				}
			}
		}
	}


	public static void main(String[] args) {
		Application.launch(Principal.class, args);
	}


}