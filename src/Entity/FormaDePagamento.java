package Entity;

public class FormaDePagamento {
	private int id = 0;
	private String descricao = "";
	private int numCartao = 0;
	private int validadeCartao = 0;
	private int codSeg = 0;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(int numCartao) {
		this.numCartao = numCartao;
	}
	public int getValidadeCartao() {
		return validadeCartao;
	}
	public void setValidadeCartao(int validadeCartao) {
		this.validadeCartao = validadeCartao;
	}
	public int getCodSeg() {
		return codSeg;
	}
	public void setCodSeg(int codSeg) {
		this.codSeg = codSeg;
	}
	
	
	
	
}
