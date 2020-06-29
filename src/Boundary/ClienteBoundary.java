package Boundary;

import Entity.Cliente;
import Control.ClienteControl;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClienteBoundary extends Application implements EventHandler<ActionEvent>{

    private TextField txtNome = new TextField();
    private TextField txtCPF = new TextField();
    private TextField txtRG = new TextField();
    private TextField txtDtNascimento = new TextField();
	private TextField txtEndereco = new TextField();
	private ChoiceBox<String> checkSexo = new ChoiceBox<String>(FXCollections.observableArrayList("M", "F") );
	private TextField txtTelefone = new TextField();
	private String opcao = "incluir";
	private String CPF = "";

	private Button btnSalvar = new Button("Salvar");
	private Button btnLimpar = new Button("Limpar");

	private ClienteControl control = new ClienteControl();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scene = new Scene(panPrincipal, 400, 250);

		GridPane Campos = new GridPane();

		Campos.add(new Label("                "), 0, 1);
		Campos.add(new Label("Nome: "), 1, 1);
		Campos.add(txtNome, 2, 1);
		Campos.add(new Label("CPF: "), 1, 2);
		Campos.add(txtCPF, 2, 2);
		Campos.add(new Label("RG: "), 1, 3);
		Campos.add(txtRG, 2, 3);
		Campos.add(new Label("Data nascimento: "), 1, 4);
		Campos.add(txtDtNascimento, 2, 4);
		Campos.add(new Label("Endereço: "), 1, 5);
		Campos.add(txtEndereco, 2, 5);
		Campos.add(new Label("Sexo"), 1, 6);
        Campos.add(checkSexo, 2, 6);
        Campos.add(new Label("Telefone: "), 1, 7);
        Campos.add(txtTelefone, 2, 7);
        Campos.add(new Label("                "), 0, 8);

        if (this.opcao != "visualizar") {
        	Campos.add(btnSalvar, 1, 9);
        	Campos.add(btnLimpar,  3, 9);

        	btnSalvar.setOnAction(this);
        	btnLimpar.setOnAction(this);

        	txtNome.setDisable(false);
        	txtCPF.setDisable(false);
        	txtRG.setDisable(false);
        	txtDtNascimento.setDisable(false);
        	txtEndereco.setDisable(false);
        	checkSexo.setDisable(false);
        	txtTelefone.setDisable(false);
        } else {
        	txtNome.setDisable(true);
        	txtCPF.setDisable(true);
        	txtRG.setDisable(true);
        	txtDtNascimento.setDisable(true);
        	txtEndereco.setDisable(true);
        	checkSexo.setDisable(true);
        	txtTelefone.setDisable(true);
        }

		panPrincipal.setCenter(Campos);

		stage.setScene(scene);
		stage.setTitle("Cadastro de Cliente");
		stage.show();
	}

	public void handle(ActionEvent event) {
		if (event.getTarget() == btnSalvar) {
			Cliente cliente = boundaryToEntity();
			if( this.opcao == "incluir") {
				if (control.pesquisarPorCPF(cliente.getCPFCliente()) == null ) {
					control.adicionar(cliente);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Inclusão do cliente");
					alert.setHeaderText(null);
					alert.setContentText("Cliente incluído com sucesso!");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Inclusão do cliente");
					alert.setHeaderText(null);
					alert.setContentText("Já existe um cliente na base de dados com o CPF informado!");
					alert.showAndWait();
				}
			} else {
				control.editar(cliente, this.CPF);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Edição do cliente");
				alert.setHeaderText(null);
				alert.setContentText("Cliente editado com sucesso!");
				alert.showAndWait();
			}
		} else if (event.getTarget() == btnLimpar){
			txtNome.setText("");
            txtCPF.setText("");
            txtRG.setText("");
            txtDtNascimento.setText("");
            txtEndereco.setText("");
            checkSexo.setValue(null);
            txtTelefone.setText("");
		}
	}

	public Cliente boundaryToEntity() {
		Cliente cliente = new Cliente();
		try {
            cliente.setNome( txtNome.getText() );
            cliente.setCPFCliente( txtCPF.getText() );
            cliente.setRGCliente( txtRG.getText() );
            cliente.setdtnascimento( txtDtNascimento.getText() );
			cliente.setendereco( txtEndereco.getText() );
			cliente.setsexo( (String) checkSexo.getValue() );
            cliente.settelefone( txtTelefone.getText() );
		} catch (Exception ex) {
			System.out.println("Erro inserir o registro na tabela!");
		}
		return cliente;
	}

	public void entityToBoundary(Cliente cliente, String opcao, String CPF) {
		txtNome.setText( cliente.getnome() );
        txtCPF.setText( cliente.getCPFCliente() );
        txtRG.setText( cliente.getRGCliente() );
		txtDtNascimento.setText( cliente.getdtnascimento() );
        txtEndereco.setText( cliente.getendereco() );
        checkSexo.setValue( cliente.getsexo() );
        txtTelefone.setText( cliente.gettelefone() );
        setOpcao(opcao);
        setCPF(CPF);
	}

	public void setOpcao(String opcao){
		this.opcao = opcao;
	}

	public void setCPF(String CPF){
		this.CPF = CPF;
	}

}