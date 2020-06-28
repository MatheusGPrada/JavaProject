package Entity;

public class Produto {
	private int codProd = 0;
	private String nome = "";
	private int Qtd = 0;
	private String Valor = "";
	
	
	public int getCodProd() {
		return codProd;
	}
	public void setCodProd(int codProd) {
		this.codProd = codProd;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQtd() {
		return Qtd;
	}
	public void setQtd(int qtd) {
		Qtd = qtd;
	}
	public String getValor() {
		return Valor;
	}
	public void setValor(String valor) {
		Valor = valor;
	}

}
