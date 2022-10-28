package com.abctreinamentos.lojavirtual;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.abctreinamentos.lojavirtual.datasources.MysqlDatasource;

public class TestingConnection {

	public static void main(String[] args) throws SQLException {
		executeStoredProcedure();
	}

	public static void firstConnection() throws SQLException {
		Connection mysqlDatasource = new MysqlDatasource().createConnection();

		if (mysqlDatasource == null)
			return;

		Statement stmt = mysqlDatasource.createStatement();

		stmt.execute("USE lojavirtual_db;");

		mysqlDatasource.close();
	}

	public static void executeStoredProcedure() throws SQLException {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Informe o nome do cliente: ");
		String nome = entrada.next();

		System.out.println("Informe o cpf do cliente: ");
		String cpf = entrada.next();

		System.out.println("Informe o email do cliente: ");
		String email = entrada.next();

		String sql = "CALL SP_INSERIR_CLIENTE(?, ?, ?)";
		Connection connection = new MysqlDatasource().createConnection();
		CallableStatement stmt = connection.prepareCall(sql);

		stmt.setString(1, nome);
		stmt.setString(2, cpf);
		stmt.setString(3, email);
		stmt.execute();

		entrada.close();
	}

	public static void avoidingSqlInjection() throws SQLException {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Informe o nome do cliente: ");
		String nome = entrada.next();

		System.out.println("Informe o cpf do cliente: ");
		String cpf = entrada.next();

		System.out.println("Informe o email do cliente: ");
		String email = entrada.next();

		String sql = "INSERT INTO `cliente`(`nome`, `cpf`, `email`) VALUES (?, ?, ?);";
		Connection connection = new MysqlDatasource().createConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, nome);
		stmt.setString(2, cpf);
		stmt.setString(3, email);
		stmt.execute();

		entrada.close();
	}

	public static void sqlInjection() {
		Scanner entrada = new Scanner(System.in);

		// Se fizermos o SQL utilizando concatenação simples com String teremos risco de
		// ser alvo de sqlInjection.
		// Por exemplo:
		// Thyago'); DELETE FROM pessoas WHERE (1 = 1);
		System.out.println("Informe o nome do cliente: ");
		String nome = entrada.next();

		System.out.println("Informe o cpf do cliente: ");
		String cpf = entrada.next();

		System.out.println("Informe o email do cliente: ");
		String email = entrada.next();

		String sql = "INSERT INTO `cliente`(`nome`, `cpf`, `email`) VALUES (" + nome + ", " + cpf + ", " + email + ");";

		System.out.println(sql);
		entrada.close();
	}

}
