package com.abctreinamentos.lojavirtual.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.abctreinamentos.lojavirtual.models.CursoModel;

public class CursoMapper {
	public static CursoModel map(ResultSet resultSet) throws SQLException {
		CursoModel cursoModel = new CursoModel();
		
		cursoModel.setId(resultSet.getInt(1));
		cursoModel.setNome(resultSet.getString(2));
		cursoModel.setDescricao(resultSet.getString(3));
		cursoModel.setValor(resultSet.getDouble(4));
		
		return cursoModel;
	}
}
