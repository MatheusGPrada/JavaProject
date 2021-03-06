package Principal;

import java.util.Optional;

import Boundary.ClienteBoundary;
import Boundary.FuncionarioBoundary;
import Boundary.LoginBoundary;
import Boundary.ProdutoBoundary;

import Control.ClienteControl;
import Control.FuncionarioControl;
import Control.LoginControl;
import Control.ProdutoControl;

import Entity.Cliente;
import Entity.Funcionario;
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

public class MenuPrincipal extends Application implements EventHandler<ActionEvent>{

	private static String CPFCliente = "";
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

	private static String CPFFuncionario = "";
	private static Funcionario funcionario = new Funcionario();
	private Button btnIncluirFuncionario = new Button("Incluir");
	private Button btnEditarFuncionario = new Button("Editar");
	private Button btnVisualizarFuncionario = new Button("Visualizar");
	private Button btnExcluirFuncionario = new Button("Excluir");

	private static String User = "";
	private Button btnIncluirLogin = new Button("Incluir");
	private Button btnExcluirLogin = new Button("Excluir");

	private ClienteBoundary clienteBoundary = new ClienteBoundary();
	private ClienteControl clienteControl = new ClienteControl();
	private FuncionarioBoundary funcionarioBoundary = new FuncionarioBoundary();
	private FuncionarioControl funcionarioControl = new FuncionarioControl();
	private ProdutoBoundary produtoBoundary = new ProdutoBoundary();
	private ProdutoControl produtoControl = new ProdutoControl();
	private LoginBoundary loginBoundary = new LoginBoundary();
	private LoginControl loginControl = new LoginControl();
	private Stage stage = new Stage();


	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scene = new Scene(panPrincipal, 400, 200);

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
		Campos.add(new Label("                "), 0, 5);
		Campos.add(new Label("Funcionario"), 1, 6);
		Campos.add(new Label("                "), 2, 6);
		Campos.add(btnIncluirFuncionario, 3, 6);
		Campos.add(btnEditarFuncionario,  4, 6);
		Campos.add(btnVisualizarFuncionario,  5, 6);
		Campos.add(btnExcluirFuncionario, 6, 6);
		Campos.add(new Label("                "), 0, 7);
		Campos.add(new Label("Login"), 1, 8);
		Campos.add(btnIncluirLogin, 3, 8);
		Campos.add(btnExcluirLogin, 4, 8);
		Campos.add(new Label("                "), 0, 9);

		btnIncluirCliente.setOnAction(this);
		btnEditarCliente.setOnAction(this);
		btnVisualizarCliente.setOnAction(this);
		btnExcluirCliente.setOnAction(this);

		btnIncluirFuncionario.setOnAction(this);
		btnEditarFuncionario.setOnAction(this);
		btnVisualizarFuncionario.setOnAction(this);
		btnExcluirFuncionario.setOnAction(this);

		btnIncluirProduto.setOnAction(this);
		btnEditarProduto.setOnAction(this);
		btnVisualizarProduto.setOnAction(this);
		btnExcluirProduto.setOnAction(this);

		btnIncluirLogin.setOnAction(this);
		btnExcluirLogin.setOnAction(this);

		panPrincipal.setCenter(Campos);

