package com.abctreinamentos.lojavirtual.views;

import java.util.List;

import javax.swing.JOptionPane;

import com.abctreinamentos.lojavirtual.daos.ICursoDAO;
import com.abctreinamentos.lojavirtual.models.CursoModel;

public class CursoViewCRUD {
	private ICursoDAO cursoDAO;

	public CursoViewCRUD(ICursoDAO cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	public void addCurso() {
		String nome = JOptionPane.showInputDialog("Informe o nome do curso: ");
		String descricao = JOptionPane.showInputDialog("Informe a descricao do curso: ");
		double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do curso: "));

		CursoModel clienteModel = new CursoModel(nome, descricao, valor);

		if (cursoDAO.create(clienteModel) == null)
			JOptionPane.showMessageDialog(null, "Falha ao criar cliente!");
		else
			JOptionPane.showMessageDialog(null, "Cliente criado com sucesso!");

	}
	
	public void findAllCurso() {
		List<CursoModel> cursoList = cursoDAO.findAll();

		if (cursoList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não há valores inseridos no banco!");
		} else {

			String message = cursoList.stream().map(cliente -> cliente.toString()).reduce("",
					(acc, value) -> acc + value + "\n");

			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	public void removeCurso() {
		List<CursoModel> cursoList = cursoDAO.findAll();

		CursoModel selected = (CursoModel) JOptionPane.showInputDialog(null, "Cliente - REMOÇÂO",
				"Informe o cliente para ser removido: ", 0, null, cursoList.toArray(), 0);

		if (selected == null)
			return;

		if (cursoDAO.remove(selected) == null) {
			JOptionPane.showMessageDialog(null, "Algo deu errado ao deletar o cliente: " + selected.getNome());
		} else {
			JOptionPane.showMessageDialog(null, "O cliente: " + selected.getNome() + ", foi removido com sucesso!");
		}
	}
	
	public void updateCurso() {
		List<CursoModel> cursoList = cursoDAO.findAll();

		CursoModel selected = (CursoModel) JOptionPane.showInputDialog(null, "Cliente - UPDATE",
				"Informe o cliente para ser atualizado: ", 0, null, cursoList.toArray(), 0);

		if (selected == null)
			return;

		selected.setNome(JOptionPane.showInputDialog("Novo nome: ", selected.getNome()));
		selected.setDescricao(JOptionPane.showInputDialog("Nova descrição: ", selected.getDescricao()));
		selected.setValor(Double.parseDouble(JOptionPane.showInputDialog("Novo valor: ", selected.getValor())));

		if (cursoDAO.update(selected) == null) {
			JOptionPane.showMessageDialog(null, "Algo deu errado ao atualizar o cliente: " + selected.getNome());
		} else {
			JOptionPane.showMessageDialog(null, "O cliente: " + selected.getNome() + ", foi atualizado com sucesso!");
		}
	}
}
