package Boundary;


import Entity.Funcionario;
import Control.FuncionarioControl;

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

public class FuncionarioBoundary extends Application implements EventHandler<ActionEvent> {
	private TextField txtCod = new TextField();
	private TextField txtNome = new TextField();
    private TextField txtCPF = new TextField();
    private TextField txtRG = new TextField();
    private TextField txtDtRegistro = new TextField();
	private TextField txtEndereco = new TextField();
	private ChoiceBox<String> checkSexo = new ChoiceBox<String>(FXCollections.observableArrayList("M", "F") );
	private String opcao = "incluir";
	private String CPF = "";

	private Button btnSalvar = new Button("Salvar");
	private Button btnLimpar = new Button("Limpar");

	private FuncionarioControl control = new FuncionarioControl();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scene = new Scene(panPrincipal, 350, 200);

		GridPane Campos = new GridPane();

		Campos.add(new Label("                "), 0, 1);
		Campos.add(new Label("Nome: "), 1, 1);
		Campos.add(txtNome, 2, 1);

		Campos.add(new Label("CPF: "), 1, 2);
		Campos.add(txtCPF, 2, 2);

		Campos.add(new Label("RG: "), 1, 3);
		Campos.add(txtRG, 2, 3);

		Campos.add(new Label("Data Registro: "), 1, 4);
		Campos.add(txtDtRegistro, 2, 4);

		Campos.add(new Label("Endereço: "), 1, 5);
		Campos.add(txtEndereco, 2, 5);

		Campos.add(new Label("Sexo"), 1, 6);
        Campos.add(checkSexo, 2, 6);
        Campos.add(new Label("                "), 0, 7);

       if (this.opcao != "visualizar") {
        Campos.add(btnSalvar, 1, 8);
		Campos.add(btnLimpar,  3, 8);

		btnSalvar.setOnAction(this);
		btnLimpar.setOnAction(this);

		txtNome.setDisable(false);
    	txtCPF.setDisable(false);
    	txtRG.setDisable(false);
    	txtCod.setDisable(false);
    	txtEndereco.setDisable(false);
    	checkSexo.setDisable(false);
    	txtDtRegistro.setDisable(false);
       } else {
    	   txtNome.setDisable(true);
       	txtCPF.setDisable(true);
       	txtRG.setDisable(true);
       	txtCod.setDisable(true);
       	txtEndereco.setDisable(true);
       	checkSexo.setDisable(true);
       	txtDtRegistro.setDisable(true);
       }

		panPrincipal.setCenter(Campos);

		stage.setScene(scene);
		stage.setTitle("Cadastro de Funcionario");
		stage.show();
	}

	public void handle(ActionEvent event) {
		if (event.getTarget() == btnSalvar) {
			Funcionario funcionario = boundaryToEntity();
			if( this.opcao == "incluir") {
				if (control.pesquisarPorCPF(funcionario.getCPFFunc()) == null ) {
					control.adicionar(funcionario);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Inclusão do funcionario");
					alert.setHeaderText(null);
					alert.setContentText("Funcionario incluído com sucesso!");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Inclusão do funcionario");
					alert.setHeaderText(null);
					alert.setContentText("Já existe um funcionario na base de dados com o CPF informado!");
					alert.showAndWait();
				}
			} else {
				control.editar(funcionario, this.CPF);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Edição do funcionario");
				alert.setHeaderText(null);
				alert.setContentText("Funcionario editado com sucesso!");
				alert.showAndWait();
			}
		} else if (event.getTarget() == btnLimpar){
			txtNome.setText("");
	    	txtCPF.setText("");
	    	txtRG.setText("");
	    	txtCod.setText("");
	    	txtEndereco.setText("");
	    	checkSexo.setValue(null);
	    	txtDtRegistro.setText("");
		}
	}
	public Funcionario boundaryToEntity() {
		Funcionario funcionario = new Funcionario();
		try {
			funcionario.setNome( txtNome.getText() );
			funcionario.setCPFFunc( txtCPF.getText() );
			funcionario.setRGFunc( txtRG.getText() );
			funcionario.setdtregistro( txtDtRegistro.getText() );
			funcionario.setendereco( txtEndereco.getText() );
			funcionario.setsexo( (String) checkSexo.getValue() );
		} catch (Exception ex) {
			System.out.println("Erro inserir o registro na tabela!");
		}
		return funcionario;
	}

	public void entityToBoundary(Funcionario funcionario, String opcao, String CPF) {
		txtNome.setText(funcionario.getnome());
        txtCPF.setText(funcionario.getCPFFunc());
        txtRG.setText(funcionario.getRGFunc());
		txtDtRegistro.setText(funcionario.getdtregistro());
        txtEndereco.setText(funcionario.getendereco());
        checkSexo.setValue(funcionario.getsexo());
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