		stage.setScene(scene);
		stage.setTitle("Tela Principal");
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
			dialog.setHeaderText("Insira o CPF que ser� editado");
			dialog.setContentText("CPF do Cliente");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CPFCliente = result.get();
				cliente = clienteControl.pesquisarPorCPF(CPFCliente);
				if (cliente != null){
					try {
						clienteBoundary.entityToBoundary(cliente, "editar", CPFCliente);
						clienteBoundary.start(stage);
					} catch (Exception error) {
						error.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Edi��o de cliente");
					alert.setHeaderText(null);
					alert.setContentText("CPF inv�lido!");
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
				CPFCliente = result.get();
				cliente = clienteControl.pesquisarPorCPF(CPFCliente);
				if (cliente != null){
					try {
						clienteBoundary.entityToBoundary(cliente, "visualizar", CPFCliente);
						clienteBoundary.start(stage);
					} catch (Exception error) {
						error.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Visualiza��o de cliente");
					alert.setHeaderText(null);
					alert.setContentText("CPF inv�lido!");
					alert.showAndWait();
				}
			}
		}else if (event.getTarget() == btnExcluirCliente){
			TextInputDialog dialog = new TextInputDialog("CPF");
			dialog.setTitle("Excluir Cliente");
			dialog.setHeaderText("Insira o CPF que ser� exclu�do");
			dialog.setContentText("CPF do Cliente");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CPFCliente = result.get();
				if (clienteControl.excluirPorCPF(CPFCliente)){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclus�o de cliente");
					alert.setHeaderText(null);
					alert.setContentText("Cliente exclu�do com sucesso!");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclus�o de cliente");
					alert.setHeaderText(null);
					alert.setContentText("CPF inv�lido!");
					alert.showAndWait();
				}
			}
		}else if (event.getTarget() == btnIncluirFuncionario) {
			try {
				FuncionarioBoundary FuncionarioBoundary = new FuncionarioBoundary();
				FuncionarioBoundary.start(stage);
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else if (event.getTarget() == btnEditarFuncionario){
			TextInputDialog dialog = new TextInputDialog("CPF funcion�rio");
			dialog.setTitle("Editar funcion�rio");
			dialog.setHeaderText("Insira o CPF que ser� editado");
			dialog.setContentText("CPF do funcion�rio");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CPFFuncionario = result.get();
				funcionario = funcionarioControl.pesquisarPorCPF(CPFFuncionario);
				if (funcionario != null){
					try {
						funcionarioBoundary.entityToBoundary(funcionario, "editar", CPFFuncionario);
						funcionarioBoundary.start(stage);
					} catch (Exception error) {
						error.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Edi��o de funcion�rio");
					alert.setHeaderText(null);
					alert.setContentText("CPF inv�lido!");
					alert.showAndWait();
				}
			}

		} else if (event.getTarget() == btnVisualizarFuncionario) {
			TextInputDialog dialog = new TextInputDialog("C�digo Funcionario");
			dialog.setTitle("Visualizar Funcionario");
			dialog.setHeaderText("Insira o c�digo do funcionario que deseja visualizar");
			dialog.setContentText("C�digo do Cliente");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CPFFuncionario = result.get();
				funcionario = funcionarioControl.pesquisarPorCPF(CPFFuncionario);
				if (funcionario != null){
					try {
						funcionarioBoundary.entityToBoundary(funcionario, "visualizar", CPFFuncionario);
						funcionarioBoundary.start(stage);
					} catch (Exception error) {
						error.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Visualiza��o de funcionario");
					alert.setHeaderText(null);
					alert.setContentText("C�digo inv�lido!");
					alert.showAndWait();
				}
			}
		}else if (event.getTarget() == btnExcluirFuncionario){
			TextInputDialog dialog = new TextInputDialog("C�digo Funcionario");
			dialog.setTitle("Excluir Funcionario");
			dialog.setHeaderText("Insira o o funcionario que ser� exclu�do");
			dialog.setContentText("C�digo do Funcionario");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CPFFuncionario = result.get();
				if (funcionarioControl.excluirPorCPF(CPFFuncionario)){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclus�o de funcionario");
					alert.setHeaderText(null);
					alert.setContentText("Funcionario exclu�do com sucesso!");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclus�o de funcionario");
					alert.setHeaderText(null);
					alert.setContentText("C�digo de funcionario inv�lido!");
					alert.showAndWait();
				}
			}
		}

		else if (event.getTarget() == btnIncluirProduto) {
			try {
				ProdutoBoundary produtoBoundary = new ProdutoBoundary();
				produtoBoundary.start(stage);
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else if (event.getTarget() == btnEditarProduto) {
			TextInputDialog dialog = new TextInputDialog("C�digo Produto");
			dialog.setTitle("Editar Produto");
			dialog.setHeaderText("Insira o c�digo do produto que ser� editado");
			dialog.setContentText("C�digo do Produto");

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
			TextInputDialog dialog = new TextInputDialog("C�digo Produto");
			dialog.setTitle("Visualizar Produto");
			dialog.setHeaderText("Insira o c�digo do produto que deseja visualizar");
			dialog.setContentText("C�digo Produto");

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
					alert.setTitle("Visualiza��o de cliente");
					alert.setHeaderText(null);
					alert.setContentText("CPF inv�lido!");
					alert.showAndWait();
				}
			}

		} else if (event.getTarget() == btnExcluirProduto) {
			TextInputDialog dialog = new TextInputDialog("C�digo Produto");
			dialog.setTitle("Excluir Produto");
			dialog.setHeaderText("Insira o c�digo do produto que ser� exclu�do");
			dialog.setContentText("C�digo do Produto");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				CodProd = result.get();
				if (produtoControl.excluirPorCod(Integer.parseInt(CodProd))){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclus�o de produto");
					alert.setHeaderText(null);
					alert.setContentText("Produto exclu�do com sucesso!");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclus�o de produto");
					alert.setHeaderText(null);
					alert.setContentText("C�digo de produto inv�lido!");
					alert.showAndWait();
				}
			}
		} else if (event.getTarget() == btnIncluirLogin){
			try {
				loginBoundary.start(stage);
			} catch (Exception error) {
				error.printStackTrace();
			}
		} else if (event.getTarget() == btnExcluirLogin) {
			TextInputDialog dialog = new TextInputDialog("Usu�rio");
			dialog.setTitle("Excluir Usu�rio");
			dialog.setHeaderText("Insira o Usu�rio que ser� exclu�do");
			dialog.setContentText("Usu�rio");

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				User = result.get();
				if (loginControl.excluirPorUser(User)){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclus�o de usu�rio");
					alert.setHeaderText(null);
					alert.setContentText("Usu�rio exclu�do com sucesso!");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exclus�o de Usu�rio");
					alert.setHeaderText(null);
					alert.setContentText("Usu�rio inv�lido!");
					alert.showAndWait();
				}
			}
		}
	}

}