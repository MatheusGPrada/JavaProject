package Entity;

public class Funcionario {
	private int CodFunc = 0;
	private String nome = "";
	private String CPFFunc = "";
	private String RGFunc = "";
	private String dtregistro = "";
	private String endereco = "";
	private String sexo = null;
	
	public void setCodFunc(int CodFunc) {
		this.CodFunc = CodFunc;
	}		
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCPFFunc(String CPFFunc) {
		this.CPFFunc = CPFFunc;
	}
	public void setRGFunc(String RGFunc) {
		this.RGFunc = RGFunc;
	}
	public void setdtregistro(String dtregistro) {
		this.dtregistro = dtregistro;
	}
	public void setendereco(String endereco) {
		this.endereco = endereco;
	}
	public void setsexo(String sexo) {
		this.sexo = sexo;
	}
	
	public int getCodFunc() {
		return CodFunc;
	}
	public String getnome() {
		return nome;
	}
	public String getCPFFunc() {
		return CPFFunc;
	}
	public String getRGFunc() {
		return RGFunc;
	}
	public String getdtregistro() {
		return dtregistro;
	}
	public String getendereco() {
		return endereco;
	}
	public String getsexo() {
		return sexo;
	}
}
