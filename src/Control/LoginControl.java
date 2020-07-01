package Control;

import DAO.LoginDAO;
import Entity.Login;

public class LoginControl {

	private LoginDAO dao = new LoginDAO();

	public void adicionar(Login login) {
		dao.adicionar(login);
	}

	public void editar(Login login,  String User) {
		dao.editar(login, User);
	}

	public Boolean efetuarLogin(String User, String Password) {
		return dao.efetuarLogin(User, Password);
	}

	public Login pesquisarPorUser(String User) {
		return dao.pesquisarPorUser(User);
	}

	public boolean excluirPorUser(String User) {
		return dao.excluirPorUser(User);
	}

}
