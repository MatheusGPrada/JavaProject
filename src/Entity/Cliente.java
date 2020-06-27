package Entity;

public class Cliente{
	private String nome = "";
	private String CPFCliente = "";
	private String RGCliente = "";
	private String dtnascimento = "";
	private String endereco = "";
	private String sexo = null;
	private String telefone = "";

	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCPFCliente(String CPFCliente) {
		this.CPFCliente = CPFCliente;
	}
	public void setRGCliente(String RGCliente) {
		this.RGCliente = RGCliente;
	}
	public void setdtnascimento(String dtnascimento) {
		this.dtnascimento = dtnascimento;
	}
	public void setendereco(String endereco) {
		this.endereco = endereco;
	}
	public void setsexo(String sexo) {
		this.sexo = sexo;
	}
	public void settelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getnome() {
		return nome;
	}
	public String getCPFCliente() {
		return CPFCliente;
	}
	public String getRGCliente() {
		return RGCliente;
	}
	public String getdtnascimento() {
		return dtnascimento;
	}
	public String getendereco() {
		return endereco;
	}
	public String getsexo() {
		return sexo;
	}
	public String gettelefone() {
		return telefone;
	}

}
