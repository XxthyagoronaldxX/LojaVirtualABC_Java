package com.abctreinamentos.lojavirtual.views;

import java.util.List;

import javax.swing.JOptionPane;

import com.abctreinamentos.lojavirtual.daos.IClienteDAO;
import com.abctreinamentos.lojavirtual.models.ClienteModel;

public class ClienteViewCRUD {
	private IClienteDAO clienteDAO;

	public ClienteViewCRUD(IClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public void updateCliente() {
		List<ClienteModel> clienteList = clienteDAO.findAll();

		ClienteModel selected = (ClienteModel) JOptionPane.showInputDialog(null, "Cliente - UPDATE",
				"Informe o cliente para ser atualizado: ", 0, null, clienteList.toArray(), 0);

		if (selected == null)
			return;

		selected.setNome(JOptionPane.showInputDialog("Novo nome: ", selected.getNome()));
		selected.setEmail(JOptionPane.showInputDialog("Novo email: ", selected.getEmail()));
		selected.setCpf(JOptionPane.showInputDialog("Novo cpf: ", selected.getCpf()));

		if (clienteDAO.update(selected) == null) {
			JOptionPane.showMessageDialog(null, "Algo deu errado ao atualizar o cliente: " + selected.getNome());
		} else {
			JOptionPane.showMessageDialog(null, "O cliente: " + selected.getNome() + ", foi atualizado com sucesso!");
		}
	}

	public void removeCliente() {
		List<ClienteModel> clienteList = clienteDAO.findAll();

		ClienteModel selected = (ClienteModel) JOptionPane.showInputDialog(null, "Cliente - REMOÇÂO",
				"Informe o cliente para ser removido: ", 0, null, clienteList.toArray(), 0);

		if (selected == null)
			return;

		if (clienteDAO.remove(selected) == null) {
			JOptionPane.showMessageDialog(null, "Algo deu errado ao deletar o cliente: " + selected.getNome());
		} else {
			JOptionPane.showMessageDialog(null, "O cliente: " + selected.getNome() + ", foi removido com sucesso!");
		}
	}

	public void findAllCliente() {
		List<ClienteModel> clienteList = clienteDAO.findAll();

		if (clienteList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não há valores inseridos no banco!");
		} else {

			String message = clienteList.stream().map(cliente -> cliente.toString()).reduce("",
					(acc, value) -> acc + value + "\n");

			JOptionPane.showMessageDialog(null, message);
		}
	}

	public void findClienteByCpf() {
		String cpf = JOptionPane.showInputDialog("Informe o cpf para ser buscado: ");

		ClienteModel cliente = clienteDAO.findByCpf(cpf);

		if (cliente == null)
			JOptionPane.showMessageDialog(null, "Cliente com CPF: " + cpf + ", não foi encontrado!");
		else
			JOptionPane.showMessageDialog(null, cliente.toString());
	}

	public void addCliente() {
		String nome = JOptionPane.showInputDialog("Informe o nome do cliente: ");
		String cpf = JOptionPane.showInputDialog("Informe o cpf do cliente: ");
		String email = JOptionPane.showInputDialog("Informe o email do cliente: ");

		ClienteModel clienteModel = new ClienteModel(nome, cpf, email);

		if (clienteDAO.create(clienteModel) == null)
			JOptionPane.showMessageDialog(null, "Falha ao criar cliente!");
		else
			JOptionPane.showMessageDialog(null, "Cliente criado com sucesso!");

	}
}
