package Boundary;

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

import Entity.Produto;
import Control.ProdutoControl;

public class ProdutoBoundary implements EventHandler<ActionEvent> {


		private TextField txtNome = new TextField();
	    private TextField txtValor = new TextField();
	    private TextField txtQtd = new TextField();
	    private TextField txtCodProd = new TextField();
	    private String opcao = "incluir";
	    private String CodProd = "";


		private Button btnSalvar = new Button("Salvar");
		private Button btnLimpar = new Button("Limpar");

		private ProdutoControl control = new ProdutoControl();

		public void start(Stage stage) throws Exception {
			BorderPane panPrincipal = new BorderPane();
			Scene scene = new Scene(panPrincipal, 400, 150);

			GridPane Campos = new GridPane();

			Campos.add(new Label("                "), 0, 1);
			Campos.add(new Label("Nome: "), 1, 1);
			Campos.add(txtNome, 2, 1);
			Campos.add(new Label("Valor: "), 1, 2);
			Campos.add(txtValor, 2, 2);
			Campos.add(new Label("Quantidade: "), 1, 3);
			Campos.add(txtQtd, 2, 3);
			Campos.add(new Label("Código do Produto: "), 1, 4);
			Campos.add(txtCodProd, 2, 4);

			if (this.opcao != "visualizar") {
	        	Campos.add(btnSalvar, 1, 5);
	        	Campos.add(btnLimpar, 2, 5);

	        	btnSalvar.setOnAction(this);
	        	btnLimpar.setOnAction(this);

	        	txtNome.setDisable(false);
	        	txtValor.setDisable(false);
	        	txtQtd.setDisable(false);
	        	txtCodProd.setDisable(false);
	        } else {
	        	txtNome.setDisable(true);
	        	txtValor.setDisable(true);
	        	txtQtd.setDisable(true);
	        	txtCodProd.setDisable(true);
	        }

			panPrincipal.setCenter(Campos);

			stage.setScene(scene);
			stage.setTitle("Cadastro de produto");
			stage.show();
		}

		public void handle(ActionEvent event) {
			if (event.getTarget() == btnSalvar) {
				Produto produto = boundaryToEntity();
				if( this.opcao == "incluir") {
					if (control.pesquisarPorCod(produto.getCodProd()) == null ) {
						control.adicionar(produto);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Inclusão do produto");
						alert.setHeaderText(null);
						alert.setContentText("Produto incluído com sucesso!");
						alert.showAndWait();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Inclusão do produto");
						alert.setHeaderText(null);
						alert.setContentText("Código do produto já existe na base de dados!");
						alert.showAndWait();
					}
				} else {
					control.editar(produto,  this.CodProd);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Edição do produto");
					alert.setHeaderText(null);
					alert.setContentText("Produto editado com sucesso!");
					alert.showAndWait();
				}
			} else if (event.getTarget() == btnLimpar){
				txtNome.setText("");
	            txtValor.setText("");
	            txtQtd.setText("");
	            txtCodProd.setText("");
			}
		}

		public Produto boundaryToEntity() {
			Produto produto = new Produto();
			try {
	            produto.setNome( txtNome.getText() );
	            produto.setValor( txtValor.getText() );
	            produto.setQtd(Integer.parseInt(txtQtd.getText()));
	            produto.setCodProd( Integer.parseInt(txtCodProd.getText()));

			} catch (Exception ex) {
				System.out.println("Erro inserir o registro na tabela!");
			}
			return produto;
		}

		public void entityToBoundary(Produto produto, String opcao, String CodProd) {
			txtNome.setText( produto.getNome() );
	        txtValor.setText( produto.getValor() );
	        txtQtd.setText(String.valueOf (produto.getQtd()));
			txtCodProd.setText(String.valueOf (produto.getCodProd()));
			setOpcao(opcao);
			setCodProd(CodProd);
		}

		public void setOpcao(String opcao){
			this.opcao = opcao;
		}

		public void setCodProd(String CodProd){
			this.CodProd = CodProd;
		}

}
