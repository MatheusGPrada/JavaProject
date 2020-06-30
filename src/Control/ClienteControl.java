package Control;

import Entity.Cliente;
import DAO.ClienteDAO;

public class ClienteControl {

	private ClienteDAO dao = new ClienteDAO();

	public void adicionar(Cliente cliente) {
		dao.adicionar(cliente);
	}

	public void editar(Cliente cliente,  String CPF) {
		dao.editar(cliente, CPF);
	}

	public Cliente pesquisarPorCPF(String CPF) {
		return dao.pesquisarPorCPF(CPF);
	}


	public boolean excluirPorCPF(String CPF) {
		return dao.excluirPorCPF(CPF);
	}

}