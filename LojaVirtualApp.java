package com.abctreinamentos.lojavirtual;

import java.sql.Connection;

import javax.swing.JOptionPane;

import com.abctreinamentos.lojavirtual.daos.IClienteDAO;
import com.abctreinamentos.lojavirtual.daos.ICursoDAO;
import com.abctreinamentos.lojavirtual.daos.ImplClienteDAO;
import com.abctreinamentos.lojavirtual.daos.ImplCursoDAO;
import com.abctreinamentos.lojavirtual.datasources.MysqlDatasource;
import com.abctreinamentos.lojavirtual.views.ClienteViewCRUD;
import com.abctreinamentos.lojavirtual.views.CursoViewCRUD;

public class LojaVirtualApp {
	
	
	public static void main(String[] args) {
		Connection mysqlConnection = new MysqlDatasource().createConnection();
		
		if (mysqlConnection == null) {
			JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados!");
			return;
		}
		
		IClienteDAO clienteDAO = new ImplClienteDAO(mysqlConnection);
		ICursoDAO cursoDAO = new ImplCursoDAO(mysqlConnection);
		ClienteViewCRUD clienteViewCRUD = new ClienteViewCRUD(clienteDAO);
		CursoViewCRUD cursoViewCRUD = new CursoViewCRUD(cursoDAO);
		
		// clienteViewCRUD.addCliente();
		// clienteViewCRUD.removeCliente();
		clienteViewCRUD.findAllCliente();
		// clienteViewCRUD.updateCliente();
		// clienteViewCRUD.findClienteByCpf();
		
		// cursoViewCRUD.addCurso();
		// cursoViewCRUD.removeCurso();
		cursoViewCRUD.findAllCurso();
		// cursoViewCRUD.updateCurso();
		
		clienteDAO.close();
		cursoDAO.close();
	}

	
}
