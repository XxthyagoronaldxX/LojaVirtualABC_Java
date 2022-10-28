package com.abctreinamentos.lojavirtual.daos;

import com.abctreinamentos.lojavirtual.models.ClienteModel;

public interface IClienteDAO extends IDAO<ClienteModel>{
	public ClienteModel findByCpf(String cpf);
}
