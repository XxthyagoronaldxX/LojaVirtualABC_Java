package com.abctreinamentos.lojavirtual.daos;

import java.util.List;

public interface IDAO<T> {
	public T create(T data);
	public List<T> findAll();
	public T remove(T data);
	public T update(T data);
	public void close();
}
