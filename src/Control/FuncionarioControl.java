package Control;

import Entity.Funcionario;
import DAO.FuncionarioDAO;

public class FuncionarioControl{

	private FuncionarioDAO dao = new FuncionarioDAO();

	public void adicionar(Funcionario funcionario) {
		dao.adicionar(funcionario);
	}

	public void editar(Funcionario funcionario, String CPF) {
		dao.editar(funcionario, CPF);
	}

	public Funcionario pesquisarPorCPF(String CPF) {
		return dao.pesquisarPorCPF(CPF);
	}

	public boolean excluirPorCPF(String CPF) {
		return dao.excluirPorCPF(CPF);
	}

}