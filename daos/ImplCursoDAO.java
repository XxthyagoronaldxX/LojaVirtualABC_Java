package com.abctreinamentos.lojavirtual.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abctreinamentos.lojavirtual.mapper.CursoMapper;
import com.abctreinamentos.lojavirtual.models.CursoModel;

public class ImplCursoDAO implements ICursoDAO {
	private Connection connection;

	public ImplCursoDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public CursoModel create(CursoModel data) {
		try {
			String sql = "INSERT INTO curso(nome, descricao, preco) VALUES(?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, data.getNome());
			stmt.setString(2, data.getDescricao());
			stmt.setDouble(3, data.getValor());
			stmt.execute();

			return data;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<CursoModel> findAll() {
		List<CursoModel> cursoList = new ArrayList<>();

		try {
			String sql = "SELECT id, nome, descricao, preco FROM curso";
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next())
				cursoList.add(CursoMapper.map(resultSet));

			return cursoList;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public CursoModel remove(CursoModel data) {
		try {
			String sql = "DELETE FROM curso WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, data.getId());
			stmt.execute();

			return data;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public CursoModel update(CursoModel data) {
		try {
			String sql = "UPDATE curso SET nome = ?, descricao = ?, preco = ? WHERE curso.id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, data.getNome());
			stmt.setString(2, data.getDescricao());
			stmt.setDouble(3, data.getValor());
			stmt.setInt(4, data.getId());
			stmt.execute();

			return data;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
