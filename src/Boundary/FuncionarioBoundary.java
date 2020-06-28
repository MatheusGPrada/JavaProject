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
	private Boolean editar = false;

	private Button btnSalvar = new Button("Salvar");
	private Button btnLimpar = new Button("Limpar");

	private FuncionarioControl control = new FuncionarioControl();

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane panPrincipal = new BorderPane();
		Scene scene = new Scene(panPrincipal, 400, 250);

		GridPane Campos = new GridPane();

		Campos.add(new Label("                "), 0, 1);
		Campos.add(new Label("Código: "), 1, 1);
		Campos.add(txtCod, 2, 1);
		
		Campos.add(new Label("Nome: "), 1, 2);
		Campos.add(txtNome, 2, 2);
		
		Campos.add(new Label("CPF: "), 1, 3);
		Campos.add(txtCPF, 2, 3);
		
		Campos.add(new Label("RG: "), 1, 4);
		Campos.add(txtRG, 2, 4);
		
		Campos.add(new Label("Data Registro: "), 1, 5);
		Campos.add(txtDtRegistro, 2, 5);
		
		Campos.add(new Label("Endereço: "), 1, 6);
		Campos.add(txtEndereco, 2, 6);
		
		Campos.add(new Label("Sexo"), 1, 7);
        Campos.add(checkSexo, 2, 7);
        Campos.add(new Label("                "), 0, 8);

        Campos.add(btnSalvar, 1, 9);
		Campos.add(btnLimpar,  3, 9);

		btnSalvar.setOnAction(this);
		btnLimpar.setOnAction(this);

		panPrincipal.setCenter(Campos);

		stage.setScene(scene);
		stage.setTitle("Cadastro de Funcionario");
		stage.show();
	}

	public void handle(ActionEvent event) {
		if (event.getTarget() == btnSalvar) {
			Funcionario funcionario = boundaryToEntity();
			if( !this.editar ) {
				control.adicionar(funcionario);
			} else {
				control.editar(funcionario);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Edição do cliente");
				alert.setHeaderText(null);
				alert.setContentText("Cliente editado com sucesso!");
				alert.showAndWait();
			}
		} else if (event.getTarget() == btnLimpar){
			txtCod.setText("");
			txtNome.setText("");
            txtCPF.setText("");
            txtRG.setText("");
            txtDtRegistro.setText("");
            txtEndereco.setText("");
            checkSexo.setValue(null);
		}
	}

	public Funcionario boundaryToEntity() {
		Funcionario funcionario = new Funcionario();
		try {
			funcionario.setCodFunc(Integer.parseInt(txtCod.getText()));
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

	public void entityToBoundary(Funcionario funcionario, Boolean editar) {
		txtNome.setText(funcionario.getnome());
        txtCPF.setText(funcionario.getCPFFunc());
        txtRG.setText(funcionario.getRGFunc());
		txtDtRegistro.setText(funcionario.getdtregistro());
        txtEndereco.setText(funcionario.getendereco());
        checkSexo.setValue(funcionario.getsexo());
        setEditar(editar);
	}

	public void setEditar(Boolean editar){
		this.editar = editar;
        txtCPF.setEditable(!this.editar);
        txtRG.setEditable(!this.editar);
	}

	public static void main(String[] args) {
		Application.launch(FuncionarioBoundary.class, args);
	}
}
