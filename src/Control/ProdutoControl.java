package Control;

import DAO.ProdutoDAO;
import Entity.Produto;

public class ProdutoControl {

	private ProdutoDAO dao = new ProdutoDAO();

	public void adicionar(Produto produto) {
		dao.adicionar(produto);
	}

	public void editar(Produto produto, String CodProd) {
		dao.editar(produto, CodProd);
	}

	public Produto pesquisarPorCod(int CodProd) {
		return dao.pesquisarPorCod(CodProd);
	}

	public boolean excluirPorCod(int CodProd) {
		return dao.excluirPorCod(CodProd);
	}

}
